package com.example.crudquote;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuoteDAO {
    @Insert
    void insertNewQuote(QuoteData quoteData);

    @Delete
    void deleteQuote(QuoteData todelete);

    @Query("SELECT * FROM QuoteData")
    List<QuoteData> getAllLists();

}
