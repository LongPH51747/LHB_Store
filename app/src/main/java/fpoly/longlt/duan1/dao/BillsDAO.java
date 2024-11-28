package fpoly.longlt.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;


import java.util.ArrayList;

import fpoly.longlt.duan1.database.DBHelper;
import fpoly.longlt.duan1.model.DonHang;

public class BillsDAO {
    public DBHelper dbHelper;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public BillsDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean insertBill(DonHang donHang) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", donHang.getUser_id());
        contentValues.put("vc_id", donHang.getVc_id());
        contentValues.put("oddetail_id", donHang.getOdDetail_id());
        contentValues.put("od_date", sdf.format(donHang.getOd_date()));
        contentValues.put("status", donHang.getStatus());
        contentValues.put("chitietsp_id", donHang.getChitietsp_id());
        long result = sqLiteDatabase.insert("bills", null, contentValues);
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
        if (result == -1) {
            return false;
        }
        return true;
    }

    // Lay anh tu bang sp qua bang spchitiet de lay dc ben bills

    public String getImgBills(int od_id){
        String sql = "SELECT sanpham.img FROM bills" +
                " JOIN chitietsp ON bills.chitietsp_id = chitietsp.chitietsp_id" +
                " JOIN sanpham ON chitietsp.sp_id = sanpham.sp_id" +
                " WHERE bills.od_id = ?";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,new String[]{String.valueOf(od_id)});
        String img = null;
        if (cursor != null && cursor.moveToFirst()){
            // Lấy ảnh ra từ cốt tên img tại bảng sp
            img = cursor.getString(cursor.getColumnIndexOrThrow("img"));
            cursor.close();
            return img;
        }
        if (cursor != null && !cursor.isClosed()){
            cursor.close();
        }
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
        return img;
    }

    public ArrayList<DonHang> getAll(int user_id){
        ArrayList<DonHang> list = new ArrayList<>();
        String sql = "SELECT * FROM bills WHERE bills.user_id = ?";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{String.valueOf(user_id)});
        try {
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do {
                    DonHang donHang = new DonHang();
                    donHang.setOd_id(cursor.getInt(0));
                    donHang.setUser_id(cursor.getInt(1));
                    donHang.setVc_id(cursor.getInt(2));
                    donHang.setChitietsp_id(cursor.getInt(3));
                    donHang.setOdDetail_id(cursor.getInt(4));
                    donHang.setOd_date(sdf.parse(cursor.getString(5)));
                    donHang.setStatus(cursor.getInt(6));
                    list.add(donHang);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
                sqLiteDatabase.close();
            }
        }
        return list;
    }
    public int getID_OD(int user_id){
        String sql = "SELECT od_id FROM bills WHERE user_id = ?";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,new String[]{String.valueOf(user_id)});
        try {
            if (cursor != null && cursor.moveToFirst()){
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
                cursor.close();
                return id;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null && cursor.isClosed()){
                cursor.close();
            }
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
                sqLiteDatabase.close();
            }
        }
        return -1;
    }
}
