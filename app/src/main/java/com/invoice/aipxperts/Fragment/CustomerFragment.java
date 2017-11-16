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
import com.invoice.aipxperts.Adapter.CustomerDetailPagerAdapter;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.databinding.CustomerDetailMainLayoutBinding;


/**
 * Created by aipxperts-ubuntu-01 on 15/11/17.
 */

public class CustomerFragment extends Fragment {
    CustomerDetailMainLayoutBinding mBinding;
    View rootView;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.customer_detail_main_layout, container, false);
        rootView = mBinding.getRoot();
        preview();
        final CustomerDetailPagerAdapter adapter = new CustomerDetailPagerAdapter
                (getChildFragmentManager(), 2);
        mBinding.viewPagerCustomer.setAdapter(adapter);

        mBinding.viewPagerCustomer.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    mBinding.txtCustomerProfile.setTextColor(getResources().getColor(R.color.white));
                    mBinding.txtTaxProfile.setTextColor(getResources().getColor(R.color.un_select_header));
                    mBinding.imgCustomerProfile.setImageResource(R.mipmap.company_profile);
                    mBinding.imgTaxProfile.setImageResource(R.mipmap.tax_num_select);
                    mBinding.img1.setVisibility(View.VISIBLE);
                    mBinding.img2.setVisibility(View.GONE);

                    //  mBinding.txtPast.setTextColor(getResources().getColor(R.color.un_select_header));

                } else if (position == 1) {
                    mBinding.txtCustomerProfile.setTextColor(getResources().getColor(R.color.un_select_header));
                    mBinding.txtTaxProfile.setTextColor(getResources().getColor(R.color.white));
                    mBinding.imgCustomerProfile.setImageResource(R.mipmap.company_unselected);
                    mBinding.imgTaxProfile.setImageResource(R.mipmap.tax_num_unselect);
                    mBinding.img1.setVisibility(View.GONE);
                    mBinding.img2.setVisibility(View.VISIBLE);
                    //  mBinding.txtPast.setTextColor(getResources().getColor(R.color.un_select_header));

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBinding.txtCustomerProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBinding.viewPagerCustomer.setCurrentItem(0);
                /*ListEventRecentFragment fragment = new ListEventRecentFragment();
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_main_container, fragment).commit();
*/

            }
        });
        mBinding.txtTaxProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBinding.viewPagerCustomer.setCurrentItem(1);
                /*
                ListEventFutureFragment fragment = new ListEventFutureFragment();
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
        mBinding.viewPagerCustomer.setCurrentItem(pos);
    }
    public Fragment getCurrentFragment() {
        Fragment currentFragment = getChildFragmentManager().findFragmentById(R.id.fm_main);
        //    Log.e("currentFragment",""+currentFragment);
        return currentFragment;

    }
    public void preview() {
        ((MainActivity)context).mBinding.includeHeader.txtTitle.setText("Customer Profile");
        ((MainActivity)context).mBinding.includeHeader.imgDrawer.setImageResource(R.mipmap.menu_button);
        ((MainActivity)context).mBinding.includeHeader.imgOptionMenu.setVisibility(View.GONE);

    }
}
