package com.example.motorshop.datasrc;

import java.util.ArrayList;

public class ChiTietBaoHanh extends BaoHanh{
    private ArrayList<DanhSachSanPhamBaoHanh> danhSachSPBH = new ArrayList<>();

    public ChiTietBaoHanh() { }

    public ChiTietBaoHanh(String maBH, String maDH, String ngayBH, String maNV, ArrayList<DanhSachSanPhamBaoHanh> danhSachSPBH) {
        super(maBH, maDH, ngayBH, maNV);
        this.danhSachSPBH = danhSachSPBH;
    }

    public ArrayList<DanhSachSanPhamBaoHanh> getDanhSachSPBH() {
        return danhSachSPBH;
    }

    public void setDanhSachSPBH(ArrayList<DanhSachSanPhamBaoHanh> danhSachSPBH) {
        this.danhSachSPBH = danhSachSPBH;
    }
}
