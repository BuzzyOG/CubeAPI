package com.cubemc.api.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by henry on 4/2/15.
 */
public class MySQL {

    public MySQL(){}

    public static String username = "root";
    public static String password = "";
    public static String host = "localhost";
    public static String port = "3306";
    public static String database = "CubeAPI";
    public static String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

    public static void connectToDB(String query) throws SQLException{
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = con.prepareStatement(query);
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    public static String getResultsString(String query, String column) throws SQLException{
        String result = "";
        Connection con = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet res = ps.executeQuery();
        if(res.next())
            result = res.getString(column);
        ps.close();
        con.close();
        return result;
    }

    public static int getResultsDB(String query, String column) throws SQLException {
        int resultint = -1;
        Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet res = ps.executeQuery();
        if (res.next()) {
            if (res.getString("PlayerName") == null) {
                resultint = -1;
                ps.close();
                conn.close();
                return resultint;
            } else {
                resultint = res.getInt(column);
                ps.close();
                conn.close();
                return resultint;
            }
        } else {
            ps.close();
            conn.close();
            return resultint;
        }
    }

    public static List<String> getListStringDB(String query, String column) throws SQLException {
        List<String> players = new ArrayList<String>();
        Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            String str = res.getString(column);
            players.add(str);
        }
        return players;
    }

    public static List<Integer> getListIntegerDB(String query, String column) throws SQLException {
        List<Integer> list = new ArrayList<Integer>();
        Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            int integer = res.getInt(column);
            list.add(integer);
        }
        return list;
    }

    public static int getResultsInteger(String query, String column) throws SQLException {
        int result = 0;
        Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet res = ps.executeQuery();
        if (res.next()){
            result = res.getInt(column);
            ps.close();
            conn.close();
            return result;
        } else {
            ps.close();
            conn.close();
            return result;
        }
    }

    public static long getLong(String query, String column) throws SQLException {
        long result = 0;
        Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet res = ps.executeQuery();
        if (res.next()){
            result = res.getLong(column);
            ps.close();
            conn.close();
            return result;
        } else {
            ps.close();
            conn.close();
            return result;
        }
    }

    public static boolean doesTableContain(String query) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet res = ps.executeQuery();
        if (res.next()) {
            ps.close();
            conn.close();
            return true;
        }else{
            ps.close();
            conn.close();
            return false;
        }
    }

    public static int getCountFromTable(String table) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM " + table + ";");
        ResultSet res = ps.executeQuery();
        while (res.next()){
            ps.close();
            conn.close();
            return res.getInt(1);
        }
        ps.close();
        conn.close();
        return 0;
    }

    public static boolean getBooleanFromTable(String query, String column) throws SQLException {
        boolean result = false;
        Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet res = ps.executeQuery();
        if (res.next()){
            result = res.getBoolean(column);
            ps.close();
            conn.close();
            return result;
        }
        ps.close();
        conn.close();
        return result;
    }

    public static HashMap<String,String> getResultsStrings(String query, String[] columns) throws SQLException{
        HashMap<String,String> result = null;
        Connection con  = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet res  = ps.executeQuery();
        if(res.next()){
            result = new HashMap<String, String>();
            for(String col : columns){
                result.put(col,res.getString(col));
            }
        }
        ps.close();
        con.close();
        return result;
    }

}
