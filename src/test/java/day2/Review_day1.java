package day2;

import utility.DB_Utility;

import java.sql.*;

public class Review_day1 {
    public static void main(String[] args) throws SQLException {

        DB_Utility.createConnection();
        ResultSet rs   = DB_Utility.runQuery ("SELECT * FROM JOBS") ;

        // I want to read the first row
        rs.next() ;
        System.out.println("First column value in Jobs " + rs.getString(1)  );
        System.out.println("Second column value in Jobs " + rs.getString(2)  );

        // move to row number 7 and get above 2 columns values
        rs.absolute(7) ;
        System.out.println("First column value in Jobs in row 7 " + rs.getString(1)  );
        System.out.println("Second column value in Jobs in row 7 " + rs.getString(2)  );

        rs.last() ;
        System.out.println("First column value in Jobs in last row " + rs.getString(1)  );
        System.out.println("Second column value in Jobs in last row " + rs.getString(2)  );

        rs.previous();
        System.out.println("First column value in Jobs in 2nd row from last  " + rs.getString(1)  );
        System.out.println("Second column value in Jobs in 2nd row from last  " + rs.getString(2)  );

        System.out.println("\n---------Loop from first row till last row print JOB_ID-------");
        rs.beforeFirst();

        while( rs.next() ){
            System.out.println("Loop First Column " + rs.getString("JOB_ID") );
        }

        System.out.println("\n---------Loop from last row till first row get MIN_SALARY COLUMN AS NUMBER-------");
        // WE ARE CURRENTLY AT AFTER LAST LOCATION
        // if you really want to make sure and explicitly say so
        // we can do below
        rs.afterLast();
        while(rs.previous() ){
            // NOTHING WRONG WITH GETTING IT AS STRING , JUST FOR THE SAKE OF DEMO , WE ARE GETTING AS DOUBLE
            System.out.println("MIN SALARY COLUMN AS NUMBER  $" + rs.getDouble("MIN_SALARY")    );
        }

        // clean up the connection , statement and resultset object after usage
        DB_Utility.destroy();





    }


}

