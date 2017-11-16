package com.invoice.aipxperts.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.invoice.aipxperts.R;
import com.invoice.aipxperts.databinding.InvoiceListChildLayoutBinding;
import com.invoice.aipxperts.databinding.InvoiceListGroupLayoutBinding;

/**
 * Created by aipxperts-ubuntu-01 on 16/11/17.
 */

public class InvoiceExpandableListAdapter extends BaseExpandableListAdapter {
    Context context;
    public InvoiceExpandableListAdapter(Context context)
    {
        this.context = context;

    }
    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int i) {
        return 2;
    }

    @Override
    public Object getGroup(int i) {
        return i;
    }

    @Override
    public Object getChild(int i, int i1) {
        return i;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup parent) {
        final InvoiceListGroupLayoutBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.invoice_list_group_layout, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (InvoiceListGroupLayoutBinding) convertView.getTag();
        }
//        binding.txtCustomerName.setText(customerProfiles.get(i).getClientName());
        return convertView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {
        final InvoiceListChildLayoutBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.invoice_list_child_layout, viewGroup, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (InvoiceListChildLayoutBinding) convertView.getTag();
        }
//        binding.txtCustomerName.setText(customerProfiles.get(i).getClientName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
