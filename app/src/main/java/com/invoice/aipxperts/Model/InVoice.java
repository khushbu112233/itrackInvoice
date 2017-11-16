package com.invoice.aipxperts.Model;

import io.realm.RealmObject;

/**
 * Created by aipxperts-ubuntu-01 on 30/10/17.
 */

public class InVoice extends RealmObject {
    String InvoiceId;
    String InvoiceDate;
    String AddCustomer;
    String AddItem;
    String Price;
    String Tax;
    String Note;
    String Photo;

    public String getInvoiceId() {
        return InvoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        InvoiceId = invoiceId;
    }

    public String getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        InvoiceDate = invoiceDate;
    }

    public String getAddCustomer() {
        return AddCustomer;
    }

    public void setAddCustomer(String addCustomer) {
        AddCustomer = addCustomer;
    }

    public String getAddItem() {
        return AddItem;
    }

    public void setAddItem(String addItem) {
        AddItem = addItem;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTax() {
        return Tax;
    }

    public void setTax(String tax) {
        Tax = tax;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}
