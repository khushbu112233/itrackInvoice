package com.invoice.aipxperts.Model;

import io.realm.RealmObject;

/**
 * Created by aipxperts-ubuntu-01 on 11/11/17.
 */

public class NumberTaxType extends RealmObject {
    String NumberTaxTypeId;
    String NumberTaxTypeName;
    boolean isChecked=false;
    boolean isDeleted = false;
    public void NumberTaxType()
    {

    }
    public String getNumberTaxTypeId() {
        return NumberTaxTypeId;
    }

    public void setNumberTaxTypeId(String numberTaxTypeId) {
        NumberTaxTypeId = numberTaxTypeId;
    }

    public String getNumberTaxTypeName() {
        return NumberTaxTypeName;
    }

    public void setNumberTaxTypeName(String numberTaxTypeName) {
        NumberTaxTypeName = numberTaxTypeName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
