package fpoly.longlt.duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fpoly.longlt.duan1.database.DBHelper;
import fpoly.longlt.duan1.model.DonHang;

public class QuanLiDonHangDao {
    DBHelper dbHelper;
    SQLiteDatabase database;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public QuanLiDonHangDao(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }
    public ArrayList<DonHang> getAllDonHang(){
        ArrayList<DonHang> lst = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from bills", null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                DonHang donHang = new DonHang();
                donHang.setOd_id(cursor.getInt(0));
                donHang.setUser_id(cursor.getInt(1));
                try {
                    donHang.setOd_date(sdf.parse(cursor.getString(2)));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                donHang.setTotal_price(cursor.getInt(3));
                donHang.setStatus(cursor.getInt(4));
                lst.add(donHang);
            } while (cursor.moveToNext());
        }
        return lst;
    }

    public String getNameKH(int id){
        String name = null;
        Cursor cursor = database.rawQuery("select u.name from bills b " +
                "join user u on b.user_id = u.user_id " +
                "where u.user_id = ?",new String[]{String.valueOf(id)});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            name = cursor.getString(0);
        }
        return name;
    }

    public boolean updateStatusDonHang(int id, int status){
        ContentValues values = new ContentValues();
        values.put("status", status);
        int check = database.update("bills", values, "od_id = ?", new String[]{String.valueOf(id)});
        return check!=-1;
    }
    public int getQuantity(int id){
        int quantity = 0;
        Cursor cursor = database.rawQuery("select sum(quantity) from orderdetail where od_id = ? group by od_id", new String[]{String.valueOf(id)});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            quantity = cursor.getInt(0);
        }
        return quantity;
    }
//    "od_id integer primary key autoincrement," +
//            "user_id integer references user," +
//            "od_date date not null," +
//            "total_price integer," +
//            "status integer)";

//     "user_id integer primary key autoincrement," +
//             "username text," +
//             "password text," +
//             "name text," +
//             "sdt text," +
//             "address text default 'ha noi'," +
//             "moneyonl integer default 0," +
//             "role integer default 0," +
//             "status bit default 1," +
//             "imgavatar text)";
}
