package fpoly.longlt.duan1.model;

public class GioHang {
    private int user_id;
    private int sp_id;
    private int quantity;
    private int price;
    private int total_price;

    public GioHang() {
    }

    public GioHang(int user_id, int sp_id, int quantity, int price, int total_price) {
        this.user_id = user_id;
        this.sp_id = sp_id;
        this.quantity = quantity;
        this.price = price;
        this.total_price = total_price;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSp_id() {
        return sp_id;
    }

    public void setSp_id(int sp_id) {
        this.sp_id = sp_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
