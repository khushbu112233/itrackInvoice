package com.invoice.aipxperts.Adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.invoice.aipxperts.Fragment.CustomerTaxDetailFragment;
import com.invoice.aipxperts.Fragment.CustomerprofileDetailFragment;

/**
 * Created by aipxperts-ubuntu-01 on 25/7/17.
 */

public class CustomerDetailPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private Fragment mCurrentFragment;

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }
    //...
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            mCurrentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }
    public CustomerDetailPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                CustomerprofileDetailFragment tab1 = new CustomerprofileDetailFragment();
                return tab1;
            case 1:
                CustomerTaxDetailFragment tab2 = new CustomerTaxDetailFragment();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
        //do nothing here! no call to super.restoreState(arg0, arg1);
    }
}
