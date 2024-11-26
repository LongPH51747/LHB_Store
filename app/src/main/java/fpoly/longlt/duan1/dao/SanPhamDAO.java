package fpoly.longlt.duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.longlt.duan1.database.DBHelper;

import fpoly.longlt.duan1.model.ChiTietSP;
import fpoly.longlt.duan1.model.SanPham;

import android.util.Log;


public class SanPhamDAO {

   private DBHelper dbHelper;
   private SQLiteDatabase db;

    public SanPhamDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

//<<<<<<< HEAD
//    public ArrayList<SanPham> getAll() {
//        ArrayList<SanPham> arrayList = new ArrayList<>();
//        Cursor cursor = db.rawQuery("SELECT * FROM sanpham", null);
//        if (cursor.getCount() > 0) {
//=======
//   //Bảng sản phẩm

   public ArrayList<SanPham> getAllSP(){
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
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()){
            cursor.close();
        }
        return arrayList;
    }

//   public ArrayList<SanPham> getSPChiTiet(int sp_id){
//       SanPham sanPham = null;
//
//       Cursor cursor = db.rawQuery("SELECT * FROM sanpham WHERE sp_id = ?", new String[]{String.valueOf(sp_id)});
//
//   }
   public boolean insertSP(SanPham sanPham){
       ContentValues values = new ContentValues();
       values.put("tensp", sanPham.getTenSp());
       values.put("img", sanPham.getImg());
       values.put("status", sanPham.getStatus());
       values.put("price", sanPham.getPrice());
       long result = db.insert("sanpham", null, values);
       return result != -1;
   }

   public boolean updateStatusSP(int id, boolean check){
       int status = check ? 1 :0;
       ContentValues values = new ContentValues();
       values.put("status", status);
       long result = db.update("sanpham", values, "sp_id = ?", new String[]{String.valueOf(id)});
       return result != -1;
   }

    public boolean updateSP(SanPham sanPham){
       ContentValues values = new ContentValues();
       values.put("tensp", sanPham.getTenSp());
       values.put("img", sanPham.getImg());
       values.put("price", sanPham.getPrice());
       long result = db.update("sanpham", values,"sp_id = ?", new String[]{String.valueOf(sanPham.getSpId())});
       return result != -1;
    }

   public int getIdSP(SanPham sanPham){
       int i = -1;
       Cursor cursor = db.rawQuery("SELECT sp_id FROM sanpham WHERE tensp = ?", new String[]{String.valueOf(sanPham.getTenSp())});
       if (cursor.getCount()>0){
           cursor.moveToFirst();
           i = cursor.getInt(0);
       }
       else {
           Log.d("bug", "ko tìm thấy curso");
       }
       return i;
   }

   //Bảng chi tiết sản phẩm
    public ArrayList<ChiTietSP> getAllChiTietSP() {
       ArrayList<ChiTietSP> lst = new ArrayList<>();
       Cursor cursor = db.rawQuery("select * from chitietsp", null);
       if (cursor.getCount() > 0) {
           cursor.moveToFirst();
           do {
               ChiTietSP chiTietSP = new ChiTietSP();
               chiTietSP.setChitietSP_id(cursor.getInt(0));
               chiTietSP.setSp_id(cursor.getInt(1));
               chiTietSP.setMota(cursor.getString(2));
               chiTietSP.setSize(cursor.getString(3));
               chiTietSP.setColor(cursor.getString(4));
               chiTietSP.setSoluong(cursor.getInt(5));
               lst.add(chiTietSP);
           }while (cursor.moveToNext());
       }
       return lst;
    }

    public boolean insertChiTietSP(ChiTietSP chiTietSP) {
       ContentValues values = new ContentValues();
       values.put("sp_id", chiTietSP.getSp_id());
       values.put("description", chiTietSP.getMota());
       values.put("size", chiTietSP.getSize());
       values.put("color", chiTietSP.getColor());
       values.put("soluong", chiTietSP.getSoluong());
       long result = db.insert("chitietsp", null, values);
       return result != -1;
    }

    public boolean updateChiTietSP(ChiTietSP chiTietSP) {
       ContentValues values = new ContentValues();
       values.put("sp_id", chiTietSP.getSp_id());
       values.put("description", chiTietSP.getMota());
       values.put("size", chiTietSP.getSize());
       values.put("color", chiTietSP.getColor());
       values.put("soluong", chiTietSP.getSoluong());
       int result = db.update("chitietsp", values, "chitietsp_id = ?", new String[]{String.valueOf(chiTietSP.getChitietSP_id())});
       return result != -1;
    }

}
