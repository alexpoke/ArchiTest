package com.poke.architest.detail;

import com.poke.architest.data.Book;

public interface DetailContract {
	interface View{
		void showBook(Book book);
	}

	interface Presenter{
		void start();
	}
}
