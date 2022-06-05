package es.ieslavereda.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import es.ieslavereda.properties.MyConfig;
import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class MyDataSource {

    public static DataSource getMyOracleDataSource(){

        OracleDataSource mysqlDataSource = null;
        try {
            mysqlDataSource = new OracleDataSource();

        String host = MyConfig.getInstance().getOracleDBHost();
        String port = MyConfig.getInstance().getOracleDBPort();
        String user = MyConfig.getInstance().getOracleUsername();
        String password = MyConfig.getInstance().getOraclePassword();

        // jdbc:oracle:thin@<host>:<port>:xe
        mysqlDataSource.setURL("jdbc:oracle:thin:@"+ host + ":" + port +":xe");
        mysqlDataSource.setUser(user);
        mysqlDataSource.setPassword(password);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mysqlDataSource;
    }

}
