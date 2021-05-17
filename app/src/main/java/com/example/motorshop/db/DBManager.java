package com.example.motorshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.motorshop.datasrc.BoPhan;
import com.example.motorshop.datasrc.NhaCungCap;
import com.example.motorshop.datasrc.NhanVien;
import com.example.motorshop.datasrc.PhuTung;
import com.example.motorshop.datasrc.Xe;

import java.util.ArrayList;

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
        createTables.add("CREATE TABLE IF NOT EXISTS THONGSOXE (MATS int PRIMARY KEY, TENTS text not null)");
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
    public void insertST(){ }
    public void updateST() { }
    public void deleteST() { }
    public void loadSTList() { }


    //KHACH HANG (CUSTOMER)
    public void insertCTM() { }
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

    //THONG SO XE
    public void insertTSX() { }
    public void updateTSX() { }
    public void loadTSX() { }
    public void deleteTSX() { }


    //CHI TIET THONG SO XE
    public void insertCTTSX() { }
    public void updateCTTSX() { }
    public void loadCTTSX() { }
    public void deleteCTTSX() { }


    //THONG SO PHU TUNG
    public void insertTSPT() { }
    public void updateTSPT() { }
    public void loadTSPT() { }
    public void deleteTSPT() { }


    //DON HANG & CHI TIET DON HANG
    //public void insertDH() { }      ->        public void insertCTDH() { }
    public void loadDH() { }


//CAC PHAN CON LAI TUONG TU

}
