package com.dummy.absenqrcode;

public class Konfigurasi {
    //URL to our login.php file, url bisa diganti sesuai dengan alamat server kita
    public static final String LOGIN_URL = "http://192.168.1.145/absen/login.php";
    public static final String URL_ADD = "http://192.168.1.145/absen/simpanDataBimbingan.php";
    public static final String URL_GET_DATA = "http://192.168.1.145/absen/lihatData.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_NIM = "nim";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NAMA = "username";
    public static final String KEY_MATERI = "materi";
    public static final String KEY_PROGRES = "progress";
    public static final String KEY_MASALAH = "masalah";
    public static final String KEY_REVISI = "revisi";


    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NIM = "nim";
    public static final String TAG_NAMA = "username";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    public static final String EMP_ID = "nim";
}
