package com.desarrollo.autores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;

import com.desarrollo.autores.Json.JsonProcess;
import com.desarrollo.autores.Models.DataBook;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String BOOK_URL =  "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String START_INDEX = "&startIndex=";
    private static final String LIMIT = "&maxResults=";
    private int startIndex = 0;
    private int limit = 10;
    private String queryCurrent = "";

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncBook("javacript");
        RecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                AsyncBook(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //AsyncBook(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void AsyncBook(String query) {
        queryCurrent = query;
        JsonProcess jsonProcess = new JsonProcess(this);
        jsonProcess.execute(BOOK_URL+query+LIMIT+limit);
    }

    private void AsyncBookPagination() {
        JsonProcess jsonProcess = new JsonProcess(this);
        jsonProcess.execute(BOOK_URL+queryCurrent+START_INDEX+startIndex+limit);
    }

    private void RecyclerView() {
        recyclerView = findViewById(R.id.books_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public void setBooks(ArrayList<DataBook> books) {
        BookAdapter bookAdapter = new BookAdapter(books);
        recyclerView.setAdapter(bookAdapter);
    }
}
