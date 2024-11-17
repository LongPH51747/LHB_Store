package fpoly.longlt.duan1.dao;
import android.content.ContentValues;
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


public class SanPhamDAO {

//    public SanPhamDAO(Context context) {
//        dbHelper = new DBHelper(context);
//        database = dbHelper.getWritableDatabase();
//    }
//    public ArrayList<SanPham> getAll(){
//        ArrayList<SanPham> list = new ArrayList<>();
//        Cursor cursor = database.query("SanPham",null,null,null,null,null,null);
//        do {
//            if (cursor.moveToFirst()){
//
//            }
//        } while (cursor.moveToNext());
//        return list;
//    }

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

   public boolean insertSP(SanPham sanPham){
       ContentValues values = new ContentValues();
       values.put("tensp", sanPham.getTenSp());
       values.put("img", sanPham.getImg());
       values.put("status", sanPham.getStatus());
       values.put("price", sanPham.getPrice());
       long result = db.insert("sanpham", null, values);
       return result != -1;
   }

   public boolean updateSP(SanPham sanPham){
       ContentValues values = new ContentValues();
       values.put("status", sanPham.getStatus());
       long result = db.update("sanpham", values, "sp_id = ?", new String[]{String.valueOf(sanPham.getSpId())});
       return result != -1;
   }


   public ArrayList<Integer> getIdSP(String tenSP){
       ArrayList<Integer> id = new ArrayList<>();
       Cursor cursor = db.rawQuery("SELECT sp_id FROM sanpham WHERE tensp = ?", new String[]{tenSP});
       if (cursor.getCount() > 0){
           cursor.moveToFirst();
           id.add(cursor.getInt(0));
       }
       return id;
   }
}
