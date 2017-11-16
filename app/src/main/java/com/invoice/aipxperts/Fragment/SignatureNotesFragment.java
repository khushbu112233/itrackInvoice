package com.invoice.aipxperts.Fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.invoice.aipxperts.Activity.MainActivity;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.databinding.SignatureNotesLayoutBinding;

/**
 * Created by aipxperts-ubuntu-01 on 16/11/17.
 */

public class SignatureNotesFragment extends Fragment {
    SignatureNotesLayoutBinding mBinding;
    View rootView;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.signature_notes_layout, container, false);
        rootView = mBinding.getRoot();
        context = getActivity();
        preview();
        mBinding.signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {

            }

            @Override
            public void onClear() {

            }
        });
        mBinding.txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("sign",""+mBinding.signaturePad.getSignatureBitmap());

            }
        });
        return rootView;
    }

    private void preview() {
        ((MainActivity)context).mBinding.includeHeader.txtTitle.setText("Signature/Notes");
        ((MainActivity)context).mBinding.includeHeader.imgDrawer.setImageResource(R.mipmap.menu_button);
        ((MainActivity)context).mBinding.includeHeader.imgOptionMenu.setVisibility(View.GONE);
    }
}
