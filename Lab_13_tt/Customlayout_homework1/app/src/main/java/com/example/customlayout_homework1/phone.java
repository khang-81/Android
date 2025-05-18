package com.example.customlayout_homework1;

public class phone {
    private String namephone;
    private int imagephone;
    private String giaban;

    public phone(String namephone, int imagephone, String giaban) {
        this.namephone = namephone;
        this.imagephone = imagephone;
        this.giaban = giaban;
    }

    public String getNamephone() {
        return namephone;
    }

    public void setNamephone(String namephone) {
        this.namephone = namephone;
    }

    public int getImagephone() {
        return imagephone;
    }

    public void setImagephone(int imagephone) {
        this.imagephone = imagephone;
    }

    public String getGiaban() {
        return giaban;
    }

    public void setGiaban(String giaban) {
        this.giaban = giaban;
    }
}
