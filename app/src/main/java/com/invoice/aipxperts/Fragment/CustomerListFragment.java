package com.invoice.aipxperts.Fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoice.aipxperts.Activity.MainActivity;
import com.invoice.aipxperts.Adapter.CustomerListAdapter;
import com.invoice.aipxperts.Model.CustomerProfile;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.databinding.CustomerListLayoutBinding;

import java.util.ArrayList;


/**
 * Created by aipxperts-ubuntu-01 on 15/11/17.
 */

public class CustomerListFragment extends Fragment{
    View rootView;
    Context context;
    CustomerListLayoutBinding mBinding;
    CustomerListAdapter customerListAdapter;
    ArrayList<CustomerProfile> customerProfiles = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.customer_list_layout, container, false);
        rootView = mBinding.getRoot();
        context = getActivity();
        preview();


        customerListAdapter=new CustomerListAdapter(context,customerProfiles);
        mBinding.customerLst.setAdapter(customerListAdapter);
        Log.e("customer","test");
        return rootView;
    }

    public void preview() {
        ((MainActivity)context).mBinding.includeHeader.txtTitle.setText("Customer List");
        ((MainActivity)context).mBinding.includeHeader.imgDrawer.setImageResource(R.mipmap.menu_button);
        ((MainActivity)context).mBinding.includeHeader.imgOptionMenu.setVisibility(View.VISIBLE);
        ((MainActivity)context).mBinding.includeHeader.imgOptionMenu.setImageResource(R.mipmap.plus_main);
        ((MainActivity)context).mBinding.includeHeader.imgOptionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerFragment customerFragment = new CustomerFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_main_container,customerFragment).addToBackStack(null).commit();
            }
        });
    }
}
