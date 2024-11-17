package fpoly.longlt.duan1.dao;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import  fpoly.longlt.duan1.database.DBHelper;
import fpoly.longlt.duan1.model.SanPham;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.longlt.duan1.Database.DBHelper;
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

   private DBHelper dbHelper;
   private SQLiteDatabase db;

   public SanPhamDAO(Context context){
      dbHelper = new DBHelper(context);
      db = dbHelper.getWritableDatabase();
   }

   public ArrayList<SanPham> getAll(){
      ArrayList<SanPham> arrayList = new ArrayList<>();
       Cursor cursor = db.rawQuery("SELECT * FROM sanpham", null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
                do {
            SanPham sanPham = new SanPham();
            sanPham.setSpId(cursor.getInt(0));
            sanPham.setTenSp(cursor.getString(1));
            sanPham.setImg(cursor.getString(2));
            sanPham.setStatus(cursor.getInt(3));
            sanPham.setPrice(cursor.getInt(4));
        arrayList.add(sanPham);
                }while (cursor.moveToNext());

        }
   return arrayList;
   }
}
