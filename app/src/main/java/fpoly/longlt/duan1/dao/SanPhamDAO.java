package fpoly.longlt.duan1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.longlt.duan1.Database.DBHelper;
import fpoly.longlt.duan1.model.SanPham;

public class SanPhamDAO {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public SanPhamDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Lấy danh sách tất cả sản phẩm
    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> arrayList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM sanpham", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    SanPham sanPham = new SanPham();
                    sanPham.setSpId(cursor.getInt(0)); // sp_id
                    sanPham.setTenSp(cursor.getString(1)); // tên sản phẩm
                    sanPham.setImg(cursor.getString(2)); // ảnh
                    sanPham.setStatus(cursor.getInt(3)); // trạng thái
                    sanPham.setPrice(cursor.getInt(4)); // giá
                    arrayList.add(sanPham);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return arrayList;
    }

    // Lấy chi tiết sản phẩm
    public SanPham getSPChiTiet(int spId) {
        SanPham sanPham = null;
        Cursor cursor = null;
        Cursor cursorChiTiet = null;

        try {
            // Lấy dữ liệu từ bảng sản phẩm
            cursor = db.rawQuery("SELECT * FROM sanpham WHERE sp_id = ?", new String[]{String.valueOf(spId)});
            if (cursor != null && cursor.moveToFirst()) {
                sanPham = new SanPham();
                sanPham.setSpId(cursor.getInt(0)); // sp_id
                sanPham.setTenSp(cursor.getString(1)); // tên sản phẩm
                sanPham.setImg(cursor.getString(2)); // ảnh sản phẩm
                sanPham.setStatus(cursor.getInt(3)); // trạng thái
                sanPham.setPrice(cursor.getInt(4)); // giá sản phẩm
            }

            // Lấy dữ liệu từ bảng chi tiết sản phẩm
            if (sanPham != null) { // Nếu sản phẩm tồn tại
                cursorChiTiet = db.rawQuery("SELECT * FROM chitietsp WHERE sp_id = ?", new String[]{String.valueOf(spId)});
                if (cursorChiTiet != null && cursorChiTiet.moveToFirst()) {
                    sanPham.setDescription(cursorChiTiet.getString(2)); // mô tả
                    sanPham.setSize(cursorChiTiet.getString(3)); // kích cỡ
                    sanPham.setColors(cursorChiTiet.getString(4)); // màu sắc
                    sanPham.setSoLuong(cursorChiTiet.getInt(5)); // số lượng
                }
            }
        } finally {
            if (cursor != null) cursor.close();
            if (cursorChiTiet != null) cursorChiTiet.close();
        }

        return sanPham;
    }
    public ArrayList<String> getAllColors(int spId) {
        ArrayList<String> colors = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Truy vấn để lấy tất cả các màu sắc của sản phẩm
            cursor = db.rawQuery("SELECT DISTINCT color FROM chitietsp WHERE sp_id = ?", new String[]{String.valueOf(spId)});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String color = cursor.getString(0);
                    colors.add(color); // Thêm màu vào danh sách
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
        }

        return colors;
    }

    public ArrayList<String> getAllSizes(int spId) {
        ArrayList<String> sizes = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Truy vấn để lấy tất cả các kích thước của sản phẩm
            cursor = db.rawQuery("SELECT DISTINCT size FROM chitietsp WHERE sp_id = ?", new String[]{String.valueOf(spId)});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String size = cursor.getString(0);
                    sizes.add(size); // Thêm kích thước vào danh sách
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) cursor.close();
        }

        return sizes;
    }

}
