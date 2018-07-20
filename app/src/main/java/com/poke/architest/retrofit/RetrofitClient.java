package com.poke.architest.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
	public static final String BASE_URL = "https://www.googleapis.com/books/v1/";
	private static Retrofit retrofit = null;

	public static Retrofit getClient() {
		if (retrofit==null) {
			retrofit = new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return retrofit;
	}

	public static BooksEndpointInterface getAPIService() {

		return RetrofitClient.getClient().create(BooksEndpointInterface.class);
	}
}
