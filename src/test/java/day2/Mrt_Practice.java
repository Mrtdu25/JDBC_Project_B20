package day2;

import utility.DB_Utility;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.List;

public class Mrt_Practice {
    public static void main(String[] args) throws SQLException {


        DB_Utility.createConnection();
        ResultSet rs = DB_Utility.runQuery("SELECT * FROM EMPLOYEES");

        ResultSetMetaData rsmt = rs.getMetaData();

        int columnCount = rsmt.getColumnCount();

        System.out.println(columnCount);


        for (int countNum = 1; countNum < columnCount; countNum++) {

            System.out.printf("%15s",rsmt.getColumnName(countNum));

        }
        System.out.println();

        rs.beforeFirst();

        while (rs.next()){
            for (int countNum = 1; countNum < columnCount; countNum++) {

                System.out.printf("%14s",rs.getString(countNum));

            }
            System.out.println();
        }


        System.out.println("==========================================================================================================================================================");
        rs.last();

        while (rs.previous()){
            for (int countNum = 1; countNum < columnCount; countNum++) {

                System.out.printf("%14s",rs.getString(countNum));

            }
            System.out.println();
        }

        int columnCount1 = DB_Utility.getColumnCount();
        System.out.println(columnCount1);

        List<String> columnNames = DB_Utility.getColumnNames();
        System.out.println("columnNames = " + columnNames);


        List<String> row36DataAsList = DB_Utility.getRowDataAsList(36);
        System.out.printf("%14s",row36DataAsList);


        rs.beforeFirst();

        while (rs.next()){
            System.out.println(rs.getString(1));
        }

        rs.beforeFirst();

        String columnLabel2 = rsmt.getColumnLabel(2);
        System.out.println(columnLabel2);

        rs.beforeFirst();

        for (int colNum = 1; colNum <columnCount; colNum++) {
            System.out.println(rsmt.getColumnName(colNum));
        }

        rs.beforeFirst();

        rs.absolute(29);
        for (int colNum29 = 1; colNum29 <=columnCount; colNum29++) {
            System.out.printf("%14s",rs.getString(colNum29));
        }
        System.out.println();

        String column29DataAtRow = DB_Utility.getColumnDataAtRow(29, 2);
        System.out.println(column29DataAtRow);

        String columnDataByString29 = DB_Utility.getColumnDataAtRow(29, "FIRST_NAME");

        System.out.println(columnDataByString29);
        System.out.println();

        List<String> columnDataAsList = DB_Utility.getColumnDataAsList(2);
        System.out.println(columnDataAsList);

        List<String> columnDataAsList2 = DB_Utility.getColumnDataAsList("FIRST_NAME");
        System.out.println(columnDataAsList2);

        DB_Utility.displayAllData();


    }
}
