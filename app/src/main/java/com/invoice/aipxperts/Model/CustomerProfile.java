package com.invoice.aipxperts.Model;

import io.realm.RealmObject;

/**
 * Created by aipxperts-ubuntu-01 on 30/10/17.
 */

public class CustomerProfile extends RealmObject{
    String ClientId;
    String ClientName;
    String ClientEmailId ;
    String ClientPhoneNumber;
    String Address;
    String City;
    String State;
    String Pincode;
    String Country;

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getClientEmailId() {
        return ClientEmailId;
    }

    public void setClientEmailId(String clientEmailId) {
        ClientEmailId = clientEmailId;
    }

    public String getClientPhoneNumber() {
        return ClientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        ClientPhoneNumber = clientPhoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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
}
