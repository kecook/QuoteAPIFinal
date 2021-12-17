package com.example.crudquote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkingService {

    String url = "https://zenquotes.io/api/quotes";

    public static final ExecutorService networkingExecutor = Executors.newFixedThreadPool(4);
    static Handler networkingHandler = new Handler(Looper.getMainLooper());

    interface NetworkingListener{
        void APINetworkListener(String jsonString);

    }


    NetworkingListener listener;


//    View.OnClickListener listener;

    public void fetchQuoteData(){
        String completeURL = url;
        connect(completeURL);
    }




    private void connect(String url){
        //takes the text the user input
        networkingExecutor.execute(new Runnable() {
            String jsonString = "";
            @Override
            public void run() {

                HttpURLConnection httpURLConnection = null;
                try {
                    URL urlObj = new URL(url);
                    httpURLConnection = (HttpURLConnection)urlObj.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Content_Type", "application/json");
                    int statues = httpURLConnection.getResponseCode();

                    if ((statues >= 200) && (statues <= 299)) {
                        InputStream in = httpURLConnection.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(in);
                        int read = 0;
                        String jsonString = "";
                        while ((read = inputStreamReader.read()) != -1) {
                            char c = (char) read;
                            jsonString += c;
                        }
                        final String finalJson = jsonString;
                        networkingHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.APINetworkListener(finalJson);

                            }
                        });
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                finally {
                    httpURLConnection.disconnect();
                }

            }
        });

    }
}
