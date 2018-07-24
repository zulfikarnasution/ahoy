package com.example.acer.tbcrudmysql.Model;

public class ModelData {
    String npm,nama_lengkap,fakultas,prodi;
    String id_matkul,nama_matkul,hari,ruangan;
    String nik,nama_dosen,matakuliah,prodi1;
    public ModelData(){}

    public ModelData(String npm, String nama_lengkap, String fakultas, String prodi,
                     String id_matkul, String nama_matkul, String hari, String ruangan,
                     String nik, String nama_dosen, String matakuliah, String prodi1) {
        this.npm = npm;
        this.nama_lengkap = nama_lengkap;
        this.fakultas = fakultas;
        this.prodi = prodi;

        this.nik = nik;
        this.nama_dosen = nama_dosen;
        this.matakuliah = matakuliah;
        this.prodi1 = prodi1;

        this.id_matkul = id_matkul;
        this.nama_matkul = nama_matkul;
        this.hari = hari;
        this.ruangan = ruangan;
    }


    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }


    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama_dosen() {
        return nama_dosen;
    }

    public void setNama_dosen(String nama_dosen) {
        this.nama_dosen = nama_dosen;
    }

    public String getMatakuliah() {
        return matakuliah;
    }

    public void setMatakuliah(String matakuliah) {
        this.matakuliah = matakuliah;
    }

    public String getProdi1() {
        return prodi1;
    }

    public void setProdi1(String prodi1) {
        this.prodi1 = prodi1;
    }



    public String getId_matkul() {
        return id_matkul;
    }

    public void setId_matkul(String id_matkul) {
        this.id_matkul = id_matkul;
    }

    public String getNama_matkul() {
        return nama_matkul;
    }

    public void setNama_matkul(String nama_matkul) {
        this.nama_matkul = nama_matkul;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }
}
