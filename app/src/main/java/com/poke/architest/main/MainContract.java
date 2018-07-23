package com.poke.architest.main;

import com.poke.architest.data.Book;

import java.util.List;

public interface MainContract {
	interface View{
		void showError(String errorMessage);
		void showEmpty();
		void showList(List<Book> books);

		void showProgress();
		void hideProgress();

		void showBookDetails(int index);
	}

	interface Presenter{
		void searchText(String text);

		void onBookRowClicked(int position);
	}
}
