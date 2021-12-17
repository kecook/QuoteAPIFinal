package com.example.crudquote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SavedQuoteActivity extends AppCompatActivity {
    NetworkingService networkingService;
    JsonService jsonService;
    TextView textView;
    TextView textViewTile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_quote);
        networkingService = ((myApp) getApplication()).getNetworkingService();
        jsonService = ((myApp) getApplication()).getJsonService();

        String quoteName = getIntent().getStringExtra("SelectedQuote");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(quoteName);
        textViewTile = (TextView) findViewById(R.id.textView);
        configureBackButton();
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

    }





