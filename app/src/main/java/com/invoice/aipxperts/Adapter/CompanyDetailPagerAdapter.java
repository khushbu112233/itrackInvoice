package com.invoice.aipxperts.Adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.invoice.aipxperts.Fragment.CompanyBankDetailFragment;
import com.invoice.aipxperts.Fragment.CompanyDetailFragment;
import com.invoice.aipxperts.Fragment.CompanyTaxDetailFragment;

/**
 * Created by aipxperts-ubuntu-01 on 25/7/17.
 */

public class CompanyDetailPagerAdapter extends FragmentStatePagerAdapter {
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
    public CompanyDetailPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                CompanyDetailFragment tab1 = new CompanyDetailFragment();
                return tab1;
            case 1:
                CompanyTaxDetailFragment tab2 = new CompanyTaxDetailFragment();
                return tab2;
            case 2:
                CompanyBankDetailFragment tab3 = new CompanyBankDetailFragment();
                return tab3;
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
