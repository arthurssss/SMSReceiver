package com.arthur.smsreceiver.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

public class YZViewPagerAdapter extends PagerAdapter {

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}

}
