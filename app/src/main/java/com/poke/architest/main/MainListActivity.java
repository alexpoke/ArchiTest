package com.poke.architest.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.util.FixedPreloadSizeProvider;
import com.poke.architest.R;
import com.poke.architest.data.BooksRepository;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainListActivity extends AppCompatActivity implements MainContract.View{

	private MainPresenter mPresenter;
	private RecyclerView.LayoutManager mLayoutManager;
	private RecyclerView.Adapter<BookListAdapter.BookViewHolder> mAdapter;

	@OnClick(R.id.btnSearch)
	void search(){
		if(etSearch == null)
			return;

		if(TextUtils.isEmpty(etSearch.getText())){
			showError("Search text must not be empty!");
			return;
		}

		try {
			InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		} catch (Exception e) {
			// TODO: handle exception
		}

		mPresenter.searchText(etSearch.getText().toString());
	}

	@BindView(R.id.etSearch)
	EditText etSearch;

	@BindView(R.id.tvEmpty)
	TextView tvEmpty;

	@BindView(R.id.recyclerView)
	RecyclerView mRecyclerView;

	@BindView(R.id.progressBar)
	ProgressBar mProgressBar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_list);

		ButterKnife.bind(this);


		mPresenter = new MainPresenter(BooksRepository.getInstance(), this);

		// use a linear layout manager
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);

		// specify an adapter (see also next example)
		mAdapter = new BookListAdapter(mPresenter);
		mRecyclerView.setAdapter(mAdapter);
	}

	@Override
	public void showError(String errorMessage) {
		Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showEmpty() {
		hideProgress();

		mRecyclerView.setVisibility(View.GONE);
		tvEmpty.setVisibility(View.VISIBLE);
	}

	@Override
	public void showList() {
		tvEmpty.setVisibility(View.GONE);

		mRecyclerView.getAdapter().notifyDataSetChanged();
		mRecyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showProgress() {
		mProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgress() {
		mProgressBar.setVisibility(View.GONE);
	}

	@Override
	public void showBookDetails(String id) {
		Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
	}
}
