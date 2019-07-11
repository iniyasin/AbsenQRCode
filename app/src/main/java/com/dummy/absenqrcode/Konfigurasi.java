package com.dummy.absenqrcode;

public class Konfigurasi {
    //URL to our login.php file, url bisa diganti sesuai dengan alamat server kita
    public static final String LOGIN_URL = "http://192.168.1.7/absen/login.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_NIM = "nim";
    public static final String KEY_PASSWORD = "pass";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";
}
