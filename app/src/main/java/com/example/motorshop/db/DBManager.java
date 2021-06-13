package com.example.motorshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.login.LoginActivity;
import com.example.motorshop.activity.product.QuanLyPhuTungActivity;
import com.example.motorshop.activity.product.QuanLyXeActivity;
import com.example.motorshop.datasrc.BaoHanh;
import com.example.motorshop.datasrc.BoPhan;
import com.example.motorshop.datasrc.ChiTietSanPhamDonHang;
import com.example.motorshop.datasrc.DanhSachSanPhamBaoHanh;
import com.example.motorshop.datasrc.DonHang;
import com.example.motorshop.datasrc.KhachHang;
import com.example.motorshop.datasrc.NhaCungCap;
import com.example.motorshop.datasrc.NhanVien;
import com.example.motorshop.datasrc.PhuTung;
import com.example.motorshop.datasrc.SanPham;
import com.example.motorshop.datasrc.ThongSoPhuTung;
import com.example.motorshop.datasrc.ThongSoXe;
import com.example.motorshop.datasrc.Xe;
import com.example.motorshop.datasrc.*;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {


    private static final String TAG = "DBManager";

    public DBManager(Context context) {
        super(context, "dbMOTORSTORE.db", null, 1);
        Log.d(TAG,"Create DB: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ArrayList<String> createTables = new ArrayList<>();
        createTables.add("CREATE TABLE IF NOT EXISTS BOPHAN (MABP text PRIMARY KEY, TENBP text not null)");
        createTables.add("CREATE TABLE IF NOT EXISTS NHANVIEN(MANV text PRIMARY KEY, HOTEN text not null, SDT text not null, MABP text not null, CONSTRAINT FK_NHANVIEN_BOPHAN FOREIGN KEY (MABP) REFERENCES BOPHAN(MABP))");
        createTables.add("CREATE TABLE IF NOT EXISTS KHACHHANG (CMND text PRIMARY KEY, HOTEN text not null, DIACHI text not null, SDT text null)");
        createTables.add("CREATE TABLE IF NOT EXISTS NHACUNGCAP (MANCC text PRIMARY KEY, TENNCC text not null, DIACHI text not null, SDT text not null, EMAIL text null, LOGO int not null)");
        createTables.add("CREATE TABLE IF NOT EXISTS XE (MAXE text PRIMARY KEY, TENXE text not null, SOLUONG int not null, DONGIA int not null, HANBAOHANH int not null, HINHANH BLOB not null, MANCC text not null, CONSTRAINT FK_XE_NHACUNGCAP FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC))");
        createTables.add("CREATE TABLE IF NOT EXISTS PHUTUNG (MAPT text PRIMARY KEY, TENPT text not null, SOLUONG int not null, DONGIA int not null, HANBAOHANH int not null, HINHANH BLOB not null, MANCC text not null, CONSTRAINT FK_PHUTUNG_NHACUNGCAP FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC))");
        createTables.add("CREATE TABLE IF NOT EXISTS THONGSOXE (MATS integer PRIMARY KEY AUTOINCREMENT, TENTS text not null)");
        createTables.add("CREATE TABLE IF NOT EXISTS CHITIETTHONGSOXE (MAXE text not null, MATS int not null, NOIDUNGTS text not null, CONSTRAINT FK_CHITIETTHONGSOXE_XE FOREIGN KEY (MAXE) REFERENCES XE(MAXE), CONSTRAINT FK_CHITIETTHONGSOXE_THONGSOXE FOREIGN KEY (MATS) REFERENCES THONGSOXE(MATS), PRIMARY KEY (MAXE, MATS))");
        createTables.add("CREATE TABLE IF NOT EXISTS THONGSOPHUTUNG (MAPT text not null, MAXE text not null, DONGIA int not null, CONSTRAINT FK_THONGSOPHUTUNG_PHUTUNG FOREIGN KEY (MAPT) REFERENCES PHUTUNG(MAPT), CONSTRAINT FK_THONGSOPHUTUNG_XE FOREIGN KEY (MAXE) REFERENCES XE(MAXE), PRIMARY KEY (MAPT, MAXE))");
        createTables.add("CREATE TABLE IF NOT EXISTS DONDATHANG (MADH text PRIMARY KEY, NGAYDAT text not null, CMND text not null, MANV text not null, CONSTRAINT FK_DONDATHANG_KHACHHANG FOREIGN KEY (CMND) REFERENCES KHACHHANG(CMND), CONSTRAINT FK_DONDATHANG_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV))");
        createTables.add("CREATE TABLE IF NOT EXISTS CHITIETDONDATXE (MADH text not null, MAXE text not null, SOLUONG int not null, DONGIABAN int not null, CONSTRAINT FK_CHITIETDONDATXE_DONDATHANG FOREIGN KEY (MADH) REFERENCES DONDATHANG(MADH), CONSTRAINT FK_CHITIETDONDATXE_XE FOREIGN KEY (MAXE) REFERENCES XE(MAXE), PRIMARY KEY (MADH, MAXE))");
        createTables.add("CREATE TABLE IF NOT EXISTS CHITIETDONDATPHUTUNG (MADH text not null, MAPT text not null, SOLUONG int not null, DONGIABAN int not null, CONSTRAINT FK_CHITIETDONDATPHUTUNG_DONDATHANG FOREIGN KEY (MADH) REFERENCES DONDATHANG(MADH), CONSTRAINT FK_CHITIETDONDATPHUTUNG_PHUTUNG FOREIGN KEY (MAPT) REFERENCES PHUTUNG(MAPT), PRIMARY KEY (MADH, MAPT))");
        createTables.add("CREATE TABLE IF NOT EXISTS BAOHANH (MABH text PRIMARY KEY, MADH text not null, NGAYBH text not null, MANV text not null, CONSTRAINT FK_BAOHANH_DONDATHANG FOREIGN KEY (MADH) REFERENCES DONDATHANG(MADH), CONSTRAINT FK_BAOHANH_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV))");
        createTables.add("CREATE TABLE IF NOT EXISTS CHITIETBAOHANHXE (MABH text not null, MAXE text not null, NOIDUNGBH text not null, PHIBH int null, CONSTRAINT FK_CHITIETBAOHANHXE_BAOHANH FOREIGN KEY (MABH) REFERENCES BAOHANH(MABH), CONSTRAINT FK_CHITIETBAOHANHXE_XE FOREIGN KEY (MAXE) REFERENCES XE(MAXE), PRIMARY KEY (MABH, MAXE, NOIDUNGBH))");
        createTables.add("CREATE TABLE IF NOT EXISTS CHITIETBAOHANHPHUTUNG (MABH text not null, MAPT text not null, NOIDUNGBH text not null, PHIBH int null, CONSTRAINT FK_CHITIETBAOHANHPHUTUNG_BAOHANH FOREIGN KEY (MABH) REFERENCES BAOHANH(MABH), CONSTRAINT FK_CHITIETBAOHANHPHUTUNG_PHUTUNG FOREIGN KEY (MAPT) REFERENCES PHUTUNG(MAPT), PRIMARY KEY (MABH, MAPT, NOIDUNGBH))");

        for(String str : createTables){
            db.execSQL(str);
            Log.d(TAG,"onCreate DB: " + str);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,"onUpgrade DB: ");
    }


    //USED IN COMMONS
    public boolean ifIDExist(String IDColumnName, String tableName, String condition){
        boolean exist = false;
        String query = "select " +IDColumnName+ " from " +tableName+ " where " +IDColumnName+ " = " +condition;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String id_tmp_check = cursor.getString(0);                              //
            cursor.close();
            db.close();
            Log.d(TAG,"Check MaBP ifIDExist: " + IDColumnName + "=" + id_tmp_check);
            return true;
        }else{
            cursor.close();
            db.close();
            Log.d(TAG,"Check MaBP ifIDExist: not exist");
            return false;
        }
    }


    //BO PHAN (DEPARTMENT)
    public void insertDP(BoPhan department){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MABP", department.getMaBP());
        values.put("TENBP", department.getTenBP());
        db.insert("BOPHAN", null, values);
        db.close();
        Log.d(TAG,"Insert DEPARTMENT: ");
    }

    public void updateDP(BoPhan department){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Update  BOPHAN  set ";
        query += " TENBP  = '" + department.getTenBP() + "' ";
        query += " WHERE MABP  = '" + department.getMaBP() + "'";
        db.execSQL(query);
        db.close();
        Log.d(TAG,"Update DEPARTMENT: ");
    }

    public void deleteDP(BoPhan department){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM BOPHAN WHERE MABP = '" + department.getMaBP() + "'";
        db.execSQL(query);
        db.close();
        Log.d(TAG,"Delete DEPARTMENT: ");
    }

    public void deleteDP(String departmentID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM BOPHAN WHERE MABP = '" + departmentID + "'";
        db.execSQL(query);
        db.close();
        Log.d(TAG,"Delete DEPARTMENT with departmentID: ");
    }

    public void loadDPList(ArrayList<BoPhan> departmentList){
        departmentList.clear();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select * from BOPHAN";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                BoPhan department = new BoPhan();
                department.setMaBP(cursor.getString(0));
                department.setTenBP(cursor.getString(1));
                departmentList.add(department);
            } while (cursor.moveToNext());
        }
        Log.d(TAG,"Load DEPARTMENT LIST: ");
    }


    //NHAN VIEN (STAFF)
    public void insertST(NhanVien staff){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MANV", staff.getMaNV());
        values.put("HOTEN", staff.getHoTen());
        values.put("SDT", staff.getSdt());
        values.put("MABP", staff.getMaBP());
        db.insert("NHANVIEN", null, values);
        db.close();
        Log.d(TAG,"Insert STAFF: ");
    }
    public void updateST() { }
    public void deleteST() { }
    public void loadSTList() { }


    //KHACH HANG (CUSTOMER)
    public void insertCTM(KhachHang customer) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CMND", customer.getCmnd());
        values.put("HOTEN", customer.getHoTen());
        values.put("DIACHI", customer.getDiaChi());
        values.put("SDT", customer.getSdt());
        db.insert("KHACHHANG", null, values);
        db.close();
        Log.d(TAG,"Insert CUSTOMER: ");
    }
    public void updateCTM() { }
    public void deleteCTM() { }
    public void loadCTMList() { }


    //NHA CUNG CAP (BRAND)
    public void insertBR(NhaCungCap brand){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MANCC", brand.getMaNCC());
        values.put("TENNCC", brand.getTenNCC());
        values.put("DIACHI", brand.getDiaChi());
        values.put("SDT", brand.getSdt());
        values.put("EMAIL", brand.getEmail());
        values.put("LOGO", brand.getLogo());
        db.insert("NHACUNGCAP", null, values);
        db.close();
        Log.d(TAG,"Insert BRAND: ");
    }

    public void updateBR(NhaCungCap brand){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Update  NHACUNGCAP  set ";
        query += " TENNCC  = '"+ brand.getTenNCC()+"', ";
        query += " DIACHI  = '"+ brand.getDiaChi()+"', ";
        query += " SDT  = '"+ brand.getSdt()+"', ";
        query += " EMAIL  = '"+ brand.getEmail()+"', ";
        query += " LOGO  = '"+ brand.getLogo()+"' ";
        query += " WHERE MANCC  = '"+ brand.getMaNCC()+"'";
        db.execSQL(query);
        db.close();
        Log.d(TAG,"Update BRAND: ");
    }

    public void deleteBR(NhaCungCap brand){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM NHACUNGCAP WHERE MANCC = '" + brand.getMaNCC()+"'";
        db.execSQL(query);
        db.close();
        Log.d(TAG,"Delete BRAND: ");
    }

    public void deleteBR(String brandName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM NHACUNGCAP WHERE TENNCC = '" + brandName + "'";
        db.execSQL(query);
        db.close();
        Log.d(TAG,"Delete BRAND with brandID: ");
    }

    public void loadBRList(ArrayList<NhaCungCap> brandList){
        brandList.clear();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM NHACUNGCAP";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                NhaCungCap brand = new NhaCungCap();
                brand.setMaNCC(cursor.getString(0));
                brand.setTenNCC(cursor.getString(1));
                brand.setDiaChi(cursor.getString(2));
                brand.setSdt(cursor.getString(3));
                brand.setEmail(cursor.getString(4));
                brand.setLogo(cursor.getInt(5));
                brandList.add(brand);
            } while (cursor.moveToNext());
        }
        Log.d(TAG,"Load BRAND: ");
    }


    //XE
    public void insertXe(Xe xe) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MAXE", xe.getMaSP());
        values.put("TENXE", xe.getTenSP());
        values.put("SOLUONG", xe.getSoLuong());
        values.put("DONGIA", xe.getDonGia());
        values.put("HANBAOHANH", xe.getHanBH());
        values.put("HINHANH", xe.getHinhAnh());
        values.put("MANCC", xe.getTenNCC());
        db.insert("XE", null, values);
        db.close();
        Log.d("DBManager", "4.1. InsertXe");
    }

    public void updateXe(Xe xe) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Update  XE  set ";
        sql += "TENXE  = '" + xe.getTenSP() + "' ,  ";
        sql += "SOLUONG  = " + xe.getSoLuong() + " ,  ";
        sql += "DONGIA  = " + xe.getDonGia() + " ,  ";
        sql += "HANBAOHANH  = " + xe.getHanBH() + " ,  ";
        sql += "HINHANH  = '" + xe.getHinhAnh() + "'  , ";
        if (xe.getTenNCC().equals("HD"))
            sql += "MANCC  = '" + "HD" + "' ";
        if (xe.getTenNCC().equals("YM"))
            sql += "MANCC  = '" + "YM" + "' ";
        if (xe.getTenNCC().equals("SY"))
            sql += "MANCC  = '" + "SY" +"' ";
        sql += "  WHERE MAXE  = '" + xe.getMaSP() + "'";

        ContentValues values = new ContentValues();
        values.put("HINHANH", xe.getHinhAnh());

        db.execSQL(sql);
        db.update("XE", values,"MAXE = '" + xe.getMaSP() +"' ",null);
        db.close();
        Log.d("DBManager", "4.2. UpdateXe");
    }

    public void loadXe(ArrayList<Xe> data) {
        data.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from XE";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Xe xe = new Xe();
                xe.setMaSP(cursor.getString(0));
                xe.setTenSP(cursor.getString(1));
                xe.setSoLuong(cursor.getInt(2));
                xe.setDonGia(cursor.getInt(3));
                xe.setHanBH(cursor.getInt(4));
                xe.setHinhAnh(cursor.getBlob(5));
                xe.setTenNCC(cursor.getString(6));
                data.add(xe);
            } while (cursor.moveToNext());
        }
        Log.d("DBManager", "4.3. LoadXe");
    }

    public void deleteXe(Xe xe) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM XE WHERE MAXE='" + xe.getMaSP() + "'";
        db.execSQL(query);
        db.close();
        Log.d("DBManager", "4.4. DeleteXe");
    }

    public ArrayList<Xe> searchXe(String keyword) {
        ArrayList<Xe> xes = null;
        try{
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = "select * from XE where TENXE like ?";
            Cursor cursor = sqLiteDatabase.rawQuery(query, new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()){
                xes = new ArrayList<Xe>();
                do {
                    Xe xe = new Xe();
                    xe.setMaSP(cursor.getString(0));
                    xe.setTenSP(cursor.getString(1));
                    xe.setSoLuong(cursor.getInt(2));
                    xe.setDonGia(cursor.getInt(3));
                    xe.setHanBH(cursor.getInt(4));
                    xe.setHinhAnh(cursor.getBlob(5));
                    xe.setTenNCC(cursor.getString(6));
                    xes.add(xe);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            xes = null;
        }
        return xes;
    }

    public ArrayList<Xe> searchNCC(String keyword) {
        ArrayList<Xe> xes = null;
        try{
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = "select * from XE where MANCC like ?";
            Cursor cursor = sqLiteDatabase.rawQuery(query, new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()){
                xes = new ArrayList<Xe>();
                do {
                    Xe xe = new Xe();
                    xe.setMaSP(cursor.getString(0));
                    xe.setTenSP(cursor.getString(1));
                    xe.setSoLuong(cursor.getInt(2));
                    xe.setDonGia(cursor.getInt(3));
                    xe.setHanBH(cursor.getInt(4));
                    xe.setHinhAnh(cursor.getBlob(5));
                    xe.setTenNCC(cursor.getString(6));
                    xes.add(xe);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            xes = null;
        }
        return xes;
    }


    //PHU TUNG
    public void insertPT(PhuTung phuTung) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MAPT", phuTung.getMaSP());
        values.put("TENPT", phuTung.getTenSP());
        values.put("SOLUONG", phuTung.getSoLuong());
        values.put("DONGIA", phuTung.getDonGia());
        values.put("HANBAOHANH", phuTung.getHanBH());
        values.put("HINHANH", phuTung.getHinhAnh());
        values.put("MANCC", phuTung.getTenNCC());
        db.insert("PHUTUNG", null, values);
        db.close();
        Log.d("DBManager", "5.1. InsertPhuTung");
    }

    public void updatePT(PhuTung phuTung) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Update  PHUTUNG  set ";
        sql += "TENPT  = '" + phuTung.getTenSP() + "' ,  ";
        sql += "SOLUONG  = " + phuTung.getSoLuong() + " ,  ";
        sql += "DONGIA  = " + phuTung.getDonGia() + " ,  ";
        sql += "HANBAOHANH  = " + phuTung.getHanBH() + " ,  ";
        sql += "HINHANH  = '" + phuTung.getHinhAnh() + "' , ";
        if (phuTung.getTenNCC().equals("HD"))
            sql += "MANCC  = '" + "Honda" + "' ";
        if (phuTung.getTenNCC().equals("YM"))
            sql += "MANCC  = '" + "Yamaha" + "' ";
        if (phuTung.getTenNCC().equals("SY"))
            sql += "MANCC  = '" + "SYM" +"' ";
        sql += "  WHERE MAPT  = '" + phuTung.getMaSP() + "'";

        ContentValues values = new ContentValues();
        values.put("HINHANH", phuTung.getHinhAnh());

        db.execSQL(sql);
        db.update("PHUTUNG", values,"MAPT = '" + phuTung.getMaSP() +"' ",null);
        db.close();
        Log.d("DBManager", "5.2. UpdatePhuTung");
    }

    public void loadPT(ArrayList<PhuTung> data) {
        data.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from PHUTUNG";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                PhuTung phuTung = new PhuTung();
                phuTung.setMaSP(cursor.getString(0));
                phuTung.setTenSP(cursor.getString(1));
                phuTung.setSoLuong(cursor.getInt(2));
                phuTung.setDonGia(cursor.getInt(3));
                phuTung.setHanBH(cursor.getInt(4));
                phuTung.setHinhAnh(cursor.getBlob(5));
                phuTung.setTenNCC(cursor.getString(6));
                data.add(phuTung);
            } while (cursor.moveToNext());
        }
        Log.d("DBManager", "5.3. LoadPhuTung");
    }

    public void deletePT(PhuTung phuTung) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM PHUTUNG WHERE MAPT='" + phuTung.getMaSP() + "'";
        db.execSQL(query);
        db.close();
        Log.d("DBManager", "5.4. DeletePhuTung");
    }

    public ArrayList<PhuTung> searchPT(String keyword) {
        ArrayList<PhuTung> phuTungs = null;
        try{
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = "select * from PHUTUNG where TENPT like ?";
            Cursor cursor = sqLiteDatabase.rawQuery(query, new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()){
                phuTungs = new ArrayList<PhuTung>();
                do {
                    PhuTung phuTung = new PhuTung();
                    phuTung.setMaSP(cursor.getString(0));
                    phuTung.setTenSP(cursor.getString(1));
                    phuTung.setSoLuong(cursor.getInt(2));
                    phuTung.setDonGia(cursor.getInt(3));
                    phuTung.setHanBH(cursor.getInt(4));
                    phuTung.setHinhAnh(cursor.getBlob(5));
                    phuTung.setTenNCC(cursor.getString(6));
                    phuTungs.add(phuTung);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            phuTungs = null;
        }
        return phuTungs;
    }

    public ArrayList<PhuTung> searchNCC_PT(String keyword) {
        ArrayList<PhuTung> phuTungs = null;
        try{
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = "select * from PHUTUNG where MANCC like ?";
            Cursor cursor = sqLiteDatabase.rawQuery(query, new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()){
                phuTungs = new ArrayList<PhuTung>();
                do {
                    PhuTung phuTung = new PhuTung();
                    phuTung.setMaSP(cursor.getString(0));
                    phuTung.setTenSP(cursor.getString(1));
                    phuTung.setSoLuong(cursor.getInt(2));
                    phuTung.setDonGia(cursor.getInt(3));
                    phuTung.setHanBH(cursor.getInt(4));
                    phuTung.setHinhAnh(cursor.getBlob(5));
                    phuTung.setTenNCC(cursor.getString(6));
                    phuTungs.add(phuTung);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            phuTungs = null;
        }
        return phuTungs;
    }

    //THONG SO XE
    public void insertTSX(String motorSpecName) {      //Specification
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENTS", motorSpecName);
        db.insert("THONGSOXE", null, values);
        db.close();
        Log.d(TAG,"Insert MOTOR SPECIFICATION: ");
    }

    public void updateTSX(ThongSoXe thongSoXe) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Update  THONGSOXE  set ";
        sql += "TENTS  = '" + thongSoXe.getTenTS() + "' ";
        sql += "  WHERE MATS  = '" + thongSoXe.getMaTS() + "'";

        db.execSQL(sql);
        db.close();
        Log.d("DBManager", "Update THONG SO XE");
    }

    public void loadTSX(ArrayList<ThongSoXe> data) {
        data.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from THONGSOXE";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ThongSoXe thongSoXe = new ThongSoXe();
                thongSoXe.setTenTS(cursor.getString(1));
                data.add(thongSoXe);
            } while (cursor.moveToNext());
        }
        Log.d("DBManager", "Load THONG SO XE");
    }

    public void deleteTSX(ThongSoXe thongSoXe) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM THONGSOXE WHERE MATS='" + thongSoXe.getMaTS() + "'";
        db.execSQL(query);
        db.close();
        Log.d("DBManager", "Delete THONG SO XE");
    }


    //CHI TIET THONG SO XE
    public void insertCTTSX(ChiTietThongSoXe chiTietThongSoXe) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MAXE", chiTietThongSoXe.getMaXe());
        values.put("MATS", chiTietThongSoXe.getMaTS());
        values.put("NOIDUNGTS", chiTietThongSoXe.getNoiDungTS());
        db.insert("CHITIETTHONGSOXE", null, values);
        db.close();
        Log.d(TAG,"Insert CHI TIET THONG SO XE: ");
    }

    public void updateCTTSX(ChiTietThongSoXe chiTietThongSoXe) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Update  CHITIETTHONGSOXE  set ";
        sql += "NOIDUNGTS  = '" + chiTietThongSoXe.getNoiDungTS() + "' ";
        sql += "  WHERE MATS  = '" + chiTietThongSoXe.getMaTS() + "'";

        db.execSQL(sql);
        db.close();
        Log.d("DBManager", "Update CHI TIET THONG SO XE");
    }

    public void loadCTTSX(ArrayList<ChiTietThongSoXe> data) {
        data.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from CHITIETTHONGSOXE";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ChiTietThongSoXe chiTietThongSoXe = new ChiTietThongSoXe();
                chiTietThongSoXe.setMaXe(cursor.getString(0));
                chiTietThongSoXe.setMaTS(cursor.getInt(1));
                chiTietThongSoXe.setNoiDungTS(cursor.getString(2));
                data.add(chiTietThongSoXe);
            } while (cursor.moveToNext());
        }
        Log.d("DBManager", "Load CHI TIET THONG SO XE");
    }

    public void deleteCTTSX(ChiTietThongSoXe chiTietThongSoXe) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM CHITIETTHONGSOXE WHERE MATS='" + chiTietThongSoXe.getMaTS() + "'";
        db.execSQL(query);
        db.close();
        Log.d("DBManager", "Delete CHI TIET THONG SO XE");
    }


    //THONG SO PHU TUNG
    public void insertTSPT() { }
    public void insertAccessorySpec(ThongSoPhuTung accessorySpec) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MAPT", accessorySpec.getMaPT());
        values.put("MAXE", accessorySpec.getMaXe());
        values.put("DONGIA", accessorySpec.getDonGia());
        db.insert("THONGSOPHUTUNG", null, values);
        db.close();
        Log.d(TAG,"Insert ACCESSORY SPECIFICATION: ");
    }
    public void updateTSPT() { }
    public void loadTSPT() { }
    public void deleteTSPT() { }


    //DON HANG
    public void insertBill(DonHang bill) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MADH", bill.getMaDH());
        values.put("NGAYDAT", bill.getNgayDat());
        values.put("CMND", bill.getCmnd());
        values.put("MANV", bill.getMaNV());
        db.insert("DONDATHANG", null, values);
        db.close();
        Log.d(TAG,"Insert BILL: ");
    }
    public void loadDH() { }

    //CHI TIET DON HANG XE
    public void insertMotorBillDetail(String maDH, ChiTietSanPhamDonHang productBillDetail) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MADH", maDH);
        values.put("MAXE", productBillDetail.getMaSP());
        values.put("SOLUONG", productBillDetail.getSoLuong());
        values.put("DONGIABAN", productBillDetail.getDonGiaBan());
        db.insert("CHITIETDONDATXE", null, values);
        db.close();
        Log.d(TAG,"Insert MOTOR BILL DETAIL: ");
    }

    //CHI TIET DON HANG PHU TUNG
    public void insertAccessoryBillDetail(String maDH, ChiTietSanPhamDonHang productBillDetail) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MADH", maDH);
        values.put("MAPT", productBillDetail.getMaSP());
        values.put("SOLUONG", productBillDetail.getSoLuong());
        values.put("DONGIABAN", productBillDetail.getDonGiaBan());
        db.insert("CHITIETDONDATPHUTUNG", null, values);
        db.close();
        Log.d(TAG,"Insert ACCESSORY BILL DETAIL: ");
    }

    //BAO HANH
    public void insertGuarantee(BaoHanh guarantee){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MABH", guarantee.getMaBH());
        values.put("MADH", guarantee.getMaDH());
        values.put("NGAYBH", guarantee.getNgayBH());
        values.put("MANV", guarantee.getMaNV());
        db.insert("BAOHANH", null, values);
        db.close();
        Log.d(TAG,"Insert GUARANTEE: ");
    }

    //CHI TIET BAO HANH XE
    public void insertMotorGuaranteeDetail(String maBH, DanhSachSanPhamBaoHanh motorGuaranteeDetail){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MABH", maBH);
        values.put("MAXE", motorGuaranteeDetail.getMaSP());
        values.put("NOIDUNGBH", motorGuaranteeDetail.getNoiDungBH());
        values.put("PHIBH", motorGuaranteeDetail.getPhiBH());
        db.insert("CHITIETBAOHANHXE", null, values);
        db.close();
        Log.d(TAG,"Insert MOTOR GUARANTEE DETAIL: ");
    }

    //CHI TIET BAO HANH PHU TUNG
    public void insertAccessoryGuaranteeDetail(String maBH, DanhSachSanPhamBaoHanh accessoryGuaranteeDetail){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MABH", maBH);
        values.put("MAPT", accessoryGuaranteeDetail.getMaSP());
        values.put("NOIDUNGBH", accessoryGuaranteeDetail.getNoiDungBH());
        values.put("PHIBH", accessoryGuaranteeDetail.getPhiBH());
        db.insert("CHITIETBAOHANHPHUTUNG", null, values);
        db.close();
        Log.d(TAG,"Insert ACCESSORY GUARANTEE DETAIL: ");
    }


    public void initData(){

        BoPhan dp = new BoPhan("BH", "Ban Hang");
        insertDP(dp);
        dp = new BoPhan("BD", "Bao Duong");
        insertDP(dp);
        dp = new BoPhan("GD", "Giam Doc");
        insertDP(dp);

        NhanVien st = new NhanVien("NV01", "Tu An", "0123456111", "GD");
        insertST(st);
        st = new NhanVien("NV02", "Le Ngoc Khanh", "0123456222", "BH");
        insertST(st);
        st = new NhanVien("NV03", "Thu Huong", "0123456333", "BH");
        insertST(st);
        st = new NhanVien("NV04", "Kha Ngan", "0123456444", "BH");
        insertST(st);
        st = new NhanVien("NV05", "Vinh Nhat Khoa", "0123456555", "BD");
        insertST(st);
        st = new NhanVien("NV06", "Tan Loc", "0123456666", "BD");
        insertST(st);
        st = new NhanVien("NV07", "Pham Khiem", "0123456777", "BD");
        insertST(st);

        KhachHang ctm = new KhachHang("302456811", "Le Van Hau", "97 Man Thien, Ho Chi Minh", "0347895411");
        insertCTM(ctm);
        ctm = new KhachHang("302456822", "Tran Anh", "65 CMT8, Ho Chi Minh", "0347895422");
        insertCTM(ctm);
        ctm = new KhachHang("302456833", "Vo Chi Trung", "156 3/2, Ho Chi Minh", "0347895433");
        insertCTM(ctm);
        ctm = new KhachHang("302456844", "Le Anh Tuyet", "7 Ly Thai To, Ho Chi Minh", "0347895444");
        insertCTM(ctm);
        ctm = new KhachHang("302456855", "Nguyen Thuy Tran", "1 Hoa Hong, Ho Chi Minh", "0347895455");
        insertCTM(ctm);

        NhaCungCap br = new NhaCungCap("HD", "Honda", "Phuc Thang, Phuc Yen, Vinh Phuc, Viet Nam", "18008001", "cr@honda.com.vn", 2131165305);
        insertBR(br);
        br = new NhaCungCap("YM", "Yamaha", "Binh An, Trung Gia, Soc Son, Ha Noi", "18001588", "cskh@yamaha-motor.com.vn", 2131165360);
        insertBR(br);
        br = new NhaCungCap("SY", "SYM", "4 5C, KCN Nhon Trach 2, Dong Nai", "0912111918", "cskhmn@sym.com.vn", 2131165356);
        insertBR(br);
        br = new NhaCungCap("OL", "Ohlins", "Box 722, 194 27 Upplands Vasby, Sweden", "1245777432", "service@ohlins.se", 2131165352);
        insertBR(br);
        br = new NhaCungCap("AK", "Akrapovic", "1295 Ivancna Gorica, Slovenia", "3456778998", "service@akrapovic.si", 2131165279);
        insertBR(br);

        /*Xe xe = new Xe("HD01", "Wave Alpha", 8, 18099000, 24, hinhAnh, "HD");
        insertXe(xe);
        xe = new Xe("HD02", "Winner X", 9, 45999000, 48, bigIntToByteArray(R.drawable.hd_winx), "HD");
        insertXe(xe);
        xe = new Xe("HD03", "Vision", 10, 29999000, 36, bigIntToByteArray(R.drawable.hd_vision), "HD");
        insertXe(xe);
        xe = new Xe("YM01", "Sirius", 8, 21099000, 36, bigIntToByteArray(R.drawable.ym_sirius), "YM");
        insertXe(xe);
        xe = new Xe("YM02", "Exciter", 9, 50499000, 48, bigIntToByteArray(R.drawable.ym_exciter), "YM");
        insertXe(xe);
        xe = new Xe("YM03", "Grande", 10, 49999000, 48, bigIntToByteArray(R.drawable.ym_grande), "YM");
        insertXe(xe);
        PhuTung phuTung = new PhuTung("OH01", "Nhan dan trang tri Ohlins 1", 5, 403000, 3, bigIntToByteArray(R.drawable.oh_sticker1) , "OH");
        insertPT(phuTung);
        phuTung = new PhuTung("OH02", "Phuoc Ohlins Vario", 5, 8500000, 24, bigIntToByteArray(R.drawable.oh_phuoc_vario), "OH");
        insertPT(phuTung);
        phuTung = new PhuTung("AK01", "Nhan dan Akrapovic chong nhiet nhom", 5, 212000, 12, bigIntToByteArray(R.drawable.ak_sticker1), "AK");
        insertPT(phuTung);
        phuTung = new PhuTung("AK02", "Po Akrapovic GP Titan Yamaha R3", 5, 8000000, 24, bigIntToByteArray(R.drawable.ak_gp_titan_r3), "AK");
        insertPT(phuTung);*/

        ThongSoXe motorSpec = new ThongSoXe();
        insertTSX("Khối lượng");          //1
        insertTSX("Dài x Rộng x Cao");
        insertTSX("Độ cao yên");          //3
        insertTSX("Khoảng sáng gầm xe");
        insertTSX("Dung tích bình xăng"); //5
        insertTSX("Loại động cơ");
        insertTSX("Công suất tối đa");    //7
        insertTSX("Dung tích nhớt máy");
        insertTSX("Hộp số");              //9
        insertTSX("Dung tích xy-lanh");

        ChiTietThongSoXe motorSpecDetail = new ChiTietThongSoXe("HD01", 1, "97kg");
        insertCTTSX(motorSpecDetail);
        motorSpecDetail = new ChiTietThongSoXe("HD01", 2, "1.914mm x 688mm x 1.075mm");
        insertCTTSX(motorSpecDetail);
        motorSpecDetail = new ChiTietThongSoXe("HD01", 5, "3,7 lít");
        insertCTTSX(motorSpecDetail);

        motorSpecDetail = new ChiTietThongSoXe("HD02", 1, "Phiên bản phanh thường: 123kg Phiên bản phanh ABS: 124kg");
        insertCTTSX(motorSpecDetail);
        motorSpecDetail = new ChiTietThongSoXe("HD02", 2, "2.019 x 727 x 1.088 mm");
        insertCTTSX(motorSpecDetail);
        motorSpecDetail = new ChiTietThongSoXe("HD02", 5, "4,5 lít");
        insertCTTSX(motorSpecDetail);

        motorSpecDetail = new ChiTietThongSoXe("HD03", 1, "Phiên bản Tiêu chuẩn: 96kg Phiên bản Đặc biệt và Cao cấp: 97kg");
        insertCTTSX(motorSpecDetail);
        motorSpecDetail = new ChiTietThongSoXe("HD03", 2, "1.871mm x 686mm x 1.101mm");
        insertCTTSX(motorSpecDetail);
        motorSpecDetail = new ChiTietThongSoXe("HD03", 5, "4,9 lít");
        insertCTTSX(motorSpecDetail);

        motorSpecDetail = new ChiTietThongSoXe("YM01", 1, "96kg");
        insertCTTSX(motorSpecDetail);
        motorSpecDetail = new ChiTietThongSoXe("YM01", 2, "1.890mm x 665mm x 1.035mm");
        insertCTTSX(motorSpecDetail);
        motorSpecDetail = new ChiTietThongSoXe("YM01", 5, "4,2 lít");
        insertCTTSX(motorSpecDetail);

        motorSpecDetail = new ChiTietThongSoXe("YM02", 1, "121 kg");
        insertCTTSX(motorSpecDetail);
        motorSpecDetail = new ChiTietThongSoXe("YM02", 2, "1,975 mm × 665 mm × 1,085 mm");
        insertCTTSX(motorSpecDetail);
        motorSpecDetail = new ChiTietThongSoXe("YM02", 5, "5.4 lít");
        insertCTTSX(motorSpecDetail);

        motorSpecDetail = new ChiTietThongSoXe("YM03", 1, "101 kg");
        insertCTTSX(motorSpecDetail);
        motorSpecDetail = new ChiTietThongSoXe("YM03", 2, "1.820mm x 685mm x 1.150mm");
        insertCTTSX(motorSpecDetail);
        motorSpecDetail = new ChiTietThongSoXe("YM03", 5, "4,4 lít");
        insertCTTSX(motorSpecDetail);

        ThongSoPhuTung accessorySpec = new ThongSoPhuTung("OH01", "HD01", 350000);
        insertAccessorySpec(accessorySpec);
        accessorySpec = new ThongSoPhuTung("OH01", "HD03", 450000);
        insertAccessorySpec(accessorySpec);
        accessorySpec = new ThongSoPhuTung("OH01", "YM02", 400000);
        insertAccessorySpec(accessorySpec);

        accessorySpec = new ThongSoPhuTung("OH02", "YM01", 800000);
        insertAccessorySpec(accessorySpec);
        accessorySpec = new ThongSoPhuTung("OH02", "YM03", 8500000);
        insertAccessorySpec(accessorySpec);
        accessorySpec = new ThongSoPhuTung("OH02", "HD02", 9000000);
        insertAccessorySpec(accessorySpec);

        accessorySpec = new ThongSoPhuTung("AK01", "HD01", 150000);
        insertAccessorySpec(accessorySpec);
        accessorySpec = new ThongSoPhuTung("AK01", "HD02", 250000);
        insertAccessorySpec(accessorySpec);
        accessorySpec = new ThongSoPhuTung("AK01", "HD03", 200000);
        insertAccessorySpec(accessorySpec);

        accessorySpec = new ThongSoPhuTung("AK02", "YM01", 7500000);
        insertAccessorySpec(accessorySpec);
        accessorySpec = new ThongSoPhuTung("AK02", "YM02", 8000000);
        insertAccessorySpec(accessorySpec);
        accessorySpec = new ThongSoPhuTung("AK02", "YM03", 8500000);
        insertAccessorySpec(accessorySpec);

        DonHang bill = new DonHang("HD01", "01/04/2021 09:00:00", "302456811", "NV02");
        insertBill(bill);
        bill = new DonHang("HD02", "01/04/2021 15:30:00", "302456822", "NV03");
        insertBill(bill);
        bill = new DonHang("HD03", "25/04/2021 10:05:00", "302456811", "NV02");
        insertBill(bill);
        bill = new DonHang("HD04", "02/05/2021 08:15:00", "302456844", "NV04");
        insertBill(bill);
        bill = new DonHang("HD05", "02/05/2021 16:33:00", "302456844", "NV04");
        insertBill(bill);
        bill = new DonHang("HD06", "08/05/2021 13:20:00", "302456833", "NV03");
        insertBill(bill);
        bill = new DonHang("HD07", "12/05/2021 07:10:00", "302456855", "NV04");
        insertBill(bill);

        ChiTietSanPhamDonHang productBillDetail;
        productBillDetail = new ChiTietSanPhamDonHang("HD01", 1, 18099000);
        insertMotorBillDetail("HD01", productBillDetail);
        productBillDetail = new ChiTietSanPhamDonHang("YM02", 3, 50499000);
        insertMotorBillDetail("HD01", productBillDetail);
        productBillDetail = new ChiTietSanPhamDonHang("OH01", 2, 400000);
        insertAccessoryBillDetail("HD01", productBillDetail);
        productBillDetail = new ChiTietSanPhamDonHang("YM02", 2, 50499000);
        insertMotorBillDetail("HD02", productBillDetail);
        productBillDetail = new ChiTietSanPhamDonHang("YM01", 1, 21000000);
        insertMotorBillDetail("HD03", productBillDetail);
        productBillDetail = new ChiTietSanPhamDonHang("AK01", 3, 212000);
        insertAccessoryBillDetail("HD03", productBillDetail);
        productBillDetail = new ChiTietSanPhamDonHang("HD03", 1, 29999000);
        insertMotorBillDetail("HD04", productBillDetail);
        productBillDetail = new ChiTietSanPhamDonHang("YM03", 1, 49999000);
        insertMotorBillDetail("HD05", productBillDetail);
        productBillDetail = new ChiTietSanPhamDonHang("AK02", 1, 8000000);
        insertAccessoryBillDetail("HD05", productBillDetail);
        productBillDetail = new ChiTietSanPhamDonHang("HD02", 4, 45999000);
        insertMotorBillDetail("HD06", productBillDetail);
        productBillDetail = new ChiTietSanPhamDonHang("YM01", 1, 21100000);
        insertMotorBillDetail("HD06", productBillDetail);
        productBillDetail = new ChiTietSanPhamDonHang("HD03", 1, 29999000);
        insertMotorBillDetail("HD07", productBillDetail);

        BaoHanh guarantee = new BaoHanh("BH01", "HD01", "01/05/2021 10:00:00", "NV05");
        insertGuarantee(guarantee);
        guarantee = new BaoHanh("BH02", "HD01", "05/05/2021 10:00:00", "NV05");
        insertGuarantee(guarantee);
        guarantee = new BaoHanh("BH03", "HD05", "15/05/2021 08:11:00", "NV07");
        insertGuarantee(guarantee);
        guarantee = new BaoHanh("BH04", "HD04", "20/05/2021 09:22:00", "NV06");
        insertGuarantee(guarantee);

        DanhSachSanPhamBaoHanh guaranteeProductDetail = new DanhSachSanPhamBaoHanh("HD01", "Thay nhot", 0);
        insertMotorGuaranteeDetail("BH01", guaranteeProductDetail);
        guaranteeProductDetail = new DanhSachSanPhamBaoHanh("HD01", "Tang sen", 0);
        insertMotorGuaranteeDetail("BH01", guaranteeProductDetail);
        guaranteeProductDetail = new DanhSachSanPhamBaoHanh("YM02", "Thay lop xe", 0);
        insertMotorGuaranteeDetail("BH01", guaranteeProductDetail);
        guaranteeProductDetail = new DanhSachSanPhamBaoHanh("OH01", "Thay tem", 0);
        insertAccessoryGuaranteeDetail("BH01", guaranteeProductDetail);
        guaranteeProductDetail = new DanhSachSanPhamBaoHanh("YM02", "Siet thang", 0);
        insertMotorGuaranteeDetail("BH02", guaranteeProductDetail);
        guaranteeProductDetail = new DanhSachSanPhamBaoHanh("AK02", "Han ong bo", 0);
        insertAccessoryGuaranteeDetail("BH03", guaranteeProductDetail);
        guaranteeProductDetail = new DanhSachSanPhamBaoHanh("HD03", "Thay day ga", 0);
        insertMotorGuaranteeDetail("BH04", guaranteeProductDetail);

    }

}
