package com.example.motorshop.datasrc;

import java.util.ArrayList;

public class BoPhan {
    private String maBP;
    private String tenBP;

    public BoPhan() { }

    public BoPhan(String maBP, String tenBP) {
        this.maBP = maBP;
        this.tenBP = tenBP;
    }

    public String getMaBP() {
        return maBP;
    }

    public void setMaBP(String maBP) {
        this.maBP = maBP;
    }

    public String getTenBP() {
        return tenBP;
    }

    public void setTenBP(String tenBP) {
        this.tenBP = tenBP;
    }

    public ArrayList<BoPhan> initBoPhanDB(){
        ArrayList<BoPhan> list = new ArrayList<>();
        list.add(new BoPhan("BH", "Bán Hàng"));
        list.add(new BoPhan("BD", "Bảo Dưỡng"));
        list.add(new BoPhan("GD", "Giám Đốc"));
        return list;
    }
}
