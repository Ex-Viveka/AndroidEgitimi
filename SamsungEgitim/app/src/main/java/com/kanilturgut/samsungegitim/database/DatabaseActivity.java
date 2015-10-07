package com.kanilturgut.samsungegitim.database;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kanilturgut.samsungegitim.R;

import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    private final String TAG = DatabaseActivity.class.getSimpleName();
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Book book1 = new Book();
        book1.setName("Grey");
        book1.setAuthor("E L James");
        book1.setPublisher("Doğan Kitap");
        book1.setNumberOfPage(600);
        book1.setPrice(22.04);

        Book book2 = new Book();
        book2.setName("Sabit Fikir Sayı:55");
        book2.setAuthor("Kolektif");
        book2.setPublisher("Sabit Fikir Dergisi");
        book2.setNumberOfPage(60);
        book2.setPrice(2.19);

        Book book3 = new Book();
        book3.setName("Fi");
        book3.setAuthor("Akilah Azra Kohen");
        book3.setPublisher("Destek Yayınları");
        book3.setNumberOfPage(600);
        book3.setPrice(14);

        Book book4 = new Book();
        book4.setName("Kürk Mantolu Madonna");
        book4.setAuthor("Sabahattin Ali");
        book4.setPublisher("Yapı Kredi Yayınları");
        book4.setNumberOfPage(164);
        book4.setPrice(7.15);

        Book book5 = new Book();
        book5.setName("Dört Mevsim");
        book5.setAuthor("Kolektif");
        book5.setPublisher("Dört Nokta");
        book5.setNumberOfPage(96);
        book5.setPrice(7.23);

//        insertToDB(book1);
//        insertToDB(book2);
//        insertToDB(book3);
//        insertToDB(book4);
//        insertToDB(book5);

        getAllBooks();
    }

    private void insertToDB(final Book book) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                DBHandler.getInstance(mContext).create(book);
                return null;
            }
        }.execute();
    }

    private void getAllBooks() {

        new AsyncTask<Void, Void, List<Book>>() {

            @Override
            protected List<Book> doInBackground(Void... params) {
                return DBHandler.getInstance(mContext).read();
            }

            @Override
            protected void onPostExecute(List<Book> books) {
                super.onPostExecute(books);

                if (books != null) {
                    for (Book book : books) {
                        Log.i(TAG, "name : " + book.getName());
                    }
                }
            }
        }.execute();
    }
}
