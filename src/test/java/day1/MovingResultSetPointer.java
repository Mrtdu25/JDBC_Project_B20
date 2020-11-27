package day1;

import java.sql.*;

public class MovingResultSetPointer {

    public static void main(String[] args) throws SQLException {

        // we want to create a statement object that generate
        // ResultSet that can move forward and backward anytime
        String connectionStr = "jdbc:oracle:thin:@54.234.93.185:1521:XE";
        String username = "hr" ;
        String password = "hr" ;

        Connection conn = DriverManager.getConnection(connectionStr,username,password) ;
        // this way of creating statement will give you ability to generate
        // ResultSet that can move forward and backward anytime
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        ResultSet rs   =   stmt.executeQuery("SELECT * FROM REGIONS") ;

        while(rs.next() ){
            System.out.println("REGION_NAME " + rs.getString("REGION_NAME") );
        }

//        rs.previous() ;
//        System.out.println("REGION_NAME " + rs.getString("REGION_NAME") );
        while(rs.previous() ){
            System.out.println("BACKWARD - REGION_NAME " + rs.getString("REGION_NAME") );
        }

        /**
         * Other ResultSet methods for moving your pointer to specific locations
         */
        rs.beforeFirst(); // before first location
        rs.first() ;  // first row
        System.out.println("First Row " + rs.getString(2) ) ;
        rs.last() ; // last row
        System.out.println("Last Row " + rs.getString(2) ) ;
        rs.afterLast(); // after last location
        rs.absolute(3) ; // move to specific row
        System.out.println("Third Row " + rs.getString(2) ) ;

        // how to find out which row the pointer is at right now
        rs.last();
        System.out.println("NOW WE HAVE MOVED TO LAST ROW ");
        int currentRowNum = rs.getRow() ;
        System.out.println("Row Count  = " + currentRowNum);


    }

}
