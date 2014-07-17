package com.example.mydemo.utils;

/**
 * AccountManger : manage account name 
 * 
 *
 */
public class AccountManger {

	public static final String ACCOUNT_NAME_KEY = "ACCOUNT_NAME_KEY";

	AppSharedPreference mPref;

	public AccountManger(AppSharedPreference pref) {
		this.mPref = pref;
	}

	public void setCurrenAccountName(String accountName) {
		mPref.putString(ACCOUNT_NAME_KEY, accountName);
	}

	public String getAccountName() {
		return mPref.getString(ACCOUNT_NAME_KEY);
	}

}
