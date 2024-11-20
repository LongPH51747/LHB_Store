
package fpoly.longlt.duan1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.longlt.duan1.R;
import fpoly.longlt.duan1.database.DBHelper;
import fpoly.longlt.duan1.model.User;


public class UserDAO {
    private DBHelper dbHelper;

    public UserDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean insertUser(User user) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUserName());
        contentValues.put("password", user.getPassWord());
        contentValues.put("name", user.getNameUser());
        contentValues.put("sdt", user.getPhoneNumber());
        contentValues.put("address", user.getAddress());
        contentValues.put("status", user.getStatus());
        contentValues.put("imageAvatar", user.getImageAvatar());
        long result = sqLiteDatabase.insert("user", null, contentValues);
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
        if (result == -1) {
            return false;
        }
        return true;
    }

    public boolean checkLogInAdmin(String userName, String passWord) {
        int role = 1;
        String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND role = ?";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{userName, passWord, String.valueOf(role)});
        boolean result = (cursor.getCount() > 0);
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
        return result;
    }

    public boolean checkLoginUser(String useName, String passWord) {
        int role = 0;
        String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND role = ?";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{useName, passWord, String.valueOf(role)});
        boolean result = (cursor.getCount() > 0);
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
        return result;
    }

    public boolean deleteUser(int id_user) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        int result = sqLiteDatabase.delete("user", "user_id = ?", new String[]{String.valueOf(id_user)});
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean changePassWord(User user) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", user.getPassWord());
        int result = sqLiteDatabase.update("user", contentValues, "user_id = ?", new String[]{String.valueOf(user.getId_user())});
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
        if (result == -1) {
            return false;
        }
        return true;
    }

    public boolean addAddress(User user) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", user.getAddress());
        int result = sqLiteDatabase.update("user", contentValues, "user_id = ?", new String[]{String.valueOf(user.getId_user())});
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
        if (result == -1) {
            return false;
        }
        return true;
    }

    public int getIDByLogIn(String name, String pass) {
        int role = 0;
        String sql = "SELECT user_id FROM user WHERE username = ? AND password = ? AND role = ?";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{name, pass, String.valueOf(role)});
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
            cursor.close();
            sqLiteDatabase.close();
            return id;
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
        // Neu ko tim thay tra ve -1
        return -1;
    }
    public String getNameUserByID(int id){
        int role = 0;
        String sql = "SELECT name FROM user WHERE user_id = ? AND role = ?";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,new String[]{String.valueOf(id),String.valueOf(role)});
        if (cursor != null && cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            cursor.close();
            sqLiteDatabase.close();
            return name;
        }
        if (cursor != null && !cursor.isClosed()){
            cursor.close();
        }
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
        // tra ve chuoi rong
        return null;
    }

    @SuppressLint("Range")
    public ArrayList<User> getData(String sql, String... selectionArgs) {
        ArrayList<User> userArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        try {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    User user = new User();
                    user.setId_user(Integer.parseInt(cursor.getString(cursor.getColumnIndex("user_id"))));
                    user.setUserName(cursor.getString(cursor.getColumnIndex("username")));
                    user.setPassWord(cursor.getString(cursor.getColumnIndex("password")));
                    user.setNameUser(cursor.getString(cursor.getColumnIndex("name")));
                    user.setPhoneNumber(cursor.getString(cursor.getColumnIndex("sdt")));
                    user.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                    user.setMoneyOnl(Integer.parseInt(cursor.getString(cursor.getColumnIndex("moneyonl"))));
                    user.setRole(Integer.parseInt(cursor.getString(cursor.getColumnIndex("role"))));
                    userArrayList.add(user);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
        }
        return userArrayList;
    }

    public ArrayList<User> getALl() {
        String sql = "SELECT * FROM user";
        return getData(sql);
    }
}
