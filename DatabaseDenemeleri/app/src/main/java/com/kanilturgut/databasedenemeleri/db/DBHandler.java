package com.kanilturgut.databasedenemeleri.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.kanilturgut.databasedenemeleri.model.Book;

import java.util.LinkedList;
import java.util.List;

/**
 * Author   : kanilturgut
 * Date     : 07/10/15
 * Time     : 18:01
 */
public class DBHandler extends SQLiteOpenHelper {

    private String TAG = DBHandler.class.getSimpleName();
    private Context mContext;

    private static String DATABASE_NAME = "bilisim_mucitleri.db";
    private static int VERSION = 1;

    // tablo adi
    private static final String TABLE_NAME = "book";

    // sutun isimleri
    private final String COL_ID = "_id";
    private final String COL_ADI = "name";
    private final String COL_YAZAR = "author";
    private final String COL_YAYINEVI = "publisher";
    private final String COL_SAYFA_SAYISI = "number_of_page";
    private final String COL_FIYAT = "price";

    // Singleton object
    private static DBHandler instance;

    // Constructor
    private DBHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

        this.mContext = context;
    }

    public static DBHandler getInstance(Context context) {

        if (instance == null) {
            instance = new DBHandler(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = "create table " + TABLE_NAME
                + " ( "
                + COL_ID + " integer primary key autoincrement, "
                + COL_ADI + " text, "
                + COL_YAZAR + " text, "
                + COL_YAYINEVI + " text, "
                + COL_SAYFA_SAYISI + " integer, "
                + COL_FIYAT + " real, "
                + " );";

        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String drop = "drop table if exists " + TABLE_NAME;
        db.execSQL(drop);

        onCreate(db);
    }

    // CRUD --> C
    public void create(Book book) {

        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_ADI, book.getName());
            contentValues.put(COL_YAZAR, book.getAuthor());
            contentValues.put(COL_YAYINEVI, book.getPublisher());
            contentValues.put(COL_SAYFA_SAYISI, book.getNumberOfPage());
            contentValues.put(COL_FIYAT, book.getPrice());

            db.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "ERROR on create", e);
        } finally {
            db.endTransaction();
        }
    }

    // CRUD --> R
    public List<Book> read() {

        List<Book> list = new LinkedList<>();

        SQLiteDatabase db = getReadableDatabase();
        db.beginTransaction();

        try {
            Cursor cursor = db.query(true, TABLE_NAME, new String[]{
                    COL_ID,             // 0
                    COL_ADI,            // 1
                    COL_YAZAR,          // 2
                    COL_YAYINEVI,       // 3
                    COL_SAYFA_SAYISI,   // 4
                    COL_FIYAT           // 5
            }, null, null, null, null, null, null);
            db.setTransactionSuccessful();

            if (cursor != null) {
                if (cursor.moveToFirst()) {

                    do {
                        Book book = new Book();
                        book.setId(cursor.getInt(0));
                        book.setName(cursor.getString(1));
                        book.setAuthor(cursor.getString(2));
                        book.setPublisher(cursor.getString(3));
                        book.setNumberOfPage(cursor.getInt(4));
                        book.setPrice(cursor.getDouble(5));

                        list.add(book);
                    } while (cursor.moveToNext());

                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "ERROR on read", e);
        } finally {
            db.endTransaction();
        }

        return list;
    }
}
