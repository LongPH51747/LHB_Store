package fpoly.longlt.duan1.model;

import java.util.Date;

public class DonHang {
    private int od_id, user_id, vc_id, odDetail_id, total_price, status;
    private Date od_date;

    public DonHang(int user_id, int vc_id, int odDetail_id, int total_price, int status, Date od_date) {
        this.user_id = user_id;
        this.vc_id = vc_id;
        this.odDetail_id = odDetail_id;
        this.total_price = total_price;
        this.status = status;
        this.od_date = od_date;
    }

    public DonHang() {
    }

    public int getOd_id() {
        return od_id;
    }

    public void setOd_id(int od_id) {
        this.od_id = od_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVc_id() {
        return vc_id;
    }

    public void setVc_id(int vc_id) {
        this.vc_id = vc_id;
    }

    public int getOdDetail_id() {
        return odDetail_id;
    }

    public void setOdDetail_id(int odDetail_id) {
        this.odDetail_id = odDetail_id;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getOd_date() {
        return od_date;
    }

    public void setOd_date(Date od_date) {
        this.od_date = od_date;
    }
}
