
package fpoly.longlt.duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fpoly.longlt.duan1.database.DBHelper;
import fpoly.longlt.duan1.model.User;




public class UserDAO {
    private DBHelper dbHelper;

    public UserDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public boolean insertUser(User user){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUserName());
        contentValues.put("password", user.getPassWord());
        contentValues.put("name", user.getNameUser());
        contentValues.put("sdt", user.getPhoneNumber());
        contentValues.put("address", user.getAddress());
        contentValues.put("status", user.getStatus());
        contentValues.put("imgavatar", user.getImg());
        long result = sqLiteDatabase.insert("user",null,contentValues);
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
        if (result == -1){
            return false;
        }
        return true;
    }
    public ArrayList<User> getAllUser(){
        ArrayList<User> lst = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user", null);
        if (cursor.getCount()>1){
            cursor.moveToPosition(1);
            do {
                User user = new User();
                user.setId_user(cursor.getInt(0));
                user.setUserName(cursor.getString(1));
                user.setPassWord(cursor.getString(2));
                user.setNameUser(cursor.getString(3));
                user.setPhoneNumber(cursor.getString(4));
                user.setAddress(cursor.getString(5));
                user.setMoneyOnl(cursor.getInt(6));
                user.setRole(cursor.getInt(7));
                user.setStatus(cursor.getInt(8));
                user.setImg(cursor.getString(9));
                lst.add(user);
            } while (cursor.moveToNext());
        }
        return lst;
    }
    public boolean checkLogInAdmin(String userName, String passWord){
        int role = 1;
        String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND role = ?";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,new String[]{userName,passWord,String.valueOf(role)});
        boolean result = (cursor.getCount() > 0);
        if (cursor != null && !cursor.isClosed()){
            cursor.close();
        }
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
        return result;
    }

    public boolean checkLoginUser(String useName, String passWord){
        int role = 0;
        String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND role = ?";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,new String[]{useName,passWord,String.valueOf(role)});
        boolean result = (cursor.getCount() > 0);
        if (cursor != null && !cursor.isClosed()){
            cursor.close();
        }
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
        return result;
    }

    public int getStatus(String useName, String passWord) {
        int status = 0;
        String sql = "select status from user where username = ? and password = ?";
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(sql,new String[]{useName,passWord});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            status = cursor.getInt(0);
            Log.d("db","status"+status+""+cursor.getCount());
        }
        return status;
    }

    public boolean deleteUser(int id_user){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        int result = sqLiteDatabase.delete("user","user_id = ?", new String[]{String.valueOf(id_user)});
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean changePassWord(User user){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password",user.getPassWord());
        int result = sqLiteDatabase.update("user",contentValues,"user_id = ?",new String[]{String.valueOf(user.getId_user())});
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
        if (result == -1){
            return false;
        }
        return true;
    }

    public boolean addAddress(User user){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", user.getAddress());
        int result = sqLiteDatabase.update("user",contentValues,"user_id = ?",new String[]{String.valueOf(user.getId_user())});
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
        if (result == -1){
            return false;
        }
        return true;
    }

    public boolean getIDUserByLogIn(String userName, String passWord){
        int role = 0;
        String sql = "SELECT user_id FROM user WHERE username = ? AND password = ? AND role = ?";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,new String[]{userName,passWord,String.valueOf(role)});
        boolean result = (cursor.getCount() > 0);
        if (cursor != null && !cursor.isClosed()){
            cursor.close();
        }
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
        return result;
    }

    public boolean updateStatus(int id, boolean check){
        int status = check ?1:0;

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        int result = sqLiteDatabase.update("user", values, "user_id = ?", new String[]{String.valueOf(id)});
        return result != -1;
    }

}
