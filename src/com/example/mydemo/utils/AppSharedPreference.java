package com.example.mydemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

/**
 * Lớp sharedreference dùng chung cho toàn bộ app
 * -----------------------------------------------
 * 
 * 
 */
public class AppSharedPreference {

	private final SharedPreferences preferences;

	private final String DefPreferenceName = "dream_come_true_reference";// name
																			// of
																			// preferences
																			// file
																			// (preferenceName.xml)
	private SharedPreferences.Editor editor;

	/**
	 * This will initialize an instance of the SecurePreferences class
	 * 
	 * @param context
	 *            your current context.
	 */
	public AppSharedPreference(Context context) {
		this.preferences = context.getSharedPreferences(DefPreferenceName, Context.MODE_PRIVATE);
	}

	public AppSharedPreference(Context context, String referenceName) {
		this.preferences = context.getSharedPreferences(referenceName, Context.MODE_PRIVATE);
	}

	public void loadTextViewByKey(TextView tv, String key) {
		if (this.containsKey(key)) {
			String text = this.getString(key);
			if (text != null && text != "") {
				tv.setText(text.replace("\\n", "\n"));
			}

		}
	}

	public void putString(String key, String value) {
		if (editor != null) {
			editor.putString(key, value);
		}
		if (value == null) {
			preferences.edit().remove(key).commit();
		} else {
			preferences.edit().putString(key, value).commit();
		}
	}

	public String getString(String key) {
		if (preferences.contains(key)) {
			String strValue = preferences.getString(key, "");
			return strValue.replace("\\n", "\n");
		}
		return null;
	}

	public void putBoolean(String key, boolean value) {
		preferences.edit().putBoolean(key, value).commit();
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		if (preferences.contains(key))
			return preferences.getBoolean(key, defaultValue);
		else
			return defaultValue;
	}

	public void putInteger(String key, int value) {
		preferences.edit().putInt(key, value).commit();
	}

	public int getInteger(String key, int defaultValue) {
		if (preferences.contains(key))
			return preferences.getInt(key, defaultValue);
		else
			return defaultValue;
	}

	public void putLong(String key, long value) {
		preferences.edit().putLong(key, value).commit();
	}

	public long getLong(String key, long defaultValue) {
		if (preferences.contains(key))
			return preferences.getLong(key, defaultValue);
		else
			return defaultValue;
	}

	public void putDouble(final String key, final double value) {
		preferences.edit().putLong(key, Double.doubleToRawLongBits(value)).commit();
	}

	public double getDouble(final String key, final double defaultValue) {
		return Double.longBitsToDouble(preferences.getLong(key, Double.doubleToLongBits(defaultValue)));
	}

	public void removeByKey(String key) {
		if (key != null && key != "" && preferences.contains(key)) {
			preferences.edit().remove(key).commit();
		}
	}

	public boolean containsKey(String key) {
		return preferences.contains(key);
	}

	public void clear() {
		preferences.edit().clear().commit();
	}

	public void beginTransaction() {
		editor = preferences.edit();
	}

	public void endTransaction() {
		editor.commit();
		editor = null;
	}
}
