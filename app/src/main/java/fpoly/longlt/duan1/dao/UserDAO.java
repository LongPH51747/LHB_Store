
package fpoly.longlt.duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        long result = sqLiteDatabase.insert("user",null,contentValues);
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
        if (result == -1){
            return false;
        }
        return true;
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

}
