package com.example.crudquote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {
    public ArrayList<QuoteData> parseQuoteAPIData(String jsonQuotesString) {

        ArrayList<QuoteData> quotesFromAPI = new ArrayList<>(0);


        try {
            JSONArray jsonArray = new JSONArray(jsonQuotesString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject quoteObj = jsonArray.getJSONObject(i);
                String q = quoteObj.getString("q");
                String a = quoteObj.getString("a");

                quotesFromAPI.add(new QuoteData(q, a));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return quotesFromAPI;
    }

}


