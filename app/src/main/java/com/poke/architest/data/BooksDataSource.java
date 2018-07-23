package com.poke.architest.data;

import android.support.annotation.NonNull;

import java.util.List;

public interface BooksDataSource {

	interface LoadBooksCallback {

		void onBooksLoaded(List<Book> tasks);

		void onDataNotAvailable();
	}

	void loadBooks(String searchKey, @NonNull LoadBooksCallback callback);

	int getBookCount();
	Book getBookAt(int index);
}
