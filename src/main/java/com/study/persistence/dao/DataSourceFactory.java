package com.study.persistence.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DataSourceFactory.class);
    private static final DataSourceFactory dataSourceFactory = new DataSourceFactory();
    private static final String DB_FILE = "/application.properties";
    private static DataSource dataSource;
    private static  Context context = null;

    private DataSourceFactory() {

    }

//    static {
//        Properties properties = new Properties();
//        try {
//            properties.load(DataSourceFactory.class.getResourceAsStream(DB_FILE));
//            MysqlDataSource mysqlDataSource = new MysqlDataSource();
//            mysqlDataSource.setUrl(properties.getProperty("DB_URL"));
//            mysqlDataSource.setDatabaseName(properties.getProperty("DB_NAME"));
//            // mysqlDataSource.setCharacterEncoding(properties.getProperty(DB_CHARACTER_ENCODING));
//            mysqlDataSource.setUser(properties.getProperty("DB_USERNAME"));
//            mysqlDataSource.setPassword(properties.getProperty("DB_PASSWORD"));
//            dataSource = mysqlDataSource;
//            LOG.info("DataSource created: " + dataSource);
//        } catch (IOException e) {
//            LOG.error("Error while reading properties from file!", e);
//        }
//    }

    static{
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/myDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection () {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error("Error while connection creation", e);
        }
//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException e) {
//            LOG.error("Error while connection creation", e);
//        }


        return connection;
    }

    public static PreparedStatement getPreparedStatement (String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }

}
