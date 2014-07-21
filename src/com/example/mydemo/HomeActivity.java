package com.example.mydemo;

import com.example.com.example.mydemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeActivity extends Activity{

	TextView txt_bar;
	ImageView img_bar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		
		txt_bar = (TextView) findViewById(R.id.textb_bar);
		img_bar = (ImageView) findViewById(R.id.imagebar1);
		final Animation animScale = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_scale_in);
		
		AnimationListener animationlist = new AnimationListener() {
			
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
				txt_bar.setText("myHome");
			}
		};
		animScale.setAnimationListener(animationlist);
		img_bar.setAnimation(animScale);
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
