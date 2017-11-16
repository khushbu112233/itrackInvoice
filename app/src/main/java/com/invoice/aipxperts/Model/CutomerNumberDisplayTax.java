package com.invoice.aipxperts.Model;

import io.realm.RealmObject;

/**
 * Created by aipxperts-ubuntu-01 on 13/11/17.
 */

public class CutomerNumberDisplayTax extends RealmObject {

    String CutomerNumberDisplayTaxId;
    String CutomerNumberDisplayTaxName;
    String CutomerNumberDisplayTaxValue;
    boolean CutomerisDelete=false;

    public String getCutomerNumberDisplayTaxId() {
        return CutomerNumberDisplayTaxId;
    }

    public void setCutomerNumberDisplayTaxId(String cutomerNumberDisplayTaxId) {
        CutomerNumberDisplayTaxId = cutomerNumberDisplayTaxId;
    }

    public String getCutomerNumberDisplayTaxName() {
        return CutomerNumberDisplayTaxName;
    }

    public void setCutomerNumberDisplayTaxName(String cutomerNumberDisplayTaxName) {
        CutomerNumberDisplayTaxName = cutomerNumberDisplayTaxName;
    }

    public String getCutomerNumberDisplayTaxValue() {
        return CutomerNumberDisplayTaxValue;
    }

    public void setCutomerNumberDisplayTaxValue(String cutomerNumberDisplayTaxValue) {
        CutomerNumberDisplayTaxValue = cutomerNumberDisplayTaxValue;
    }

    public boolean isCutomerisDelete() {
        return CutomerisDelete;
    }

    public void setCutomerisDelete(boolean cutomerisDelete) {
        CutomerisDelete = cutomerisDelete;
    }
}
