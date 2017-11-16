package com.invoice.aipxperts.Fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoice.aipxperts.Adapter.NavDrawerListAdapter;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.databinding.SlidingMenuListBinding;

import java.util.ArrayList;


/**
 * Created by aipxperts-ubuntu-01 on 17/7/17.
 */

public class SlidingMenuFragment extends Fragment {
    SlidingMenuListBinding mBinding;
    View rootView;
    Context context;
    ArrayList<String> navDrawerItems;
    ArrayList<Integer> navDrawerItems_icons;
    String Gender="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.sliding_menu_list, container, false);
        rootView = mBinding.getRoot();
        context = getActivity();
        setdrawer();


        return rootView;
    }
    public void setdrawer()
    {


        navDrawerItems = new ArrayList<String>();
        navDrawerItems.add("Invoice");
        navDrawerItems.add("Company");
        navDrawerItems.add("Customer");
        navDrawerItems.add("Line Items");
        navDrawerItems_icons = new ArrayList<Integer>();
        navDrawerItems_icons.add(R.mipmap.ic_launcher);
        navDrawerItems_icons.add(R.mipmap.ic_launcher);
        navDrawerItems_icons.add(R.mipmap.ic_launcher);
        navDrawerItems_icons.add(R.mipmap.ic_launcher);
        // navDrawerItems_icons.add(R.mipmap.share_event_icon);
        NavDrawerListAdapter adapter = new NavDrawerListAdapter(context,navDrawerItems,navDrawerItems_icons);
        mBinding.listSlidermenu.setAdapter(adapter);
        //  mBinding.listSlidermenu.setOnItemClickListener(new DashBoardActivity.SlideMenuClickListener());

    }

}
