package com.example.mydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.example.mydemo.R;

public class menu_main_adapter extends ArrayAdapter<String>{

	String[] abc;
	public menu_main_adapter(Context context, int resource, String[] abc) {
		super(context, resource, abc);
		// TODO Auto-generated constructor stub
		this.abc = abc;
	}


	@Override
	public View getView(int position , View converview, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View v = converview;
		if(v==null){
			LayoutInflater inflater = LayoutInflater.from(getContext());
			v = inflater.inflate(R.layout.item_list_main, null);
		}

		ImageView img_menu = (ImageView) v.findViewById(R.id.img_menu);
		TextView  txt_menu = (TextView) v.findViewById(R.id.txt_menu);


		txt_menu.setText(abc[position]);
		switch (position) {
		case 0:
			img_menu.setImageResource(R.drawable.ic_cooking);
			break;
		case 1:
			img_menu.setImageResource(R.drawable.ic_cooking);
			break;
		case 2:
			img_menu.setImageResource(R.drawable.ic_launcher);
			break;
		case 3:
			img_menu.setImageResource(R.drawable.ic_cooking);
			break;
		case 4:
			img_menu.setImageResource(R.drawable.header_bg);
			break;
		case 5:
			img_menu.setImageResource(R.drawable.ab_background);
			break;
//		case 6:
//			img_menu.setImageResource(R.drawable.ab_background);
//			break;
//		case 7:
//			img_menu.setImageResource(R.drawable.ab_background);
//			break;
			
		default:
			break;
		}

		

		return v;
	}



}
