package com.study.persistence.dao;

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

    static{
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/myDB");
            LOG.info("Data source : " + dataSource.toString());

        } catch (NamingException e) {
            LOG.error("Could not get datasource", e);
        }
    }

    public static Connection getConnection () {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error("Error while connection creation", e);
        }

        return connection;
    }

    public static PreparedStatement getPreparedStatement (String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }

}
