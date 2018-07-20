package com.poke.architest.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.poke.architest.R;

import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		if(getActionBar() != null){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		ButterKnife.bind(this);


	}
}
