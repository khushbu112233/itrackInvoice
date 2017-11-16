package com.invoice.aipxperts.Model;

import io.realm.RealmObject;

/**
 * Created by aipxperts-ubuntu-01 on 30/10/17.
 */

public class CompanyProfile extends RealmObject {

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

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmailId() {
        return UserEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        UserEmailId = userEmailId;
    }

    public String getUserPhoneNumber() {
        return UserPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        UserPhoneNumber = userPhoneNumber;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }
    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getA_CNo() {
        return A_CNo;
    }

    public void setA_CNo(String a_CNo) {
        A_CNo = a_CNo;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getIFSCode() {
        return IFSCode;
    }

    public void setIFSCode(String IFSCode) {
        this.IFSCode = IFSCode;
    }

    public String getCompanyPAN() {
        return CompanyPAN;
    }

    public void setCompanyPAN(String companyPAN) {
        CompanyPAN = companyPAN;
    }

    public String getTaxNo() {
        return TaxNo;
    }

    public void setTaxNo(String taxNo) {
        TaxNo = taxNo;
    }

    public String getGSTNo() {
        return GSTNo;
    }

    public void setGSTNo(String GSTNo) {
        this.GSTNo = GSTNo;
    }
}
