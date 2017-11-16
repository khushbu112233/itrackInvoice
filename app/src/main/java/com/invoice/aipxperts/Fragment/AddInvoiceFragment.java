package com.invoice.aipxperts.Fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoice.aipxperts.Activity.MainActivity;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.databinding.AddInvoiceLayoutBinding;

/**
 * Created by aipxperts-ubuntu-01 on 16/11/17.
 */

public class AddInvoiceFragment extends Fragment {
    AddInvoiceLayoutBinding mBinding;
    View rootView;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.add_invoice_layout, container, false);
        rootView = mBinding.getRoot();
        context = getActivity();
        preview();
        mBinding.relLineItemsSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignatureNotesFragment signatureNotesFragment= new SignatureNotesFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_main_container,signatureNotesFragment).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

    private void preview() {
        ((MainActivity)context).mBinding.includeHeader.txtTitle.setText("Add Invoices");
        ((MainActivity)context).mBinding.includeHeader.imgDrawer.setImageResource(R.mipmap.menu_button);
        ((MainActivity)context).mBinding.includeHeader.imgOptionMenu.setVisibility(View.GONE);
    }
}
