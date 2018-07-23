package com.poke.architest.detail;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.poke.architest.R;
import com.poke.architest.data.Book;
import com.poke.architest.data.BooksRepository;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

	public static final String BOOK_INDEX = "index";

	private DetailPresenter mPresenter;

	@BindView(R.id.tvTitle)
	TextView tvTitle;

	@BindView(R.id.tvAuthors)
	TextView tvAuthors;

	@BindView(R.id.tvDescription)
	TextView tvDescription;

	@BindView(R.id.imageView)
	ImageView thumbnail;

	@BindView(R.id.btnBuy)
	Button btnBuy;

	private String buyUrl;

	@OnClick(R.id.btnBuy)
	void buy(){
		Toast.makeText(this, "buy", Toast.LENGTH_SHORT).show();
		try{
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(buyUrl)));
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		if(getSupportActionBar() != null){
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setDisplayShowHomeEnabled(true);
		}

		ButterKnife.bind(this);

		int bookIndex = getIntent().getIntExtra(BOOK_INDEX, -1);
		if(bookIndex == -1) {
			//TODO trigger error
			return;
		}

		mPresenter = new DetailPresenter(bookIndex, BooksRepository.getInstance(), this);

	}

	@Override
	protected void onResume() {
		super.onResume();
		mPresenter.start();
	}

	@Override
	public void showBook(Book book) {

		tvTitle.setText(book.getTitle());
		if(book.getAuthors().length == 1){
			tvAuthors.setText(book.getAuthors()[0]);
		}else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < book.getAuthors().length; i++){
				sb.append(book.getAuthors()[i]);
				if(i < book.getAuthors().length - 1){
					sb.append(", ");
				}
			}
			tvAuthors.setText(sb.toString());
		}

		tvDescription.setText(book.getDescription());

		Glide.with(this)
				.load(book.getImageURL())
				.into(thumbnail);

		if(book.getBuyLink() != null){
			btnBuy.setVisibility(View.VISIBLE);
			buyUrl = book.getBuyLink();
		}

	}
}
