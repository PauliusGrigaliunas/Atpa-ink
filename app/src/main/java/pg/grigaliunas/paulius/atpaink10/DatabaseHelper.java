package pg.grigaliunas.paulius.atpaink10;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;



public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Atpazink.db";
    public static final String Table_Gallery = "Gallery";
    public static final String Col_ID = "ID";
    public static final String Col_date = "date";
    public static final String Col_prediction = "prediction";
    public static final String Col_image = "image";

    protected SQLiteDatabase db = this.getWritableDatabase();


    protected final String CreateGalleryTable =
            "Create Table " + Table_Gallery + " (" +
                    Col_ID + " INTEGER PRIMARY KEY, " +
                    Col_date+ " text UNIQUE, " +
                    Col_prediction + " INTEGER, " +
                    Col_image + " BLOB) ";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateGalleryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if Exists " + Table_Gallery );
        onCreate(db);
    }


    public boolean insertTaskData(ImageObject imageObject ){

        byte[] data = getBitmapAsByteArray(imageObject.getImage());
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_date, System.currentTimeMillis());
        contentValues.put(Col_prediction, imageObject.getPrediction());
        contentValues.put(Col_image, data);
        long result = db.insert(Table_Gallery, null, contentValues);
        return (result == -1 )? false: true;
    }

    private static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public boolean delete(ImageObject imageObject ) {
        int result = db.delete(Table_Gallery, Col_ID  + "=" + imageObject.getID(), null);
        return (result == 0) ? false : true;
    }

    public Cursor showAll(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table_Gallery , null);
        return cursor;
    }
}

