package com.poke.architest.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.poke.architest.R;

class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder>{

	private final MainPresenter mPresenter;

	public BookListAdapter(MainPresenter presenter) {
		mPresenter = presenter;
	}

	@NonNull
	@Override
	public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new BookViewHolder(LayoutInflater.from(parent.getContext())
				.inflate(R.layout.row_view, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
		mPresenter.onBindBookRowViewAtPosition(position, holder);

		Glide.with(holder.imageView.getContext())
				.load(holder.mImageURL)
				.into(holder.imageView);
	}

	@Override
	public int getItemCount() {
		return mPresenter.getBooksCount();
	}

	public class BookViewHolder  extends RecyclerView.ViewHolder implements BooksRowView, View.OnClickListener{
		TextView titleTextView;
		TextView authorTextView;
		ImageView imageView;
		String mImageURL;

		public BookViewHolder(View itemView) {
			super(itemView);
			titleTextView = itemView.findViewById(R.id.bookTitleText);
			authorTextView = itemView.findViewById(R.id.bookAuthorText);
			imageView = itemView.findViewById(R.id.ivThumbnail);

			itemView.setOnClickListener(this);
		}

		@Override
		public void setTitle(String title) {
			titleTextView.setText(title);
		}

		@Override
		public void setAuthor(String author) {
			authorTextView.setText(author);
		}

		@Override
		public void setImageURL(String imageURL) {
			mImageURL = imageURL;
		}

		@Override
		public void onClick(View view) {
			if(mPresenter != null){
				mPresenter.onBookRowClicked(getAdapterPosition());
			}
		}
	}
}
