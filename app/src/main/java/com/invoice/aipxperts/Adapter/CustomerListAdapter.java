package com.invoice.aipxperts.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.invoice.aipxperts.Model.CustomerProfile;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.databinding.CutomerListAdaterLayoutBinding;

import java.util.ArrayList;

/**
 * Created by aipxperts-ubuntu-01 on 15/11/17.
 */

public class CustomerListAdapter extends BaseAdapter {
    Context context;
    ArrayList<CustomerProfile> customerProfiles;

    public  CustomerListAdapter(Context context,ArrayList<CustomerProfile> customerProfiles){
        this.context=context;
        this.customerProfiles = customerProfiles;

    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        final CutomerListAdaterLayoutBinding  binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.cutomer_list_adater_layout, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (CutomerListAdaterLayoutBinding) convertView.getTag();
        }
//        binding.txtCustomerName.setText(customerProfiles.get(i).getClientName());
        return convertView;
    }
}
