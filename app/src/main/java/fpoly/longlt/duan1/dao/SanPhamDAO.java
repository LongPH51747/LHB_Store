package fpoly.longlt.duan1.dao;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import  fpoly.longlt.duan1.database.DBHelper;
import fpoly.longlt.duan1.model.SanPham;

public class SanPhamDAO {
    DBHelper dbHelper;
    SQLiteDatabase database;

    public SanPhamDAO(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }
    public ArrayList<SanPham> getAll(){
        ArrayList<SanPham> list = new ArrayList<>();
        Cursor cursor = database.query("SanPham",null,null,null,null,null,null);
        do {
            if (cursor.moveToFirst()){
                a
            }
        } while (cursor.moveToNext());
        return list;
    }

}
