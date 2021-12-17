package com.example.crudquote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class SingleQuoteActivity extends AppCompatActivity implements NetworkingService.NetworkingListener{
    static QuoteDataManager quoteDataManager = new QuoteDataManager();
    QuoteData quoteDataObject;
    NetworkingService networkingService;
    JsonService jsonService;
    TextView quoteText;
    TextView quoteAuthor;
    Button saveQuoteBtn;
    Button backBtn;
    ArrayList<QuoteData> quoteData = new ArrayList<>(0);
    ArrayList<QuoteData> quoteDataDB = new ArrayList<>(0);
    String qu;
    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_single_quote);
       quoteText = findViewById(R.id.quote);
//       quoteText.setText();
        quoteAuthor =findViewById(R.id.author);
        saveQuoteBtn = findViewById(R.id.saveQuote);

        quoteDataObject = new QuoteData();

        networkingService = ((myApp)getApplication()).getNetworkingService();
        jsonService = ((myApp)getApplication()).getJsonService();
        networkingService.listener = this;
        networkingService.fetchQuoteData();
        configureBackButton();


//        configureSaveButton();


    }

    private void configureBackButton() {
        Button back_btn = (Button) findViewById(R.id.backBtn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void configureSaveButton(ArrayList<QuoteData> selectedQuote) {
        saveQuoteBtn = findViewById(R.id.saveQuote);
        saveQuoteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleQuoteActivity.this,SavedQuoteActivity.class);
                intent.putExtra("SelectedQuote",selectedQuote.get(0).q);
                startActivity(intent);
                quoteDataObject.setQ(selectedQuote.get(0).q);
                quoteDataObject.setA(selectedQuote.get(0).a);
                quoteDataManager.addNewQuote(quoteDataObject);
                Toast.makeText(SingleQuoteActivity.this,"hello",Toast.LENGTH_LONG).show();
                quoteDataDB=quoteDataManager.getListOfQuoteData();
                //call above method in fav
                System.out.println("fromDB"+quoteDataDB.get(0).q);
            }
        });
    }

        @Override
    public void APINetworkListener(String jsonString) {
        quoteData = jsonService.parseQuoteAPIData(jsonString);
        quoteText.setText(quoteData.get(0).q);
        quoteAuthor.setText(quoteData.get(0).a);
        ArrayList<QuoteData> quoteData = jsonService.parseQuoteAPIData(jsonString);
            configureSaveButton(quoteData);

    }



}