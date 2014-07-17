package com.example.mydemo.utils;

import java.io.File;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Utils {

	/**
	 * Hide keyboard programatically
	 * 
	 * @param context
	 * @param et
	 */
	public static void hideSoftKeyboard(Context context, EditText et) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
	}

	public static File createTempFileImage() {
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"DreamCamera");
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("ApoliCameraApp", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
		File tempFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".png");
		Log.e("getOutputMediaFile", "current_path_image_from_camera:" + tempFile.getPath());
		return tempFile;
	}

	public static String getRealPathFromURI(Context context, Uri contentUri) {
		Cursor cursor = null;
		try {
			String[] proj = { MediaStore.Images.Media.DATA };
			cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	public static void hideSoftKeyboard(Activity activity) {
		if (activity == null) {
			return;
		}
		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		View view = activity.getCurrentFocus();
		if (view != null)
			inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}

	public static void showSoftKeyboard(Activity activity, View view) {
		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.showSoftInput(view, 0);
	}

	public static void setupUI(View view) {

		// Set up touch listener for non-text box views to hide keyboard.
		if (!(view instanceof EditText)) {

			view.setOnTouchListener(new OnTouchListener() {

				public boolean onTouch(View v, MotionEvent event) {
					hideSoftKeyboard((Activity) v.getContext());
					return false;
				}

			});
		}

		// If a layout container, iterate over children and seed recursion.
		if (view instanceof ViewGroup) {

			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

				View innerView = ((ViewGroup) view).getChildAt(i);

				setupUI(innerView);
			}
		}
	}

	public static void createYNDialog(Context context, String title, String message,
			DialogInterface.OnClickListener onOK, DialogInterface.OnClickListener onCancel) {
		if (context == null) {
			return;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		if (title != null) {
			builder.setTitle(title);
		}

		builder.setMessage(message);
		builder.setPositiveButton("sigout", onOK);
		builder.setNegativeButton("ã‚­ãƒ£ãƒ³ã‚»ãƒ«", onCancel);
		builder.create().show();

	}

	/**
	 * @param context
	 * @param message
	 * @param onOK
	 * @param onCancel
	 */
	public static void createYN_JPDialog(Context context, String title, String message,
			DialogInterface.OnClickListener onOK, DialogInterface.OnClickListener onCancel) {
		if (context == null) {
			return;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		if (title != null) {
			builder.setTitle(title);
		}

		builder.setMessage(message);
		builder.setPositiveButton("ã?¯ã?„", onOK);
		builder.setNegativeButton("ã?„ã?„ã?ˆ", onCancel);
		builder.create().show();

	}

	/**
	 * @param context
	 * @param title
	 * @param message
	 * @param okBtn
	 * @param cancelBtn
	 * @param onOK
	 * @param onCancel
	 */
	public static void createYNDialog(Context context, String title, String message, String okBtn, String cancelBtn,
			DialogInterface.OnClickListener onOK, DialogInterface.OnClickListener onCancel) {
		if (context == null) {
			return;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		if (title != null) {
			builder.setTitle(title);
		}

		builder.setMessage(message);
		builder.setPositiveButton(okBtn, onOK);
		builder.setNegativeButton(cancelBtn, onCancel);
		builder.create().show();

	}

	/**
	 * @param context
	 * @param message
	 * @param onOK
	 */
	public static void createYDialog(Context context, String message, DialogInterface.OnClickListener onOK) {
		if (context == null) {
			return;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
		builder.setPositiveButton("ã‚ªãƒ¼ã‚­ãƒ¼", onOK);
		Dialog dialog = builder.create();
		dialog.setCancelable(false);
		dialog.show();

	}
}
