package com.poke.architest.data;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.poke.architest.retrofit.BooksEndpointInterface;
import com.poke.architest.retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksRepository implements BooksDataSource {

	private static BooksRepository INSTANCE = null;

	public static BooksRepository getInstance(){
		if(INSTANCE == null){
			INSTANCE = new BooksRepository();
		}

		return INSTANCE;
	}



	@Override
	public void loadBooks(String searchKey, @NonNull final LoadBooksCallback callback) {
		BooksEndpointInterface apiService = RetrofitClient.getAPIService();
		apiService.getBooks(searchKey).enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				if(response.isSuccessful()) {
					try{
						List<Book> books = new ArrayList<>();
						String body = response.body().string();
						JSONObject bodyJO = new JSONObject(body);
						JSONArray items = bodyJO.getJSONArray("items");
						for (int i = 0; i < items.length(); i++){
							JSONObject item = items.getJSONObject(i);
							Book book = new Book(item.getJSONObject("volumeInfo").getString("title"));

							book.setId(item.getString("id"));
							String urlsStr = item.getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail");
							if(!TextUtils.isEmpty(urlsStr)) {
								book.setImageURL(urlsStr);
							}

							if(item.getJSONObject("volumeInfo").has("authors")){
								JSONArray authorsJA = item.getJSONObject("volumeInfo").getJSONArray("authors");
								if(authorsJA.length() > 0){
									String[] authors = new String[authorsJA.length()];
									for(int j = 0; j < authorsJA.length(); j++){
										authors[j] = (String)authorsJA.get(j);
									}

									book.setAuthors(authors);
								}
							}

							book.setDescription(item.getJSONObject("volumeInfo").getString("description"));

							if( item.getJSONObject("volumeInfo").has("saleInfo") &&
									item.getJSONObject("volumeInfo").getJSONObject("saleInfo").has("buyLink")){
								book.setBuyLink(item.getJSONObject("volumeInfo").getJSONObject("saleInfo").getString("buyLink"));
							}
							books.add(book);
						}

						callback.onBooksLoaded(books);
					}catch (Exception e){
						e.printStackTrace();
						callback.onDataNotAvailable();
					}
				}else {
					callback.onDataNotAvailable();
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				callback.onDataNotAvailable();
			}
		});
	}

}
