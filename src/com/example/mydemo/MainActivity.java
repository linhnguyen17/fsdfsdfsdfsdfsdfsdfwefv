package com.example.mydemo;
/*
 * Copyright (C) 2013 AChep@xda <artemchep@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.com.example.mydemo.R;
import com.example.mydemo.adapter.menu_main_adapter;
import com.example.mydemo.bean.Quan;
import com.example.mydemo.bean.QuanDao;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

public class MainActivity extends Activity {

	private static final String LINK_TO_GITHUB = "https://github.com/AChep/Header2ActionBar";

	private FadingActionBarHelper mFadingActionBarHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFadingActionBarHelper = new FadingActionBarHelper()
		.actionBarBackground(R.drawable.ab_background)
		.headerLayout(R.layout.fragment_header)
		.contentLayout(R.layout.fragment_listview);

		setContentView(mFadingActionBarHelper.createView(this));
		mFadingActionBarHelper.initActionBar(this);


		ListView listView = (ListView) findViewById(android.R.id.list);
		String[] txt_m = getResources().getStringArray(R.array.home_item_menu);
		menu_main_adapter adapter = new menu_main_adapter(getApplicationContext(), R.layout.item_list_main, txt_m);
		listView.setAdapter(adapter);


		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, final View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				if(position == 1){


					final Animation animScale = AnimationUtils.loadAnimation(getApplication(),R.anim.tran_scale_out);
					final  Animation animationFadeIn = AnimationUtils.loadAnimation(getApplication(), R.anim.fadein);
					final  Animation   animationFadeOut = AnimationUtils.loadAnimation(getApplication(), R.anim.fadeout);
					AnimationListener animation1 = new AnimationListener() {

						@Override
						public void onAnimationStart(Animation animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationRepeat(Animation animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationEnd(Animation animation) {
							// TODO Auto-generated method stub
							arg1.setVisibility(View.GONE);
							MainActivity.this.finish();
							Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
							startActivity( intent);
							overridePendingTransition(R.anim.fadein, R.anim.fadeout);
						}
					};
					animScale.setAnimationListener(animation1);
					arg1.setAnimation(animScale);




				}
			}
		});

		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				QuanDao categoryDao = DreamApplication.getmDaoSession().getQuanDao();
				if (categoryDao.queryBuilder().count() == 0) {

					List<Quan> listhome = new ArrayList<Quan>();
					listhome.add(new Quan(null, "acb", "canh ga", 100, "so 2 hang non", "", null, null, 133, 3, 0, 0112323323, 011111111, 0));
					listhome.add(new Quan(null, "acb", "canh ga", 100, "so 2 hang non", "", null, null, 133, 3, 0, 0111111111, 011111111, 0));
					listhome.add(new Quan(null, "acb", "canh ga", 100, "so 2 hang non", "", null, null, 133, 3, 0, 0111111111, 011111111, 0));
					listhome.add(new Quan(null, "acb", "canh ga", 100, "so 2 hang non", "", null, null, 133, 3, 0, 0111111111, 011111111, 0));
					listhome.add(new Quan(null, "acb", "canh ga", 100, "so 2 hang non", "", null, null, 133, 3, 0, 0111111111, 011111111, 0));
					listhome.add(new Quan(null, "acb", "canh ga", 100, "so 2 hang non", "", null, null, 133, 3, 0, 0111111111, 011111111, 0));

					DreamApplication.getmDaoSession().getQuanDao().insertInTx(listhome);

				}
				return null;
			}

		}.execute();
	}

	// ==========================================================================================
	// end creat
	//===========================================================================================


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
			Uri uri = Uri.parse(LINK_TO_GITHUB);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			return true;
		}
//		if (id == R.id.action_search) { // open repo at GitHub
//			
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}

	public FadingActionBarHelper getFadingActionBarHelper() {
		return mFadingActionBarHelper;
	}

}
