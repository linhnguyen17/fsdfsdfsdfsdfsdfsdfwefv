package com.example.mydemo;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.capricorn.ArcMenu;
import com.example.com.example.mydemo.R;
import com.example.mydemo.adapter.menu_home_adapter;
import com.example.mydemo.bean.Quan;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

public class HomeActivity extends Activity{

	FadingActionBarHelper   mFadingActionBarHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_home);


		ActionBar mActionBar = getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.home_custom_actionbar, null);
		TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_bar);
		mTitleTextView.setText("My Own Title");

		ImageView imageButton = (ImageView) mCustomView.findViewById(R.id.imagebar);
		imageButton.setImageResource(R.drawable.ic_cooking);
		mActionBar.setCustomView(mCustomView);
		mActionBar.setDisplayShowCustomEnabled(true);

		mFadingActionBarHelper = new FadingActionBarHelper()
		.actionBarBackground(R.drawable.ab_background)
		.headerLayout(R.layout.fragment_header_home)
		.contentLayout(R.layout.home_activity);
		setContentView(mFadingActionBarHelper.createView(this));
		mFadingActionBarHelper.initActionBar(this);


		//		ImageView logo = (ImageView) findViewById(android.R.id.home);
		//		logo.setBackgroundResource(R.drawable.ic_cooking);

			
		new AsyncTask<Void, Void, List<Quan>>() {

			@Override
			protected List<Quan> doInBackground(Void... params) {
				return DreamApplication.getmDaoSession().getQuanDao()
						.queryBuilder().build().list();
			}

			protected void onPostExecute(List<Quan> result) {
				List<Quan> listuse = result;
				ListView listView = (ListView) findViewById(android.R.id.list);
				menu_home_adapter adapter = new menu_home_adapter(getApplicationContext(), listuse);
				listView.setAdapter(adapter);
			};
		}.execute();


		final Animation animScale = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_in);
		//
		//		AnimationListener animationlist = new AnimationListener() {
		//
		//			@Override
		//			public void onAnimationStart(Animation animation) {
		//				// TODO Auto-generated method stub
		//
		//			}
		//
		//			@Override
		//			public void onAnimationRepeat(Animation animation) {
		//				// TODO Auto-generated method stub
		//
		//			}
		//
		//			@Override
		//			public void onAnimationEnd(Animation animation) {
		//				// TODO Auto-generated method stub
		//			}
		//		};
		//		animScale.setAnimationListener(animationlist);
		imageButton.setAnimation(animScale);

		//=================================== MENU ARC =======================
		final int[] ITEM_DRAWABLES = { R.drawable.ic_1,R.drawable.ic_2,R.drawable.ic_3, R.drawable.ic_4, R.drawable.ic_5, R.drawable.ic_6 };
		// 1 : back top  
		// 2 : yêu thíc
		// 3 : xem lịch hẹn 
		// 4 : chỉnh sửa trang cá nhân 
		// 5 : theo dõi bạn bè.
		// 6 : Back to main

		ArcMenu arcMenu2 = (ArcMenu) findViewById(R.id.arc_menu_2);
		initArcMenu(arcMenu2, ITEM_DRAWABLES);

		//=================================== MENU ARC =======================
	}

	//============================
	// End Creat 
	//============================
	private void initArcMenu(ArcMenu menu, int[] itemDrawables) {
		final int itemCount = itemDrawables.length;
		for (int i = 0; i < itemCount; i++) {
			ImageView item = new ImageView(this);
			item.setImageResource(itemDrawables[i]);

			final int position = i;
			menu.addItem(item, new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(HomeActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_github) { // open repo at GitHub
			//            Uri uri = Uri.parse(LINK_TO_GITHUB);
			//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			//            startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public FadingActionBarHelper getFadingActionBarHelper() {
		return mFadingActionBarHelper;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
		this.finish();
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}
}
