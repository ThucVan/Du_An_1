package thuc.dvph17759.du_an_1.Model;

import java.util.List;

public class title {
    String name;
    List<Phone> phoneList;

    public title(String name, List<Phone> phoneList) {
        this.name = name;
        this.phoneList = phoneList;
    }

    public title() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }
}
