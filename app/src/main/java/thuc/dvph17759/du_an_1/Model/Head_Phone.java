package thuc.dvph17759.du_an_1.Model;

import java.io.Serializable;

public class Head_Phone implements Serializable {
    private int id;
    private String img;
    private String name;
    private Integer price;
    private String details;
    private String sale;

    public Head_Phone(int id, String img, String name, Integer price, String details, String sale) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.price = price;
        this.details = details;
        this.sale = sale;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
