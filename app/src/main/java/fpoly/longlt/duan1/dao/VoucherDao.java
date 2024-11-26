package fpoly.longlt.duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.longlt.duan1.database.DBHelper;
import fpoly.longlt.duan1.model.Voucher;

public class VoucherDao {
    Context context;
    DBHelper dbHelper;
    SQLiteDatabase database;

    public VoucherDao(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public ArrayList<Voucher> getAllVC() {
        ArrayList<Voucher> list = new ArrayList<>();
        String sql = "SELECT * FROM voucher";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                Voucher vc = new Voucher();
                vc.setId_vc(cursor.getInt(0));
                vc.setCode(cursor.getString(1));
                vc.setDiscount_price(cursor.getInt(2));
                vc.setStart_date(cursor.getString(3));
                vc.setEnd_date(cursor.getString(4));
                list.add(vc);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean insertVC(Voucher vc) {
        ContentValues values = new ContentValues();
        values.put("code", vc.getCode());
        values.put("discount_price", vc.getDiscount_price());
        values.put("start_date", vc.getStart_date());
        values.put("end_date", vc.getEnd_date());
        values.put("status", vc.getStatus());
        long result = database.insert("voucher", null, values);
        return result != -1;
    }



}
