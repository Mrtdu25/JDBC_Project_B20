package utility;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.net.URI;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_Utility {

    static Connection conn; // make it static field so we can reuse in every methods we write
    static Statement stmnt;
    static ResultSet rs;

    //THIS METHOD MAKE DATABASE CONNECTION
    public static void createConnection() {
        String BD_URL = "jdbc:oracle:thin:@54.234.93.185:1521:XE";
        String BD_USERNAME = "hr";
        String BD_PASSWORD = "hr";

        try {
            conn = DriverManager.getConnection(BD_URL, BD_USERNAME, BD_PASSWORD);
            System.out.println("CONNECTION SUCCESSFUL !! ");
        } catch (SQLException e) {
            System.out.println("CONNECTION HAS FAILED !!! " + e.getMessage());
        }
    }

    //THIS WILL GET US RESULT SET rs
    public static ResultSet runQuery(String query) {

        try {
            stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmnt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING THE RESULT SET " + e.getMessage());
        }
        return rs;
    }


    //THIS WILL DESTROY CON. RS. STMNT
    public static void destroy() {

        try {
            rs.close();
            stmnt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //THIS WILL COUNT US ROWS

    public static int getRowCount() {
        int rowCount = 0;

        try {
            rs.last();
            rowCount = rs.getRow();

            // move the cursor back to beforeFirst location to avoid accident
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW COUNT " + e.getMessage());
        }
        return rowCount;
    }

    //THIS WILL GET ME COLUMN COUNT

    public static int getColumnCount() {
        int columnCount = 0;

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE COUNTING THE COLUMNS " + e.getMessage());
        }
        return columnCount;
    }

    /// THIS WILL GET MW ALL THE COLUMN NAMES

    public static List<String> getColumnNames() {

        List<String> columnList = new ArrayList<>();

        try {
            ResultSetMetaData rsmd = rs.getMetaData();

            for (int colNum = 1; colNum <= getColumnCount(); colNum++) {
                String columnLabels = rsmd.getColumnLabel(colNum);
                columnList.add(columnLabels);
            }
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ALL COLUMN NAMES " + e.getMessage());
        }

        return columnList;
    }


    //THIS WILL GET getRowDataAsList

    public static List<String> getRowDataAsList(int rowNum) {

        List<String> rowDataList = new ArrayList<>();

        try {
            rs.absolute(rowNum);
            for (int colNum = 1; colNum <= getColumnCount(); colNum++) {
                String eachCellValue = rs.getString(colNum);
                rowDataList.add(eachCellValue);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW DATA AS LIST " + e.getMessage());
        }

        return rowDataList;
    }

    //THIS WILL GET SINGLE DATA AT SPECIFIC ROW BY USING INDEX NUMBER

    public static String getColumnDataAtRow(int rowNum, int colNum) {

        String result = "";

        try {
            rs.absolute(rowNum);
            result = rs.getString(colNum);

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING COLUMN DATA " + e.getMessage());
        }
        return result;
    }


    //THIS WILL GET SINGLE DATA AT SPECIFIC ROW BY USING COLUMN NAME

    public static String getColumnDataAtRow(int rowNum, String colName) {

        String result = "";

        try {
            rs.absolute(rowNum);
            result = rs.getString(colName);

            rs.beforeFirst();
        } catch (SQLException e) {

            System.out.println("ERROR WHILE GETTING COLUMN DATA " + e.getMessage());
        }
        return result;
    }

    //THIS WILL GET SPECIFIC COLUMNS ALL DATA AS LIST BY USING INDEX NUM

    public static List<String> getColumnDataAsList(int colNum) {

        List<String> cellValuesList = new ArrayList<>();


        try {
            while (rs.next()) {

                String cellValue = rs.getString(colNum);
                cellValuesList.add(cellValue);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println();
        }
        return cellValuesList;
    }

    //THIS WILL GET SPECIFIC COLUMNS ALL DATA AS LIST BY USING COLUMN NAME

    public static List<String> getColumnDataAsList(String colName) {

        List<String> cellValuesList = new ArrayList<>();


        try {
            while (rs.next()) {

                String cellValue = rs.getString(colName);
                cellValuesList.add(cellValue);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println();
        }
        return cellValuesList;
    }

    //THIS WILL DISPLAY ALL DATA FROM COLUMN AND ROW USE NESTED LOOP

    public static void displayAllData(){

        try{

            while (rs.next()){
                for (int colNum = 1; colNum <=getColumnCount(); colNum++) {

                    String eachRowValue = rs.getString(colNum);
                    System.out.printf("%14s",eachRowValue);
                }
                System.out.println();
            }
            rs.beforeFirst();

        }catch (SQLException e){
            System.out.println("ERROR WHILE DISPLAYING ENTIRE TABLE "+e.getMessage());
        }


    }





}




