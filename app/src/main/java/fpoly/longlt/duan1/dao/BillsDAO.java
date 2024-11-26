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
        contentValues.put("total_price", donHang.getTotal_price());
        contentValues.put("status", donHang.getStatus());
        long result = sqLiteDatabase.insert("bills", null, contentValues);
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
        if (result == -1) {
            return false;
        }
        return true;
    }

    @SuppressLint("Range")
    public ArrayList<DonHang> getData(String sql, String... selectionArgs) {
        ArrayList<DonHang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                DonHang donHang = new DonHang();
                donHang.setOd_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("od_id"))));
                donHang.setUser_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("user_id"))));
                donHang.setVc_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("vc_id"))));
                donHang.setOdDetail_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex("oddetail_id"))));
                try {
                    donHang.setOd_date(sdf.parse(cursor.getString(cursor.getColumnIndex("od_date"))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                donHang.setTotal_price(Integer.parseInt(cursor.getString(cursor.getColumnIndex("total_price"))));
                donHang.setStatus(Integer.parseInt(cursor.getString(cursor.getColumnIndex("status"))));
                list.add(donHang);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
        return list;
    }

    // Lay tat ca data
    public ArrayList<DonHang> getAll(){
        String sql = "SELECT * FROM bills";
        return getData(sql);
    }

    // Lay tat ca theo id
    public DonHang getDataByID(String id){
        String sql = "SELECT * FROM bills WHERE od_id = ?";
        ArrayList<DonHang> list = getData(sql,id);
        return list.get(0);
    }


}
