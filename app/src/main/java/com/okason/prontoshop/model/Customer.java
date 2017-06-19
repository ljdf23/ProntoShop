package com.okason.prontoshop.model;

/**
 * Created by Gerardo on 18/06/2017.
 */

public class Customer {
    private long id;
    private String customerName;
    private String emailAddress;
    private String phoneNumber;
    private String profileImagePath;
    private String streetAddress;
    private String streetAddress2;
    private String city;
    private String state;
    private String postalCode;
    private String note;
    private long dateAdded;
    private long dateOfLastTransaction;

    public Customer(){
        id=0;
        customerName = "";
        emailAddress = "";
        phoneNumber = "";
        profileImagePath = "empty";
        streetAddress ="";
        streetAddress2 ="";
        city = "";
        state = "";
        postalCode = "";
        note = "";
        dateAdded = 0L;
        dateOfLastTransaction = 0L;
    }
}
