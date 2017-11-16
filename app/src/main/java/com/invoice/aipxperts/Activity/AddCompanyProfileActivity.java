package com.invoice.aipxperts.Activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.darsh.multipleimageselect.models.Image;
import com.invoice.aipxperts.Fragment.CompanyDetailFragment;
import com.invoice.aipxperts.Fragment.CompanyDetailMainFragment;
import com.invoice.aipxperts.R;
import com.invoice.aipxperts.Utils.Constants;
import com.invoice.aipxperts.Utils.Pref;
import com.invoice.aipxperts.databinding.ActivityAddCompanyProfileBinding;

import java.util.ArrayList;

/**
 * Created by aipxperts-ubuntu-01 on 30/10/17.
 */

public class AddCompanyProfileActivity extends FragmentActivity {
    Context context;
    public ActivityAddCompanyProfileBinding mBinding;
    String UserId;
    String UserName;
    String UserEmailId;
    String UserPhoneNumber;
    String Address1;
    String Address2;
    String City;
    String State;
    String Pincode;
    String Country;
    String BankName;
    String A_CNo;
    String BranchName;
    String IFSCode;
    String CompanyPAN;
    String TaxNo;
    String GSTNo;
    String imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Constants.StatusBar();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_company_profile);
        Pref.setValue(AddCompanyProfileActivity.this,"CompanyFrom","0");
        CompanyDetailMainFragment companyDetailMainFragment = new CompanyDetailMainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fm2, companyDetailMainFragment).commit();



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);

        Log.e("3333", "" + requestCode);

        if (requestCode == 200 && resultCode == RESULT_OK) {
            if (getCurrentFragment() instanceof CompanyDetailFragment) {

                ((CompanyDetailFragment) getCurrentFragment()).onActivity(data);
            }

        } else if (requestCode == 201 && resultCode == RESULT_OK) {
            if (getCurrentFragment() instanceof CompanyDetailFragment) {


                ArrayList<Image> images = data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
                Log.e("data",""+images);
                ((CompanyDetailFragment) getCurrentFragment()).onActivityGallery(images.get(0));
            }
        }

    }
    public void SetCompanyId(String UserId)
    {
        this.UserId=UserId;
    }
    public String getCompanyId()
    {
        return UserId;
    }
    public void SetCompanyName(String UserName)
    {
        this.UserName=UserName;
    }
    public String getCompanyName()
    {
        return UserName;
    }

    public void SetCompanyEmailId(String UserEmailId)
    {
        this.UserEmailId=UserEmailId;
    }
    public String getCompanyEmailId()
    {
        return UserEmailId;
    }

    public void SetCompanyPhoneNumber(String UserPhoneNumber)
    {
        this.UserPhoneNumber=UserPhoneNumber;
    }
    public String getCompanyPhoneNumber()
    {
        return UserPhoneNumber;
    }

    public void SetCompanyAddress1(String Address1)
    {
        this.Address1=Address1;
    }
    public String getCompanyAddress1()
    {
        return Address1;
    }
    public void SetCompanyAddress2(String Address2)
    {
        this.Address2=Address2;
    }
    public String getCompanyAddress2()
    {
        return Address2;
    }

    public void SetCompanyCity(String City)
    {
        this.City=City;
    }
    public String getCompanyCity()
    {
        return City;
    }

    public void SetCompanyState(String State)
    {
        this.State=State;
    }
    public String getCompanyState()
    {
        return State;
    }

    public void SetCompanyPincode(String Pincode)
    {
        this.Pincode=Pincode;
    }
    public String getCompanyPincode()
    {
        return Pincode;
    }

    public void SetCompanyBankName(String BankName)
    {
        this.BankName=BankName;
    }
    public String getCompanyBankName()
    {
        return BankName;
    }

    public void Seta_cno(String A_CNo)
    {
        this.A_CNo=A_CNo;
    }
    public String geta_cno()
    {
        return A_CNo;
    }

    public void SetBranchName(String BranchName)
    {
        this.BranchName=BranchName;
    }
    public String getBranchName()
    {
        return BranchName;
    }

    public void SetIFSCode(String IFSCode)
    {
        this.IFSCode=IFSCode;
    }
    public String getIFScode()
    {
        return IFSCode;
    }
    public void SetCompanyPAN(String CompanyPAN)
    {
        this.CompanyPAN=CompanyPAN;
    }
    public String getCompanyPAN()
    {
        return CompanyPAN;
    }

    public void SetTaxNo(String TaxNo)
    {
        this.TaxNo=TaxNo;
    }
    public String getTaxNo()
    {
        return TaxNo;
    }

    public void SetGstNo(String GSTNo)
    {
        this.GSTNo=GSTNo;
    }
    public String getGstNo()
    {
        return GSTNo;
    }

    public void SetCountry(String country)
    {
        this.Country=country;
    }
    public String getCountry()
    {
        return Country;
    }

    public String SetImage_Path(String storePath){

        return imagePath;
    }
    public void getImagePath(String imagePath){
        this.imagePath=imagePath;
    }
    public Fragment getCurrentFragment() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fm2);
        Fragment childFragment = currentFragment.getChildFragmentManager().getFragments().get(0);

        Log.e("currentFragment",""+childFragment);

        return childFragment;

    }

}
