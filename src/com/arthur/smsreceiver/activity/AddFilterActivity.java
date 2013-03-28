package com.arthur.smsreceiver.activity;

import java.util.ArrayList;
import java.util.List;

import com.arthur.smsreceiver.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

public class AddFilterActivity extends Activity {

	private ViewPager awesomePager;

	private AwesomePagerAdapter awesomeAdapter;

	private LayoutInflater mInflater;
	private List<View> mListViews;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		awesomeAdapter = new AwesomePagerAdapter();
		awesomePager = (ViewPager) findViewById(R.id.viewpaper);
		awesomePager.setAdapter(awesomeAdapter);

		mListViews = new ArrayList<View>();
		mInflater = getLayoutInflater();
		mListViews.add(mInflater.inflate(R.layout.activity_addfilter, null));
		mListViews.add(mInflater.inflate(R.layout.activity_listfilter, null));

	}

	public class AwesomePagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View collection, int position) {

			((ViewPager) collection).addView(mListViews.get(position), 0);

			return mListViews.get(position);
		}

		@Override
		public void destroyItem(View collection, int position, Object view) {
			((ViewPager) collection).removeView(mListViews.get(position));
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == (object);
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}

	}

}
