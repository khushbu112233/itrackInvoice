package com.invoice.aipxperts.Fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoice.aipxperts.Activity.MainActivity;
import com.invoice.aipxperts.Adapter.CompanyDetailPagerAdapter;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.Utils.Pref;
import com.invoice.aipxperts.databinding.CompanyDetailMainLayoutBinding;

/**
 * Created by aipxperts-ubuntu-01 on 14/11/17.
 */

public class CompanyDetailMainFragment extends Fragment {
    public static CompanyDetailMainLayoutBinding mBinding;
    View rootView;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.company_detail_main_layout, container, false);
        rootView = mBinding.getRoot();

        if(Pref.getValue(context,"CompanyFrom","").equalsIgnoreCase("1"))
        {
          mBinding.llTitle.setVisibility(View.GONE);
            preview();
        }else
        {
            mBinding.llTitle.setVisibility(View.VISIBLE);
        }
        final CompanyDetailPagerAdapter adapter = new CompanyDetailPagerAdapter
                (getChildFragmentManager(), 3);
        mBinding.viewPager1.setAdapter(adapter);

        mBinding.viewPager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    mBinding.txtCompanyProfile.setTextColor(getResources().getColor(R.color.white));
                    mBinding.txtTaxProfile.setTextColor(getResources().getColor(R.color.un_select_header));
                    mBinding.txtCompanyBank.setTextColor(getResources().getColor(R.color.un_select_header));
                    mBinding.imgCompanyProfile.setImageResource(R.mipmap.company_profile);
                    mBinding.imgTaxProfile.setImageResource(R.mipmap.tax_num_select);
                    mBinding.imgCompanyBank.setImageResource(R.mipmap.bank_unselect);
                    mBinding.img1.setVisibility(View.VISIBLE);
                    mBinding.img2.setVisibility(View.GONE);
                    mBinding.img3.setVisibility(View.GONE);

                    //  mBinding.txtPast.setTextColor(getResources().getColor(R.color.un_select_header));

                } else if (position == 1) {
                    mBinding.txtCompanyProfile.setTextColor(getResources().getColor(R.color.un_select_header));
                    mBinding.txtTaxProfile.setTextColor(getResources().getColor(R.color.white));
                    mBinding.txtCompanyBank.setTextColor(getResources().getColor(R.color.un_select_header));
                    mBinding.imgCompanyProfile.setImageResource(R.mipmap.company_unselected);
                    mBinding.imgTaxProfile.setImageResource(R.mipmap.tax_num_unselect);
                    mBinding.imgCompanyBank.setImageResource(R.mipmap.bank_unselect);
                    mBinding.img1.setVisibility(View.GONE);
                    mBinding.img2.setVisibility(View.VISIBLE);
                    mBinding.img3.setVisibility(View.GONE);
                    //  mBinding.txtPast.setTextColor(getResources().getColor(R.color.un_select_header));

                } else if (position == 2) {
                    mBinding.txtCompanyProfile.setTextColor(getResources().getColor(R.color.un_select_header));
                    mBinding.txtTaxProfile.setTextColor(getResources().getColor(R.color.un_select_header));
                    mBinding.txtCompanyBank.setTextColor(getResources().getColor(R.color.white));
                    mBinding.imgCompanyProfile.setImageResource(R.mipmap.company_unselected);
                    mBinding.imgTaxProfile.setImageResource(R.mipmap.tax_num_select);
                    mBinding.imgCompanyBank.setImageResource(R.mipmap.bank_select);
                    mBinding.img1.setVisibility(View.GONE);
                    mBinding.img2.setVisibility(View.GONE);
                    mBinding.img3.setVisibility(View.VISIBLE);
                    //  mBinding.txtPast.setTextColor(getResources().getColor(R.color.white));

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBinding.txtCompanyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBinding.viewPager1.setCurrentItem(0);
                /*ListEventRecentFragment fragment = new ListEventRecentFragment();
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_main_container, fragment).commit();
*/

            }
        });
        mBinding.txtTaxProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBinding.viewPager1.setCurrentItem(1);
                /*
                ListEventFutureFragment fragment = new ListEventFutureFragment();
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_main_container, fragment).commit();*/

            }
        });
        mBinding.txtCompanyBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBinding.viewPager1.setCurrentItem(2);
                /*  ListEventPastFragment fragment = new ListEventPastFragment();
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_main_container, fragment).commit();*/


            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    public void setPosition(int pos) {
        mBinding.viewPager1.setCurrentItem(pos);
    }
    public Fragment getCurrentFragment() {
        Fragment currentFragment = getChildFragmentManager().findFragmentById(R.id.fm_main);
        //    Log.e("currentFragment",""+currentFragment);
        return currentFragment;

    }
    public void preview() {
        ((MainActivity)context).mBinding.includeHeader.txtTitle.setText("Company Profile");
        ((MainActivity)context).mBinding.includeHeader.imgDrawer.setImageResource(R.mipmap.menu_button);
        ((MainActivity)context).mBinding.includeHeader.imgOptionMenu.setVisibility(View.GONE);

    }
}
