package com.poke.architest.main;

import com.poke.architest.data.Book;
import com.poke.architest.data.BooksDataSource;
import com.poke.architest.data.BooksRepository;

import java.util.List;

public class MainPresenter implements MainContract.Presenter{

	private MainContract.View mView;

	private BooksRepository mRepository;

	private List<Book> mBooks;

	public MainPresenter(BooksRepository repo, MainContract.View view){
		mRepository = repo;
		mView = view;
	}

	@Override
	public void searchText(String text) {
		mView.showProgress();
		mRepository.loadBooks(text, new BooksDataSource.LoadBooksCallback() {
			@Override
			public void onBooksLoaded(List<Book> books) {
				mBooks = books;

				mView.hideProgress();
				mView.showList();
			}

			@Override
			public void onDataNotAvailable() {
				mView.hideProgress();
				mView.showEmpty();
			}
		});


	}

	public int getBooksCount() {
		return mBooks.size();
	}

	public void onBindBookRowViewAtPosition(int position, BooksRowView  rowView) {
		//Repository repo = repositories.get(position);
		rowView.setTitle(mBooks.get(position).getTitle());
		rowView.setImageURL(mBooks.get(position).getImageURL());


	}
}
