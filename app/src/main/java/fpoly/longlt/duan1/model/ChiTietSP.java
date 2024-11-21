package fpoly.longlt.duan1.model;

public class ChiTietSP {
    int chitietSP_id, sp_id, soluong;
    String mota, size, color;

    public int getChitietSP_id() {
        return chitietSP_id;
    }

    public void setChitietSP_id(int chitietSP_id) {
        this.chitietSP_id = chitietSP_id;
    }

    public int getSp_id() {
        return sp_id;
    }

    public void setSp_id(int sp_id) {
        this.sp_id = sp_id;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ChiTietSP() {
    }

    public ChiTietSP(int chitietSP_id, int sp_id, int soluong, String mota, String size, String color) {
        this.chitietSP_id = chitietSP_id;
        this.sp_id = sp_id;
        this.soluong = soluong;
        this.mota = mota;
        this.size = size;
        this.color = color;
    }
}
