package wen.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 单利模式 饿汉式
 */
public class DruidUtil {
    private static DataSource dataSource;

    static {
        try {
            Properties prop = new Properties();
            InputStream is = DruidUtil.class.getResourceAsStream("/druid.properties");
            prop.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DruidUtil() {
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
