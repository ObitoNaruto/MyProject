package com.mobile.android.home.util;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinming.xxm on 2015/12/2.
 */
public class FragmentHolder {
	List<Fragment> mFragmentList;

	public FragmentHolder(){
		mFragmentList = new ArrayList<>();
	}

	public void addFragment(String name, Fragment fragment){
			mFragmentList.add(fragment);
	}

	public List<Fragment> getFragmenList() {
		return mFragmentList;
	}

}
