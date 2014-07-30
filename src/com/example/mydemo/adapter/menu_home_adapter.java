package com.example.mydemo.adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.andengine.engine.handler.runnable.RunnableHandler;

import android.R.bool;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.example.mydemo.R;
import com.example.mydemo.bean.Quan;
import com.example.mydemo.utils.MyTextView;
import com.example.mydemo.utils.ProgressWheel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class menu_home_adapter extends BaseAdapter {

	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	String[] imageUrls;
	Context context;
	List<Quan> listmenu;
	boolean add_layout = false ;

	private Object holder;
	DisplayImageOptions options = new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.ic_stub)
	.showImageForEmptyUri(R.drawable.ic_empty)
	.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
	.cacheOnDisk(true).considerExifParams(true)
	.displayer(new RoundedBitmapDisplayer(20)).build();
	ImageLoader imageloader = ImageLoader.getInstance();

	public menu_home_adapter(Context context, List<Quan> listmenu) {
		super();
		this.context = context;
		this.listmenu = listmenu;
	}

	@Override
	public int getCount() {
		return listmenu.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View v = arg1;
		final ViewHolder holder ;
		Quan quanan = listmenu.get(arg0);
		ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

		if (v == null) {
			LayoutInflater inflate = LayoutInflater.from(context);
			v = inflate.inflate(R.layout.item_list_home_menu, null);
			holder = new ViewHolder();
			//====================

			holder.img = (ImageView) v.findViewById(R.id.img_monan);
			holder.name_mon = (TextView) v.findViewById(R.id.tenmonan);
			holder.ratingbar = (RatingBar) v.findViewById(R.id.ratingbar);
			holder.gia = (MyTextView) v.findViewById(R.id.gia);
			holder.like =(ImageView) v.findViewById(R.id.like);
			holder.progess =(ProgressWheel) v.findViewById(R.id.progressWheelUser);
			holder.check_is = (ImageView) v.findViewById(R.id.check_in);
			holder.diachi = (TextView) v.findViewById(R.id.dia_chi_quan);
			holder.sdt = (TextView) v.findViewById(R.id.sdt_quan);
			holder.vitri = (ImageView) v.findViewById( R.id.vi_tri);
			holder.xem_them = (ImageView) v.findViewById(R.id.Go_quan);
			holder.remove_item = (ImageView) v.findViewById(R.id.remove_item_use);
			holder.hien = (ImageView) v.findViewById(R.id.shipping);
			holder.relayout =(RelativeLayout) v.findViewById(R.id.footer);

			//=====================
			v.setTag(holder);
		}else{
			holder = (ViewHolder) v .getTag();
		}

//		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
//				holder.relayout.getWidth(), holder.relayout.getHeight());
//		holder.relayout.setLayoutParams(lp);
		if(!add_layout)
			holder.relayout.setVisibility(View.GONE);

		holder.ratingbar.setRating(4.6f);
		holder.name_mon.setText(quanan.getName_mon());
		holder.gia.setText(quanan.getGia() + " K");
		holder.diachi.setText(quanan.getDiachi());
		holder.sdt.setText(quanan.getSdt().toString());


		imageloader.displayImage("file://" + quanan.getImg_mon(), holder.img,
				options, animateFirstListener);

		holder.like.setOnClickListener(onclick); 
		holder.like.setTag(arg0);
		holder.check_is.setOnClickListener(onclick);
		holder.check_is.setTag(arg0);
		holder.vitri.setOnClickListener(onclick);
		holder.vitri.setTag(arg0);
		holder.xem_them.setOnClickListener(onclick);
		holder.xem_them.setTag(arg0);
		holder.remove_item.setOnClickListener(onclick);
		holder.remove_item.setTag(arg0);
		//		holder.hien.setOnClickListener(onclick);
		//		holder.hien.setTag(arg0);
		holder.hien.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(holder.relayout.getVisibility() == View.GONE){
				Toast.makeText(context, "ok rôi", Toast.LENGTH_SHORT).show();
				holder.relayout.setVisibility(View.VISIBLE);
				}
			}
		});

		holder.progess.setProgress(90);
		holder.progess.setText("09:65");



		return v;
	}

	private OnClickListener onclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			final int position = (Integer) v.getTag();
			Thread thread = null;
			switch (v.getId()) {
			case R.id.like:
				Toast.makeText(context, "1111", Toast.LENGTH_SHORT).show();
				break;
			case R.id.check_in:
				Toast.makeText(context, "a222", Toast.LENGTH_SHORT).show();
				break;
			case R.id.Go_quan:
				Toast.makeText(context, "333", Toast.LENGTH_SHORT).show();
				break;
			case R.id.vi_tri:
				Toast.makeText(context, "444", Toast.LENGTH_SHORT).show();
				break;
			case R.id.remove_item_use:
				Toast.makeText(context, "abc", Toast.LENGTH_SHORT).show();
				//				listmenu.remove(position);
				break;

			default:
				break;
			}

		}
	};


}
class ViewHolder {
	TextView name_mon;
	ImageView img;
	ImageView like ;
	ProgressWheel progess;
	ImageView check_is ;
	TextView diachi;
	TextView sdt ;
	ImageView vitri;
	ImageView xem_them;
	RatingBar ratingbar;
	MyTextView gia ;
	ImageView remove_item;
	ImageView hien ;
	RelativeLayout relayout;
}



class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

	static final List<String> displayedImages = Collections
			.synchronizedList(new LinkedList<String>());

	@Override
	public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
		if (loadedImage != null) {
			ImageView imageView = (ImageView) view;
			boolean firstDisplay = !displayedImages.contains(imageUri);
			if (firstDisplay) {
				FadeInBitmapDisplayer.animate(imageView, 500);
				displayedImages.add(imageUri);
			}
		}
	}

}
