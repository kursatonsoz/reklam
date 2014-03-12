/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reklamotomasyonu;

/**
 *
 * @author Kursat
 */
import java.sql.*;

public class DbQueries {

    private static final String baglantiStr = "jdbc:sqlserver://localhost:1433;databaseName=reklam;user=sa;password=1";

    public static String[][] Goster(String sql) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(baglantiStr);
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData rmd = resultSet.getMetaData();
            int j = rmd.getColumnCount();
            int satir = 0;
            while (resultSet.next()) {
                satir++;
            }
            String[][] datalar = new String[satir][j];
            System.out.println(satir);
            resultSet = statement.executeQuery(sql);
            int i=0;
           
            while(resultSet.next()){
                for (int k = 0; k < j; k++) {
                    datalar[i][k] = resultSet.getString(k+1);
                }
                i++;
            }
            conn.close();
            return datalar;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
