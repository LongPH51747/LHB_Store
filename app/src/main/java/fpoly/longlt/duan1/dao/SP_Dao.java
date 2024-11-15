package fpoly.longlt.duan1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.longlt.duan1.database.DBHelper;
import fpoly.longlt.duan1.model.SanPham;

public class SP_Dao {
    private DBHelper dbHelper;
    public SQLiteDatabase db;

    public SP_Dao(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<SanPham> getAllSP(){
        ArrayList<SanPham> lst = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        Cursor cursor = db.rawQuery(sql, null);

        return lst;
    }
}
