package com.kanilturgut.databasedenemeleri.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kanilturgut.databasedenemeleri.R;
import com.kanilturgut.databasedenemeleri.db.DBHandler;
import com.kanilturgut.databasedenemeleri.model.Book;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFakeBooks();
    }

    private void createFakeBooks() {

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

        // sisteme tek tek ekle
//        insertToDB(book1);
//        insertToDB(book2);
//        insertToDB(book3);
//        insertToDB(book4);
//        insertToDB(book5);

        // sistemden al
//        getBooksFromDB();
//        updateBookFromId(3);
        deleteBookWithId(3);


    }

    private void insertToDB(final Book book) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                DBHandler.getInstance(mContext).create(book);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Log.i(TAG, "SUCCESSFULL");
            }
        }.execute();

    }

    private void getBooksFromDB() {

        new AsyncTask<Void, Void, List<Book>>() {

            @Override
            protected List<Book> doInBackground(Void... params) {
                List<Book> list = DBHandler.getInstance(mContext).read();

                return list;
            }

            @Override
            protected void onPostExecute(List<Book> books) {
                super.onPostExecute(books);

                for (Book book : books) {
                    Log.i(TAG, "The author of " + book.getName() +
                            " is " + book.getAuthor());
                }
            }
        }.execute();
    }

    private void updateBookFromId(final int id) {

        final Book book = new Book();
        book.setName("Fi");
        book.setAuthor("Akilah Azra Kohen");
        book.setPublisher("Destek Yayınları");
        book.setNumberOfPage(600);
        book.setPrice(26);

        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {

                return DBHandler.getInstance(mContext).update(book, id);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);

                if (aBoolean) {
                    Log.i(TAG, "oldu");
                }
            }
        }.execute();
    }

    private void deleteBookWithId(final int id) {

        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {
                return DBHandler.getInstance(mContext).delete(id);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);

                if (aBoolean) {
                    Log.i(TAG, "Sildim abi");
                }
            }
        }.execute();
    }
}
