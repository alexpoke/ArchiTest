package com.poke.architest.detail;

import com.poke.architest.data.Book;
import com.poke.architest.data.BooksRepository;

public class DetailPresenter implements DetailContract.Presenter{

	private DetailContract.View mView;

	private BooksRepository mRepository;

	private int mIndex;



	public DetailPresenter(int bookIndex, BooksRepository repo, DetailContract.View view){
		mIndex = bookIndex;
		mRepository = repo;
		mView = view;
	}

	private void loadBook(int index){
		Book bookAtPos = mRepository.getBookAt(index);
		mView.showBook(bookAtPos);
	}

	@Override
	public void start() {
		loadBook(mIndex);
	}
}
