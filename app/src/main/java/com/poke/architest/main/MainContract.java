package com.poke.architest.main;

public interface MainContract {
	interface View{
		void showError(String errorMessage);
		void showEmpty();
		void showList();

		void showProgress();
		void hideProgress();

		void showBookDetails(String id);
	}

	interface Presenter{
		void searchText(String text);

		void onBookRowClicked(int position);
	}
}
