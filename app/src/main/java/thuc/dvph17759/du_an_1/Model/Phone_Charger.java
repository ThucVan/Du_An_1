package thuc.dvph17759.du_an_1.Model;

import java.io.Serializable;

public class Phone_Charger implements Serializable {
    private int id;
    private String img;
    private String tenhang;
    private String tensp;
    private Integer gia;
    private String chitiet;

    public Phone_Charger(int id, String img, String tenhang, String tensp, Integer gia, String chitiet) {
        this.id = id;
        this.img = img;
        this.tenhang = tenhang;
        this.tensp = tensp;
        this.gia = gia;
        this.chitiet = chitiet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }
}
