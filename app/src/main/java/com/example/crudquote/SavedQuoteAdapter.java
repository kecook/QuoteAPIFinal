package com.example.crudquote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SavedQuoteAdapter extends RecyclerView.Adapter<SavedQuoteAdapter.ViewHolder>{
        public ArrayList<QuoteData> recyclerQuoteData;

        private Context context;

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView quote_text;
            private final TextView author_text;
            public ViewHolder(@NonNull View itemView){
                super(itemView);
                quote_text = itemView.findViewById(R.id.textViewQuote);
                author_text = itemView.findViewById(R.id.textViewAuthor);
            }
            public TextView getQuote_text() {return quote_text;}
            public TextView getAuthor_text(){return author_text;}
        }
        public SavedQuoteAdapter(Context context, ArrayList<QuoteData> recyclerQuoteData){
            this.context = context;
//       this.quoteData = quoteData;
            this.recyclerQuoteData = recyclerQuoteData;
        }
        @NonNull
        @Override
        public SavedQuoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_saved_quotes,parent,false);
            return new ViewHolder(view);
        }


    @Override
        public void onBindViewHolder(SavedQuoteAdapter.ViewHolder holder, int position) {
            holder.getQuote_text().setText(recyclerQuoteData.get(position).q);
            holder.getAuthor_text().setText(recyclerQuoteData.get(position).a);
        }
        @Override
        public int getItemCount() {
//        return quoteData.size();
            return recyclerQuoteData.size();
        }


    }






