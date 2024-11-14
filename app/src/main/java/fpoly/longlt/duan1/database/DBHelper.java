package fpoly.longlt.duan1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "myDB";
    public static final int DATABASE_VERSION = 1;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUser = "create table user(" +
                "user_id integer primary key autoincrement," +
                " username text," +
                " password text," +
                "name text," +
                "sdt text," +
                "address text default 'ha noi'," +
                "moneyonl integer default 0," +
                "role integer default 0)";
        db.execSQL(createUser);
        String createSP = "create table sanpham(" +
                "sp_id integer primary key autoincrement," +
                "tensp text," +
                "img text," +
                "status bit default 1," +
                "price integer)";
        db.execSQL(createSP);
        String createChiTietSP = "create table chitietsp(" +
                "chitietsp_id integer primary key autoincrement," +
                "sp_id integer references sanpham," +
                "description text," +
                "size text," +
                "color text," +
                "soluong integer)";
        db.execSQL(createChiTietSP);
        String createOrder = "create table orders(" +
                "od_id integer primary key autoincrement," +
                "user_id integer references user," +
                "vc_id integer references voucher," +
                "oddetail_id integer references orderdetail," +
                "od_date text," +
                "total_price integer," +
                "status integer)";
        db.execSQL(createOrder);
        String createOrderDetail = "create table orderdetail(" +
                "oddetail_id integer primary key autoincrement," +
                "od_id integer references orders," +
                "sp_id integer references sanpham," +
                "quantity integer," +
                "price integer)";
        db.execSQL(createOrderDetail);
        String createVoucher = "create table voucher(" +
                "vc_id integer primary key autoincrement," +
                "od_id integer references orders," +
                "code text," +
                "discount_price integer," +
                "start_date text," +
                "end_date text)";
        db.execSQL(createVoucher);
        String addAdmin = ("insert into user values" +
                "(0,'admin','admin123','Vien Kiem Soat','0971297489','Ha Noi',0,1)"
        );
        db.execSQL(addAdmin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists user");
            db.execSQL("drop table if exists sanpham");
            db.execSQL("drop table if exists chitietsp");
            db.execSQL("drop table if exists orders");
            db.execSQL("drop table if exists orderdetail");
            db.execSQL("drop table if exists voucher");
            onCreate(db);
        }
    }
}
