package com.invoice.aipxperts.Fragment;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoice.aipxperts.Activity.AddCompanyProfileActivity;
import com.invoice.aipxperts.Activity.MainActivity;
import com.invoice.aipxperts.Model.CompanyProfile;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.Utils.JavaEncryption;
import com.invoice.aipxperts.Utils.Pref;
import com.invoice.aipxperts.databinding.CompanyBankDetailFragmentLayoutBinding;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import io.realm.Realm;

import static com.invoice.aipxperts.Activity.BaseActivity.realm;

/**
 * Created by aipxperts-ubuntu-01 on 30/10/17.
 */

public class CompanyBankDetailFragment extends Fragment {
    CompanyBankDetailFragmentLayoutBinding mBinding;
    Context context;
    View rootView;
    private static String secretKey = "bankDetail";
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.company_bank_detail_fragment_layout, container, false);
        rootView = mBinding.getRoot();
        context = getActivity();

        mBinding.txtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JavaEncryption encryptionClassObject = new JavaEncryption();

                try {
                    ((AddCompanyProfileActivity)context).SetCompanyBankName(mBinding.edtBankName.getText().toString());
                    ((AddCompanyProfileActivity)context).Seta_cno(encryptionClassObject.encrypt(mBinding.edtAccountNumber.getText().toString(),secretKey));
                    ((AddCompanyProfileActivity)context).SetBranchName(mBinding.edtBranchName.getText().toString());
                    ((AddCompanyProfileActivity)context).SetIFSCode(encryptionClassObject.encrypt(mBinding.edtIFCCode.getText().toString(),secretKey));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                }


                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Long tsLong = System.currentTimeMillis() / 1000;
                        String ts = tsLong.toString();
                        // Add a Category
                        CompanyProfile companyProfile = realm.createObject(CompanyProfile.class);

                        companyProfile.setUserId(((AddCompanyProfileActivity)context).getCompanyId());
                        companyProfile.setUserName(((AddCompanyProfileActivity)context).getCompanyName());
                        companyProfile.setUserEmailId(((AddCompanyProfileActivity)context).getCompanyEmailId());
                        companyProfile.setUserPhoneNumber(((AddCompanyProfileActivity)context).getCompanyPhoneNumber());
                        companyProfile.setAddress1(((AddCompanyProfileActivity)context).getCompanyAddress1());
                        companyProfile.setAddress2(((AddCompanyProfileActivity)context).getCompanyAddress2());
                        companyProfile.setCity(((AddCompanyProfileActivity)context).getCompanyCity());
                        companyProfile.setState(((AddCompanyProfileActivity)context).getCompanyState());
                        companyProfile.setPincode(((AddCompanyProfileActivity)context).getCompanyPincode());
                        companyProfile.setCountry(((AddCompanyProfileActivity)context).getCountry());
                        companyProfile.setBranchName(((AddCompanyProfileActivity)context).getBranchName());
                        companyProfile.setA_CNo(((AddCompanyProfileActivity)context).geta_cno());
                        companyProfile.setBankName(((AddCompanyProfileActivity)context).getCompanyBankName());
                        companyProfile.setIFSCode(((AddCompanyProfileActivity)context).getIFScode());
                        companyProfile.setCompanyPAN(((AddCompanyProfileActivity)context).getCompanyPAN());
                        companyProfile.setTaxNo(((AddCompanyProfileActivity)context).getTaxNo());
                        companyProfile.setGSTNo(((AddCompanyProfileActivity)context).getGstNo());


                    }
                });

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                Pref.setValue(context,"first","1");
                ((FragmentActivity)context).finish();


            }
        });


        return rootView;
    }
}
