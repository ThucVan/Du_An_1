package thuc.dvph17759.du_an_1.Model;

import java.io.Serializable;

public class OpDienThoai implements Serializable {
    private int id;
    private String img;
    private String name;
    private Integer price;
    private String mota;

    public OpDienThoai(int id, String img, String name, Integer price, String mota) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.price = price;
        this.mota = mota;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
