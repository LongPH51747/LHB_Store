package fpoly.longlt.duan1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "myDB";
    public static final int DATABASE_VERSION = 10;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUser = "create table user(" +
                "user_id integer primary key autoincrement," +
                "username text," +
                "password text," +
                "name text," +
                "sdt text," +
                "address text default 'ha noi'," +
                "moneyonl integer default 0," +
                "role integer default 0," +
                "status bit default 1," +
                "imgavatar text)";
        db.execSQL(createUser);
        String createSP = "create table sanpham(" +
                "sp_id integer primary key autoincrement," +
                "tensp text," +
                "img text," +
                "status bit default 1," +
                "price integer," +
                "description text)";
        db.execSQL(createSP);
        String createChiTietSP = "create table chitietsp(" +
                "chitietsp_id integer primary key autoincrement," +
                "sp_id integer references sanpham," +
                "size text," +
                "color text," +
                "soluong integer)";
        db.execSQL(createChiTietSP);
        String insertIntoSP = "INSERT INTO sanpham VALUES " +
                "(0,'áo khoác gió nam phong cách hàn quốc', 'img_2', 1, 10000, 'chất liệu cotton siêu mát')," +
                "(1,'Quần jean trắng', 'img_3', 1, 20000, 'vải nhung tăm dày dặn, thoải mái, ấm áp');";
        db.execSQL(insertIntoSP);

        String createBills = "create table bills(" +
                "od_id integer primary key autoincrement," +
                "user_id integer references user," +
                "vc_id integer references voucher," +
                "chitietsp_id integer references chitietsp," +
                "oddetail_id integer references orderdetail," +
                "od_date date not null," +
                "status integer)";
        db.execSQL(createBills);
        String createOrderDetail = "create table orderdetail(" +
                "oddetail_id integer primary key autoincrement," +
                "od_id integer references orders," +
                "sp_id integer references sanpham," +
                "quantity integer," +
                "price integer)";
        db.execSQL(createOrderDetail);
        String createVoucher = "create table voucher(" +
                "vc_id integer primary key autoincrement," +
                "code text," +
                "discount_price integer," +
                "start_date text," +
                "end_date text," +
                "dieukien integer," +
                "status bit default 0)";
        db.execSQL(createVoucher);

        // Them cac du lieu fix cung de test o day
        String addAdmin = ("insert into user values" +
                "(0,'admin','admin123','Vien Kiem Soat','0971297489','Ha Noi',0,1,1,'img_1')"
        );
        db.execSQL(addAdmin);
        String insertUser = ("insert into user values" +
                "(1,'hai','123','HaiViet','0971296368','Ha Tay',1,0,1,'1234')," +
                "(2,'bao','123','GiaBao','0987654412','Ha Nam',1,0,1,'345')," +
                "(3,'long','123','ThanhLong','091234567','Ha Dong',1,0,1,'678')"
        );
        db.execSQL(insertUser);
        String createCart = "create table cart(" +
                "cart_id integer primary key autoincrement," +  // ID tự tăng cho từng mục trong giỏ hàng
                "user_id integer references user," +            // ID người dùng, liên kết với bảng users (nếu có)
                "sp_id integer references sanpham," +           // ID sản phẩm, liên kết với bảng sản phẩm
                "quantity integer default 1," +                 // Số lượng sản phẩm, mặc định là 1
                "price integer," +                              // Giá của một sản phẩm
                "total_price integer" +                         // Tổng giá = quantity * price
                ")";
        db.execSQL(createCart);
        String insertIntoChiTietSP = "INSERT INTO chitietsp VALUES" +
                "(0, 0, 'M', 'Red', 100)," +
                "(1, 0, 'L', 'White', 70)," +
                "(2, 0, 'L', 'Red', 70)," +
                "(3, 0, 'S', 'Blue', 50)," +
                "(4, 0, 'M', 'Black', 60)," +
                "(5, 0,'L', 'White', 40)," +
                "(6, 0,'XL', 'Black', 30)," +
                "(7, 1,'M', 'White', 60)," +
                "(8, 1,'L', 'Black', 40)," +
                "(9, 1,'X', 'Gray', 24)," +
                "(10, 1,'XL', 'Beige', 16)," +
                "(11, 1,'M', 'Blue', 08)," +
                "(12, 1,'L', 'Black-Blue', 77)";
        db.execSQL(insertIntoChiTietSP);
        String insertBills = "INSERT INTO bills VALUES" +
                "(0,1,1,1,1,'11/11/2024',0)";
        db.execSQL(insertBills);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("drop table if exists user");
            db.execSQL("drop table if exists sanpham");
            db.execSQL("drop table if exists chitietsp");
            db.execSQL("drop table if exists bills");
            db.execSQL("drop table if exists orderdetail");
            db.execSQL("drop table if exists voucher");
            db.execSQL("drop table if exists cart");
            onCreate(db);
        }
    }
}
