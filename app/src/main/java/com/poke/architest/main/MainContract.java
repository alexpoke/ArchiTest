package com.poke.architest.main;

public interface MainContract {
	interface View{
		void showError(String errorMessage);
		void showEmpty();
		void showList();

		void showProgress();
		void hideProgress();
	}

	interface Presenter{
		void searchText(String text);
	}
}