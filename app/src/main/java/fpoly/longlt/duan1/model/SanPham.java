package fpoly.longlt.duan1.model;

public class SanPham {
    private int spId;
    private String tenSp;
    private int img;
    private int status;
    private int price;

    public SanPham(int spId, String tenSp, int img, int status, int price) {
        this.spId = spId;
        this.tenSp = tenSp;
        this.img = img;
        this.status = status;
        this.price = price;
    }

    public SanPham(String tenSp, int img, int price) {
        this.tenSp = tenSp;
        this.img = img;
        this.price = price;
    }

    public int getSpId() {
        return spId;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

