package com.invoice.aipxperts.Fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoice.aipxperts.Adapter.InvoiceExpandableListAdapter;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.databinding.InvoiceListLayoutBinding;

/**
 * Created by aipxperts-ubuntu-01 on 30/10/17.
 */

public class InvoiceListFragment extends Fragment {
    InvoiceListLayoutBinding mBinding;
    View rootView;
    Context context;
    InvoiceExpandableListAdapter invoiceExpandableListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.invoice_list_layout, container, false);
        rootView = mBinding.getRoot();
        context = getActivity();
        invoiceExpandableListAdapter = new InvoiceExpandableListAdapter(context);
        mBinding.lvExp.setAdapter(invoiceExpandableListAdapter);
        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddInvoiceFragment addInvoiceFragment = new AddInvoiceFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_main_container,addInvoiceFragment).addToBackStack(null).commit();
            }
        });
        return rootView;
    }
    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();


        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getActivity().finish();

                        Log.e("test","test");
                        return true;
                    }
                }
                return false;
            }
        });
    }

}
