package com.example.mydemo.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * @author HUNGCV
 *         <p>
 *         Class to be well provided with some quick dialog such as:
 *         <p>
 *         Loading dialog
 *         <p>
 *         ConfirmDialog
 */
public class DialogUtils {

	/**
	 * @param context
	 * @param title
	 * @param message
	 * @param strPositiveButton
	 * @param strNegativeButton
	 * @param onOK
	 * @param onCancel
	 */
	public static void showConfirmDialog(Context context, String title, String message, String strPositiveButton,
			String strNegativeButton, DialogInterface.OnClickListener onOK, DialogInterface.OnClickListener onCancel) {
		showConfirmDialog(context, title, message, strPositiveButton, strNegativeButton, onOK, onCancel, true, null);
	}

	/**
	 * @param context
	 * @param title
	 * @param message
	 * @param onOK
	 * @param onCancel
	 */
	public static void showConfirm_JPDialog(Context context, String title, String message,
			DialogInterface.OnClickListener onOK, DialogInterface.OnClickListener onCancel) {
		showConfirmDialog(context, title, message, "ã?¯ã?„", "ã?„ã?„ã?ˆ", onOK, onCancel, true, null);
	}

	/**
	 * @param context
	 * @param title
	 * @param message
	 * @param strPositiveButton
	 * @param strNegativeButton
	 * @param positiveOnclick
	 * @param negativeOnClick
	 * @param cancelAble
	 * @param cancelListener
	 */
	public static void showConfirmDialog(Context context, String title, String message, String strPositiveButton,
			String strNegativeButton, DialogInterface.OnClickListener positiveOnclick,
			DialogInterface.OnClickListener negativeOnClick, boolean cancelAble,
			DialogInterface.OnCancelListener cancelListener) {
		if (context == null) {
			return;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		if (title != null) {
			builder.setTitle(title);
		}

		builder.setMessage(message);
		if (TextUtils.isEmpty(strPositiveButton)) {
			builder.setPositiveButton("OK", positiveOnclick);
		} else {
			builder.setPositiveButton(strPositiveButton, positiveOnclick);
		}
		if (!TextUtils.isEmpty(strNegativeButton)) {
			builder.setNegativeButton(strNegativeButton, negativeOnClick);
		}
		builder.setCancelable(cancelAble);
		builder.setOnCancelListener(cancelListener);
		builder.create().show();
	}

	/**
	 * @param context
	 * @param message
	 * @param onOK
	 */
	public static void showDismissDialog(Context context, String message, DialogInterface.OnClickListener onOK) {
		showConfirmDialog(context, null, message, null, null, onOK, null, true, null);
	}

	/**
	 * @param context
	 * @param message
	 */
	public static void showDismissDialog(Context context, String message) {
		showConfirmDialog(context, null, message, null, null, null, null, true, null);
	}

	/**
	 * Show dissmiss dialog with cancel listener
	 * 
	 * @param context
	 * @param message
	 * @param onOK
	 * @param onCancelListener
	 */
	public static void showDismissDialog(Context context, String message, DialogInterface.OnClickListener onOK,
			DialogInterface.OnCancelListener onCancelListener) {
		showConfirmDialog(context, null, message, null, null, onOK, null, true, onCancelListener);
	}

	/**
	 * @param context
	 * @param message
	 * @param onOK
	 */
	public static void showDismissDialogCancelUnable(Context context, String message,
			DialogInterface.OnClickListener onOK) {
		showConfirmDialog(context, null, message, null, null, onOK, null, false, null);
	}

	/**
	 * @param context
	 * @return
	 */
	public static Dialog createLoadingDialog(Context context) {
		return new LoadingDialog(context);
	}

	/**
	 * @param context
	 * @param message
	 * @return
	 */
	public static Dialog createLoadingDialog(Context context, String message) {
		return new LoadingDialog(context, message);
	}

	private static class LoadingDialog extends Dialog {

		public LoadingDialog(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(initLayout(context, null));
			final Window window = getWindow();
			window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
			window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
			window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			setCancelable(false);
			setCanceledOnTouchOutside(false);
		}

		public LoadingDialog(Context context, String message) {
			super(context);
			// TODO Auto-generated constructor stub
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(initLayout(context, message));
			final Window window = getWindow();
			window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
			window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
			window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			setCancelable(false);
			setCanceledOnTouchOutside(false);
		}

		View initLayout(Context context, String message) {

			RelativeLayout parent = new RelativeLayout(context);
			parent.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT));
			int backColor = Color.parseColor("#55000000");
			parent.setBackgroundColor(backColor);

			ProgressBar progress = new ProgressBar(context);
			progress.setId(android.R.id.progress);
			LayoutParams paramsProgress = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			paramsProgress.addRule(RelativeLayout.CENTER_IN_PARENT);
			progress.setLayoutParams(paramsProgress);

			TextView tvLoading = new TextView(context);
			LayoutParams paramsTextView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			paramsTextView.addRule(RelativeLayout.CENTER_HORIZONTAL);
			paramsTextView.addRule(RelativeLayout.BELOW, android.R.id.progress);
			paramsTextView.topMargin = 20;
			tvLoading.setLayoutParams(paramsTextView);
			if (message == null) {
				tvLoading.setText("Loading...");
			} else {
				tvLoading.setText(message);
			}
			parent.addView(progress);
			parent.addView(tvLoading);
			return parent;
		}
	}
}
