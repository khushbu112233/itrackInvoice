package com.invoice.aipxperts.Model;

import io.realm.RealmObject;

/**
 * Created by aipxperts-ubuntu-01 on 11/11/17.
 */

public class CustomerNumberTaxType extends RealmObject {
    String CutomerNumberTaxTypeId;
    String CutomerNumberTaxTypeName;
    boolean CutomerisChecked=false;
    boolean CutomerisDeleted = false;
    public void CustomerNumberTaxType()
    {

    }

    public String getCutomerNumberTaxTypeId() {
        return CutomerNumberTaxTypeId;
    }

    public void setCutomerNumberTaxTypeId(String cutomerNumberTaxTypeId) {
        CutomerNumberTaxTypeId = cutomerNumberTaxTypeId;
    }

    public String getCutomerNumberTaxTypeName() {
        return CutomerNumberTaxTypeName;
    }

    public void setCutomerNumberTaxTypeName(String cutomerNumberTaxTypeName) {
        CutomerNumberTaxTypeName = cutomerNumberTaxTypeName;
    }

    public boolean isCutomerisChecked() {
        return CutomerisChecked;
    }

    public void setCutomerisChecked(boolean cutomerisChecked) {
        CutomerisChecked = cutomerisChecked;
    }

    public boolean isCutomerisDeleted() {
        return CutomerisDeleted;
    }

    public void setCutomerisDeleted(boolean cutomerisDeleted) {
        CutomerisDeleted = cutomerisDeleted;
    }
}
