package fpoly.longlt.duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.longlt.duan1.database.DBHelper;
import fpoly.longlt.duan1.model.GioHang;

public class CartDAO {
    private DBHelper fileHelper;
    private SQLiteDatabase db;

    public CartDAO(Context context){
        fileHelper = new DBHelper(context);
        db = fileHelper.getWritableDatabase();
    }

    public boolean addToCart(int user_id, int sp_id, int quantity, int price) {
        // Truy vấn kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
        Cursor cursor = db.rawQuery("SELECT * FROM cart WHERE user_id = ? AND sp_id = ?",
                new String[]{String.valueOf(user_id), String.valueOf(sp_id)});

        boolean result = false;

        if (cursor.moveToFirst()) {
            // Lấy số lượng hiện tại từ cursor
            int currentQuantity = cursor.getInt(3);
            int newQuantity = currentQuantity + quantity;
            int newTotalPrice = newQuantity * price;

            // Cập nhật số lượng và tổng tiền
            ContentValues values = new ContentValues();
            values.put("quantity", newQuantity);
            values.put("total_price", newTotalPrice);

            int rowsUpdated = db.update("cart", values, "user_id = ? AND sp_id = ?",
                    new String[]{String.valueOf(user_id), String.valueOf(sp_id)});
            result = rowsUpdated > 0;
        } else {
            // Thêm sản phẩm mới vào giỏ hàng
            ContentValues values = new ContentValues();
            values.put("user_id", user_id);
            values.put("sp_id", sp_id);
            values.put("quantity", quantity);
            values.put("price", price);
            values.put("total_price", price * quantity);

            long rowInserted = db.insert("cart", null, values);
            result = rowInserted != -1;
        }

        cursor.close();
        return result; // Trả về true nếu thành công, false nếu thất bại
    }

    public boolean delete(int user_id, int sp_id){
        long check = db.delete("cart", "user_id = ? AND sp_id = ?",
                new String[]{String.valueOf(user_id), String.valueOf(sp_id)});
        return check != -1;
    }

    public ArrayList<GioHang> getAll(int user_id) {
        ArrayList<GioHang> arrayList = new ArrayList<>();

        // Truy vấn SQL với điều kiện WHERE
        Cursor cursor = db.rawQuery("SELECT * FROM cart WHERE user_id = ?",
                new String[]{String.valueOf(user_id)});

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst(); // Di chuyển con trỏ đến dòng đầu tiên
            do {
                // Tạo đối tượng GioHang và gán giá trị
                GioHang gioHang = new GioHang();
                gioHang.setSp_id(cursor.getInt(2));
                gioHang.setQuantity(cursor.getInt(3));
                gioHang.setPrice(cursor.getInt(4));
                gioHang.setTotal_price(cursor.getInt(5));

                // Thêm vào danh sách
                arrayList.add(gioHang);
            } while (cursor.moveToNext()); // Lặp qua các dòng tiếp theo
            cursor.close(); // Đóng Cursor
        }

        return arrayList; // Trả về danh sách giỏ hàng
    }


}
