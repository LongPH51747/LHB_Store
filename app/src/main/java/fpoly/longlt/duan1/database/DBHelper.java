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
        // Tạo bảng `user`
        String createUser = "CREATE TABLE user(" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "password TEXT," +
                "name TEXT," +
                "sdt TEXT," +
                "address TEXT DEFAULT 'Ha Noi'," +
                "moneyonl INTEGER DEFAULT 0," +
                "role INTEGER DEFAULT 0," +
                "status BIT DEFAULT 1," +
                "imgavatar TEXT)";
        db.execSQL(createUser);

        // Tạo bảng `sanpham`
        String createSP = "CREATE TABLE sanpham(" +
                "sp_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tensp TEXT," +
                "img TEXT," +
                "status BIT DEFAULT 1," +
                "price INTEGER," +
                "description TEXT)";
        db.execSQL(createSP);

        // Tạo bảng `chitietsp`
        String createChiTietSP = "CREATE TABLE chitietsp(" +
                "chitietsp_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sp_id INTEGER REFERENCES sanpham," +
                "size TEXT," +
                "color TEXT," +
                "soluong INTEGER)";
        db.execSQL(createChiTietSP);

        // Tạo bảng `bills`
        String createBills = "CREATE TABLE bills(" +
                "od_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER REFERENCES user," +
                "vc_id INTEGER REFERENCES voucher," +
                "chitietsp_id INTEGER REFERENCES chitietsp," +
                "oddetail_id INTEGER REFERENCES orderdetail," +
                "od_date DATE NOT NULL," +
                "status INTEGER)";
        db.execSQL(createBills);

        // Tạo bảng `orderdetail`
        String createOrderDetail = "CREATE TABLE orderdetail(" +
                "oddetail_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "od_id INTEGER REFERENCES bills," +
                "sp_id INTEGER REFERENCES sanpham," +
                "quantity INTEGER," +
                "price INTEGER)";
        db.execSQL(createOrderDetail);

        // Tạo bảng `voucher`
        String createVoucher = "CREATE TABLE voucher(" +
                "vc_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "code TEXT," +
                "discount_price INTEGER," +
                "start_date TEXT," +
                "end_date TEXT," +
                "dieukien INTEGER," +
                "status BIT DEFAULT 0)";
        db.execSQL(createVoucher);

        // Tạo bảng `cart`
        String createCart = "CREATE TABLE cart(" +
                "cart_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER REFERENCES user," +
                "sp_id INTEGER REFERENCES sanpham," +
                "quantity INTEGER DEFAULT 1," +
                "price INTEGER," +
                "total_price INTEGER," +
                "img_path TEXT," +
                "color TEXT," +
                "size TEXT," +
                "status INTEGER)";
        db.execSQL(createCart);

        // Thêm dữ liệu mẫu
        addSampleData(db);
    }

    private void addSampleData(SQLiteDatabase db) {
        // Dữ liệu mẫu cho `user`
        String insertUser = "INSERT INTO user(username, password, name, sdt, address, role, status, imgavatar) VALUES " +
                "('admin','admin123','Vien Kiem Soat','0971297489','Ha Noi',1,1,'img_1')," +
                "('hai','123','HaiViet','0971296368','Ha Tay',0,1,'1234')," +
                "('bao','123','GiaBao','0987654412','Ha Nam',0,1,'345')," +
                "('long','123','ThanhLong','091234567','Ha Dong',0,1,'678')";
        db.execSQL(insertUser);

        // Dữ liệu mẫu cho `sanpham`
        String insertSP = "INSERT INTO sanpham(tensp, img, status, price, description) VALUES " +
                "('áo khoác gió nam phong cách hàn quốc', 'img_2', 1, 10000, 'chất liệu cotton siêu mát')," +
                "('Quần jean trắng', 'img_3', 1, 20000, 'vải nhung tăm dày dặn, thoải mái, ấm áp')";
        db.execSQL(insertSP);

        // Dữ liệu mẫu cho `chitietsp`
        String insertChiTietSP = "INSERT INTO chitietsp(sp_id, size, color, soluong) VALUES " +
                "(1, 'M', 'Red', 100)," +
                "(1, 'L', 'White', 70)," +
                "(1, 'L', 'Red', 70)," +
                "(1, 'S', 'Blue', 50)," +
                "(1, 'M', 'Black', 60)," +
                "(1, 'L', 'White', 40)," +
                "(2, 'XL', 'Black', 30)," +
                "(2, 'M', 'White', 60)," +
                "(2, 'L', 'Black', 40)," +
                "(2, 'X', 'Gray', 24)";
        db.execSQL(insertChiTietSP);

        // Dữ liệu mẫu cho `bills`
        String insertBills = "INSERT INTO bills(user_id, vc_id, chitietsp_id, oddetail_id, od_date, status) VALUES " +
                "(1, NULL, 1, NULL, '2024-11-11', 0)";
        db.execSQL(insertBills);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS user");
            db.execSQL("DROP TABLE IF EXISTS sanpham");
            db.execSQL("DROP TABLE IF EXISTS chitietsp");
            db.execSQL("DROP TABLE IF EXISTS bills");
            db.execSQL("DROP TABLE IF EXISTS orderdetail");
            db.execSQL("DROP TABLE IF EXISTS voucher");
            db.execSQL("DROP TABLE IF EXISTS cart");
            onCreate(db);
        }
    }
}
