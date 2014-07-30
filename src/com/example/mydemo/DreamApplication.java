package com.example.mydemo;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mydemo.bean.DaoMaster;
import com.example.mydemo.bean.DaoSession;
import com.example.mydemo.utils.AppSharedPreference;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class DreamApplication extends Application {
	private static DreamApplication dreamApplication;
	private DaoSession mDaoSession;
	private AppSharedPreference mPreference;

	public static DreamApplication getDreamApplication() {
		return dreamApplication;
	}

	public static DaoSession getmDaoSession() {
		Log.d("", "dreamApplication "+dreamApplication);
		return dreamApplication.mDaoSession;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		dreamApplication = this;
		Log.d("", "CREATE APP");
		initDB();
		initImageLoader();
		dreamApplication.mPreference = new AppSharedPreference(this);
	}

	void initDB() {
		SQLiteOpenHelper sql = new DaoMaster.DevOpenHelper(getApplicationContext(), "fsdfsdfsdfsdfsdfsdfwefv-db", null);
		DaoMaster daoMaster = new DaoMaster(sql.getWritableDatabase());
		mDaoSession = daoMaster.newSession();
		Log.e("initDB", "ok");
	}

	void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
				.denyCacheImageMultipleSizesInMemory().writeDebugLogs().build();
		ImageLoader.getInstance().init(config);
	}

	public static AppSharedPreference getPreference() {
		return dreamApplication.mPreference;
	}
}
