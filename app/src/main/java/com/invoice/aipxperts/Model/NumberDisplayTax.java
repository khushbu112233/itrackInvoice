package com.invoice.aipxperts.Model;

import io.realm.RealmObject;

/**
 * Created by aipxperts-ubuntu-01 on 13/11/17.
 */

public class NumberDisplayTax extends RealmObject {

    String NumberDisplayTaxId;
    String NumberDisplayTaxName;
    String NumberDisplayTaxValue;
    boolean isDelete=false;

    public String getNumberDisplayTaxName() {
        return NumberDisplayTaxName;
    }

    public void setNumberDisplayTaxName(String numberDisplayTaxName) {
        NumberDisplayTaxName = numberDisplayTaxName;
    }

    public String getNumberDisplayTaxId() {
        return NumberDisplayTaxId;
    }

    public void setNumberDisplayTaxId(String numberDisplayTaxId) {
        NumberDisplayTaxId = numberDisplayTaxId;
    }

    public String getNumberDisplayTaxValue() {
        return NumberDisplayTaxValue;
    }

    public void setNumberDisplayTaxValue(String numberDisplayTaxValue) {
        NumberDisplayTaxValue = numberDisplayTaxValue;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
