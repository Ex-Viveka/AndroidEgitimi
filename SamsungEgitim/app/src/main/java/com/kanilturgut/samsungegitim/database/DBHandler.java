package com.kanilturgut.samsungegitim.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Author   : kanilturgut
 * Date     : 29/09/15
 * Time     : 16:22
 */
public class DBHandler extends SQLiteOpenHelper {

    private final String TAG = DBHandler.class.getSimpleName();
    public Context mContext;

    // singleton instance
    private static DBHandler instance;

    // veritabani bilgileri
    private static final String DATABASE = "my_books.db";
    private static final int VERSION = 1;

    // tablo adi
    private static final String TABLE_NAME = "table_book";

    // sutun isimleri
    private final String COL_ID = "_id";
    private final String COL_ADI = "name";
    private final String COL_YAZAR = "author";
    private final String COL_YAYINEVI = "publisher";
    private final String COL_SAYFA_SAYISI = "number_of_page";
    private final String COL_FIYAT = "price";


    /*
     * Constructor
     */
    public DBHandler(Context context) {
        super(context, DATABASE, null, VERSION);

        this.mContext = context;
    }

    /**
     * Singleton veritabani objesi
     */
    public static DBHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DBHandler(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // tablo ya da tablolari olustururken calisir
        String createQuery = "create table " + TABLE_NAME
                + " ( "
                + COL_ID + " integer primary key autoincrement, "
                + COL_ADI + " text, "
                + COL_YAZAR + " text, "
                + COL_YAYINEVI + " text, "
                + COL_SAYFA_SAYISI + " integer, "
                + COL_FIYAT + " real"
                + " );";

        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // versiyon sayisi degisen tablolar icin calisir.
        String dropQuery = "drop table if exists " + TABLE_NAME;
        db.execSQL(dropQuery);

        // Gecmis tablolar silindikten on onCreate metotu calisir ve yeni tablolar olusturulur
        onCreate(db);
    }

    // ================== CRUD ISLEMLERI ==================

    /*
     * Veritabanina yeni veri yazma islemi
     */
    public void create(Book book) {

        // veritabani yazmak icin acilsin
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try {
            // veritabanina yazacagimiz objemizi olusturalim
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_ADI, book.getName());
            contentValues.put(COL_YAZAR, book.getAuthor());
            contentValues.put(COL_YAYINEVI, book.getPublisher());
            contentValues.put(COL_SAYFA_SAYISI, book.getNumberOfPage());
            contentValues.put(COL_FIYAT, book.getPrice());

            // yazma islemi sirasinda a
            db.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "ERROR on create", e);
        } finally {
            db.endTransaction();
        }
    }

    /*
     * Veritabanina kayitli butun kitaplari doner
     */
    public List<Book> read() {

        List<Book> bookList = new LinkedList<>();

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

            if (cursor.moveToFirst()) {
                do {
                    // yeni bir kitap nesnesi yaratalim
                    Book book = new Book();
                    book.setId(cursor.getInt(0));
                    book.setName(cursor.getString(1));
                    book.setAuthor(cursor.getString(2));
                    book.setPublisher(cursor.getString(3));
                    book.setNumberOfPage(cursor.getInt(4));
                    book.setPrice(cursor.getDouble(5));

                    // kitabi listemize ekleyelim
                    bookList.add(book);
                } while (cursor.moveToNext());

                // cursor her zaman kapatilmalidir
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "ERROR on read", e);
        } finally {
            db.endTransaction();
        }

        return bookList;
    }

    public List<Book> readByName(String bookName) {

        List<Book> bookList = new LinkedList<>();

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
            }, COL_ADI + " = " + bookName, null, null, null, null, null);
            db.setTransactionSuccessful();

            if (cursor.moveToFirst()) {
                do {
                    // yeni bir kitap nesnesi yaratalim
                    Book book = new Book();
                    book.setId(cursor.getInt(0));
                    book.setName(cursor.getString(1));
                    book.setAuthor(cursor.getString(2));
                    book.setPublisher(cursor.getString(3));
                    book.setNumberOfPage(cursor.getInt(4));
                    book.setPrice(cursor.getDouble(5));

                    // kitabi listemize ekleyelim
                    bookList.add(book);
                } while (cursor.moveToNext());

                // cursor her zaman kapatilmalidir
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "ERROR on readByName", e);
            return null;
        } finally {
            db.endTransaction();
        }

        return bookList;
    }

    public boolean update(Book book, int id) {

        int affectedRow = 0;
        SQLiteDatabase db = getReadableDatabase();
        db.beginTransaction();

        try {
            // veritabanina yazacagimiz objemizi olusturalim
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_ADI, book.getName());
            contentValues.put(COL_YAZAR, book.getAuthor());
            contentValues.put(COL_YAYINEVI, book.getPublisher());
            contentValues.put(COL_SAYFA_SAYISI, book.getNumberOfPage());
            contentValues.put(COL_FIYAT, book.getPrice());

            affectedRow = db.update(TABLE_NAME, contentValues, COL_ID + " =? ", new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "ERROR on update", e);
        } finally {
            db.endTransaction();
        }

        return affectedRow == 1;
    }

    public boolean delete(int id) {

        int affectedRow = 0;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try {
            affectedRow = db.delete(TABLE_NAME, COL_ID + " =? ", new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "ERROR on delete", e);
        } finally {
            db.endTransaction();
        }

        return affectedRow == 1;
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try {
            db.delete(TABLE_NAME, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "ERROR on delete", e);
        } finally {
            db.endTransaction();
        }
    }

    // select all, cursor.getCount() --> row count
}
