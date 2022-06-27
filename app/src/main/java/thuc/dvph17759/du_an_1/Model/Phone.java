package thuc.dvph17759.du_an_1.Model;

public class Phone {
    private int id;
    private int img;
    private String name;
    private String price;
    private String details;
    private String sale;

    public Phone(int id, int img, String name, String price, String details, String sale) {
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

    public Phone() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
