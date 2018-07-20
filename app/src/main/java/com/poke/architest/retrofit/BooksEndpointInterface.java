package com.poke.architest.retrofit;

import com.poke.architest.data.Book;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BooksEndpointInterface {

	@GET("volumes")
	Call<ResponseBody> getBooks(@Query("q") String q);
}
