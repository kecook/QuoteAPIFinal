package com.example.crudquote;


import java.util.ArrayList;

public class QuoteDataManager {
    ArrayList<QuoteData> listOfQuoteData = new ArrayList<>(0);


    public ArrayList<QuoteData> getListOfQuoteData() {

        //DonationDataBaseClient.ge
        return listOfQuoteData;
    }

    public void addNewQuote(QuoteData q) {
        listOfQuoteData.add(q);// database locally // webservice  // cloud database
    }
}