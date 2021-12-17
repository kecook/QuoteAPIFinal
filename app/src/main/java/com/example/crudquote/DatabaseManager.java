package com.example.crudquote;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseManager {

    static QuoteDatabase db;
    ExecutorService databaseExecuter = Executors.newFixedThreadPool(4);
    Handler db_handler = new Handler(Looper.getMainLooper());

    public interface DatabaseListener {
        void databaseAllQuoteListener(List<QuoteData> list);
    }

    public DatabaseListener listener;


    private static void BuildDBInstance (Context context) {
        db = Room.databaseBuilder(context, QuoteDatabase.class,"quote_db").build();
    }
    public static QuoteDatabase getDBInstance(Context context){
        if (db == null){
            BuildDBInstance(context);
        }
        return db;
    }

    public void insertNewQuote(QuoteData q){
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                db.getQuoteDataDAO().insertNewQuote(q);
            }
        });
    }

    public void getAllQuotes(){
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                List<QuoteData> list =  db.getQuoteDataDAO().getAllLists();
                db_handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.databaseAllQuoteListener(list);
                    }
                });

            }
        });

    }


//    public void getAllDonationsBiggerThan(double amout){
//        databaseExecuter.execute(new Runnable() {
//            @Override
//            public void run() {
//                List<QuoteData> list =  db.getDonationDAO().getAllDonationsBiggerThan(amout);
//                db_handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        listener.databaseAllDonationListener(list);
//                    }
//                });
//
//            }
//        });
//
//    }




}
