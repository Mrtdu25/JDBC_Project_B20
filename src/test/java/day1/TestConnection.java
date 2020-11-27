package day1;

import java.sql.*;


public class TestConnection {

    //54.234.93.185
    public static void main(String[] args) throws SQLException {

        // REPLACE THIS IP ADDRESS WITH YOUR OWN THAT WORKING IN SQL DEVELOPER
        // Make sure do not delete @ in front of it
        String connectionStr = "jdbc:oracle:thin:@54.234.93.185:1521:XE";
        String username = "hr" ;
        String password = "hr" ;

        Connection conn = DriverManager.getConnection(connectionStr,username,password) ;

        Statement stmnt = conn.createStatement();

        ResultSet rs   =   stmnt.executeQuery("SELECT * FROM REGIONS") ;
        // this will move the cursor from beforefirst location to first row
        rs.next(); // currently we are at the very first row
        // rs.getString(column index) can be used to read the data of that column in current row
        System.out.println("first column value using index: --> " +  rs.getString(1)   );
        System.out.println("second column value using index: --> " +  rs.getString(2)   );

        //  rs.getString(column LABEL)
        System.out.println("Region_ID at this row is : " + rs.getString("REGION_ID") );
        System.out.println("Region_Name at this row is : " + rs.getString("REGION_NAME") );

        rs.next() ; // this will move us to next row so we can read next row
        System.out.println("Region_ID at this row is : " + rs.getString("REGION_ID") );
        System.out.println("Region_Name at this row is : " + rs.getString("REGION_NAME") );


        rs.next() ; // this will move us to next row so we can read next row
        System.out.println("Region_ID at this row is : " + rs.getString("REGION_ID") );
        System.out.println("Region_Name at this row is : " + rs.getString("REGION_NAME") );


        rs.next() ; // this will move us to next row so we can read next row
        System.out.println("Region_ID at this row is : " + rs.getString("REGION_ID") );
        System.out.println("Region_Name at this row is : " + rs.getString("REGION_NAME") );



    }


}

