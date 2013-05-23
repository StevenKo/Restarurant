package com.restaurant.collection.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.restaurant.collection.entity.Note;
import com.restaurant.collection.entity.Restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteRestaurant extends SQLiteOpenHelper {

    private static final String DB_NAME          = "kosrestaurant.sqlite"; // 資料庫名稱
    private static final int    DATABASE_VERSION = 1;                // 資料庫版本
    private SQLiteDatabase      db;
    private final Context       ctx;

    // Define database schema
    public interface RestaurantSchema {
        String TABLE_NAME   = "restaurants";
        String id           = "id";
        String restaurant_id = "restaurant_id";
        String name         = "name";
        String grade_food = "grade_food";
        String grade_service  = "grade_service";
        String pic_url  = "pic_url";
        String address           = "address";
        String open_time         = "open_time";
        String official_link = "official_link";
        String price  = "price";
        String traffic  = "traffic";
        String introduction  = "introduction";
        String x = "x";
        String y = "y";
    }

    public interface NoteSchema {
        String TABLE_NAME  = "notes";
        String id          = "id";
        String note_id = "note_id";
        String restaurant_id        = "restaurant_id";
        String title       = "title";
        String intro    = "intro";
        String pic_url = "singer_name";
        String link = "link";
        String x = "x";
        String y = "y";
    }


    public SQLiteRestaurant(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        ctx = context;

        if (db == null)
            db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + RestaurantSchema.TABLE_NAME + " (" 
                + RestaurantSchema.id + " INTEGER PRIMARY KEY" + ","
                + RestaurantSchema.restaurant_id + " INTEGER NOT NULL" + ","
        		+ RestaurantSchema.name + " TEXT NOT NULL" + "," 
                + RestaurantSchema.grade_food + " TEXT NOT NULL" + "," 
        		+ RestaurantSchema.grade_service + " TEXT NOT NULL" + ","
                + RestaurantSchema.pic_url + " TEXT NOT NULL" + ","
                + RestaurantSchema.address + " TEXT NOT NULL"+ ","
                + RestaurantSchema.open_time + " TEXT NOT NULL"+ ","
                + RestaurantSchema.official_link + " TEXT NOT NULL"+ ","
                + RestaurantSchema.price + " TEXT NOT NULL"+ ","
                + RestaurantSchema.traffic + " TEXT NOT NULL"+ ","
                + RestaurantSchema.introduction + " TEXT NOT NULL"+ ","
                + RestaurantSchema.x + " REAL NOT NULL"+ ","
                + RestaurantSchema.y + " REAL NOT NULL"+ ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + NoteSchema.TABLE_NAME + " (" 
                + NoteSchema.id + " INTEGER PRIMARY KEY" + "," 
                + NoteSchema.note_id + " INTEGER NOT NULL" + ","
        		+ NoteSchema.restaurant_id + " INTEGER NOT NULL" + "," 
                + NoteSchema.title + " TEXT NOT NULL" + "," 
        		+ NoteSchema.intro + " TEXT NOT NULL" + ","
                + NoteSchema.pic_url + " TEXT NOT NULL" + ","
                + NoteSchema.link + " TEXT NOT NULL" + ","
                + NoteSchema.x + " TEXT NOT NULL" + ","
                + NoteSchema.y + " TEXT NOT NULL"+ ");");
        

    }

    public boolean deleteRestaurant(Restaurant r) {
        Cursor cursor = db.rawQuery("DELETE FROM restaurants WHERE `restaurants`.`restaurant_id` = ?", new String[] { r.getId() + "" });
        cursor.moveToFirst();
        cursor.close();
        return true;
    }
    public boolean deleteNote(Note note) {
        Cursor cursor = db.rawQuery("DELETE FROM notes WHERE `notes`.`note_id` = ?", new String[] { note.getId() + "" });
        cursor.moveToFirst();
        cursor.close();
        return true;
    }

    public long insertRestaurant(Restaurant r) {
        ContentValues args = new ContentValues();
        args.put(RestaurantSchema.restaurant_id, r.getId());
        args.put(RestaurantSchema.name,r.getName());
        args.put(RestaurantSchema.grade_food, r.getGradeFood());
        args.put(RestaurantSchema.grade_service, r.getGradeService());
        args.put(RestaurantSchema.pic_url, r.getPicUrl());
        args.put(RestaurantSchema.address, r.getAddress());
        args.put(RestaurantSchema.open_time,r.getOpenTime());
        args.put(RestaurantSchema.official_link, r.getOfficailLink());
        args.put(RestaurantSchema.price, r.getPrice());
        args.put(RestaurantSchema.traffic, r.getTraffic());
        args.put(RestaurantSchema.introduction, r.getIntroduction());
        args.put(RestaurantSchema.x, r.getX());
        args.put(RestaurantSchema.y, r.getY());
        return db.insert(RestaurantSchema.TABLE_NAME, null, args);
    }

    public long insertNote(Note note) {
        ContentValues args = new ContentValues();
        args.put(NoteSchema.note_id, note.getId());
        args.put(NoteSchema.restaurant_id,note.getRestaurantId());
        args.put(NoteSchema.title, note.getTitle());
        args.put(NoteSchema.intro, note.getIntro());
        args.put(NoteSchema.pic_url, note.getPicUrl());
        args.put(NoteSchema.link, note.getLink());
        args.put(RestaurantSchema.x, note.getX());
        args.put(RestaurantSchema.y, note.getY());
        return db.insert(NoteSchema.TABLE_NAME, null, args);
    }

    

    public ArrayList<Note> getAllNotes() {
        Cursor cursor = null;
        ArrayList<Note> notes = new ArrayList<Note>();
        cursor = db.rawQuery("SELECT * FROM " + NoteSchema.TABLE_NAME + " ORDER BY id DESC", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(1);
            int restaurant_id = cursor.getInt(2);
            String title = cursor.getString(3);
            String intro = cursor.getString(4);
            String pic_url = cursor.getString(5);
            String link = cursor.getString(6);
            Double x = cursor.getDouble(7);
            Double y = cursor.getDouble(8);
            Note note = new Note(id, restaurant_id,title, intro, pic_url, link,x,y);
            notes.add(note);
        }
        return notes;
    }
    
    public ArrayList<Restaurant> getAllRestaurants() {
        Cursor cursor = null;
        ArrayList<Restaurant> rs = new ArrayList<Restaurant>();
        cursor = db.rawQuery("SELECT * FROM " + RestaurantSchema.TABLE_NAME + " ORDER BY id DESC", null);

        while (cursor.moveToNext()) {
        	
        	int id = cursor.getInt(1);
        	String  name = cursor.getString(2);
            String  grade_food= cursor.getString(3);
            String  grade_service= cursor.getString(4);
            String  pic_url= cursor.getString(5);
            String  address= cursor.getString(6);
            String  open_time= cursor.getString(7);
            String  official_link= cursor.getString(8);
            String  price= cursor.getString(9);
            String  traffic= cursor.getString(10);
            String  introduction= cursor.getString(11);
            double  x_lan = cursor.getDouble(12);
            double  y_long = cursor.getDouble(13);

            Restaurant r = new Restaurant( id, name,grade_food,grade_service, pic_url, address,open_time, official_link,price,traffic,introduction, x_lan, y_long);
            rs.add(r);
        }
        return rs;
    }
  

    

    public Boolean isRestaurantCollected(int restaurant_id) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + RestaurantSchema.TABLE_NAME + " where restaurant_id = " + restaurant_id, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
    
    public Boolean isNoteCollected(int note_id) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + NoteSchema.TABLE_NAME + " where note_id = " + note_id, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

   
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
