package com.example.andro.church;

import android.content.Context;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class OpenHelperSQLite extends SQLiteOpenHelper {

    public OpenHelperSQLite(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Church", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Church (ID INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 0 ,Name TEXT ,Addr TEXT ,Phone TEXT ,Father_of TEXT,Children integer,Date_of_visit TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + "Church");
        onCreate(db);
    }

    public void insert(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into Church (Name,Addr,Phone,Father_of,Children,Date_of_visit) VALUES('" + contact.getName() + "','" + contact.getAddres() + "','" + contact.getNumberphine() + "','" + contact.getFather_of_confession() + "'," + contact.getChildren() + ",'" + contact.getDate_of_visit() + "')");
    db.close();
    }

    public ArrayList<Contact> search(String name) {
        ArrayList<Contact> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT *FROM Church where Name like '%" + name + "%'", null);
        if (cursor != null) {
            cursor.moveToFirst();
            list.add(new Contact(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(6),cursor.getInt(5)));
            while (cursor.moveToNext()) {
                list.add(new Contact(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(6),cursor.getInt(5)));
            }
        }
        cursor.close();
        return list;
    }

    public void update(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Church SET  Name = '"+contact.getName()+"' ,Addr = '"+contact.getAddres()+"' ,Phone = '"+contact.getNumberphine()+"' ,Father_of = '"+contact.getFather_of_confession()+"' ,Children = "+contact.getChildren()+" ,Date_of_visit = '"+contact.getDate_of_visit()+"' where ID = "+contact.getId());
        db.close();
    }
    public void delete(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete FROM Church where ID = "+contact.getId());
        db.close();
    }
    public ArrayList<Contact> all() {
        ArrayList<Contact> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT *FROM Church", null);
        if (cursor != null) {
            cursor.moveToFirst();
            list.add(new Contact(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(6),cursor.getInt(5)));
            while (cursor.moveToNext()) {
                list.add(new Contact(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(6),cursor.getInt(5)));
            }
        }
        cursor.close();
        return list;
    }

}
