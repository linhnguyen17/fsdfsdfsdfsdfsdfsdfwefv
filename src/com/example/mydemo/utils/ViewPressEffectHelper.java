package com.example.mydemo.utils;

import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

/**
 * 
 * View Press Effect Helper - usage : do some simple press effect like iOS
 * 
 * Simple Usage: ImageView img = (ImageView) findViewById(R.id.img);
 * ViewPressEffectHelper.attach(img)
 * 
 * @author Lam @ HongKong
 * 
 */
public class ViewPressEffectHelper {

	public static void attach(View view) {
		view.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					if (Build.VERSION.SDK_INT < 11) {
						final AlphaAnimation animation = new AlphaAnimation(1.0f, 0.5f);
						animation.setDuration(100);
						animation.setFillAfter(true);
						v.startAnimation(animation);
					} else
						v.setAlpha(0.5f);
				}
					break;

				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_CANCEL: {
					if (Build.VERSION.SDK_INT < 11) {
						final AlphaAnimation animation = new AlphaAnimation(0.5f, 1.0f);
						animation.setDuration(100);
						animation.setFillAfter(true);
						v.startAnimation(animation);
					} else
						v.setAlpha(1.0f);
				}
					break;
				}

				return false;
			}
		});
	}

	/**
	 * setup imageview click effect
	 * 
	 * @param button
	 */
	public static void addPressStateToView(ImageView button) {
		button.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getX() > v.getWidth() || event.getY() > v.getHeight()) {
					((ImageView) v).clearColorFilter();
					v.invalidate();
				}
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					((ImageView) v).setColorFilter(Color.parseColor("#882d2d2f"), Mode.MULTIPLY);
					v.invalidate();
					// 0xf0f47521 good
					break;

				case MotionEvent.ACTION_UP:
					((ImageView) v).clearColorFilter();
					v.invalidate();
					break;
				case MotionEvent.ACTION_MOVE:
					if (event.getX() > v.getWidth() || event.getY() > v.getHeight()) {
						((ImageView) v).clearColorFilter();
						v.invalidate();
					}
					break;
				case MotionEvent.ACTION_CANCEL:
					((ImageView) v).clearColorFilter();
					v.invalidate();
					break;
				}

				return false;
			}
		});
	}

	/**
	 * setup imageview click effect
	 * 
	 * @param button
	 */
	public static void addPressStateToView(ImageView button, final int color) {
		button.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getX() > v.getWidth() || event.getY() > v.getHeight()) {
					((ImageView) v).clearColorFilter();
					v.invalidate();
				}
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					((ImageView) v).setColorFilter(color, Mode.MULTIPLY);
					v.invalidate();
					break;

				case MotionEvent.ACTION_UP:
					((ImageView) v).clearColorFilter();
					v.invalidate();
					break;
				case MotionEvent.ACTION_MOVE:
					if (event.getX() > v.getWidth() || event.getY() > v.getHeight()) {
						((ImageView) v).clearColorFilter();
						v.invalidate();
					}
					break;
				case MotionEvent.ACTION_CANCEL:
					((ImageView) v).clearColorFilter();
					v.invalidate();
					break;
				}

				return false;
			}
		});
	}
}
