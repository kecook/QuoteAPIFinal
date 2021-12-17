package com.example.crudquote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NetworkingService.NetworkingListener{
Button quoteButton;


    ArrayList<QuoteData> quoteData = new ArrayList<QuoteData>();
    QuoteDataManager quoteDataManager = new QuoteDataManager();
    QuoteData quoteObj;
    SavedQuoteAdapter adapter;


    NetworkingService networkingService;
    JsonService jsonService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quoteButton = findViewById(R.id.getQuote);


        networkingService = ((myApp)getApplication()).getNetworkingService();
        jsonService = ((myApp)getApplication()).getJsonService();
//        networkingService.fetchQuoteData();//send request to api
//        networkingService.listener = this;


        configureQuoteButton();

    }


        private void configureQuoteButton(){
            quoteButton = findViewById(R.id.getQuote);
            quoteButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,SingleQuoteActivity.class));

                }
            });
        }

    @Override
    public void APINetworkListener(String jsonString) {
        quoteData = jsonService.parseQuoteAPIData(jsonString);
        adapter.recyclerQuoteData = quoteData;
        adapter.notifyDataSetChanged();

    }
}
