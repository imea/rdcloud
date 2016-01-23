/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.dao;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import javax.servlet.http.Part;


/**
 *
 * @author Maxim
 */
public class HelperContext {

    public static String encryptSha1(String password) throws UnsupportedEncodingException {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static Date toUserDate(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(date);
    }

    public static Timestamp toMySqlDateNow() {
        Timestamp timestamp = new Timestamp(new Date().getTime());

        return timestamp;
    }

    public static String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                String s = content.substring(content.indexOf('=') + 1).trim();
                return s.substring(s.lastIndexOf(File.separator) + 1).replace("\"", "");
            }
        }
        return null;
    }

    public static boolean hasEmptyValue(String[] tab) {
        if (tab == null) {
            return false;
        }
        for (String value : tab) {
            if (value.equals("")) {
                return true;
            }
        }

        return false;
    }

    public static Timestamp toMySqlTimestamp(Date date_uploaded) {
        Timestamp ts = new Timestamp(date_uploaded.getTime());
        return ts;
    }

}
