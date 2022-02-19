package service;

import dao.DataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbService {
    public static volatile DbService dbService;

    private DbService() {

    }

    public static DbService getInstance() {
        if (dbService == null) {
            synchronized (DbService.class) {
                if (dbService == null) {
                    dbService = new DbService();
                }
            }
        }
        return dbService;
    }

    public void createSchema() throws IOException, SQLException {
        Statement statement = getStatement();
        BufferedReader reader = getSchemaReader();

        String sql;
        StringBuilder builder = new StringBuilder();

        while (true) {
            sql = reader.readLine();
            if ((sql == null || sql.isEmpty()) && !builder.toString().isEmpty()) {
                statement.execute(builder.toString());
                if (sql == null) {
                    break;
                }
                builder = new StringBuilder();
            } else {
                builder.append(sql);
            }
        }
    }

    private BufferedReader getSchemaReader() {
        InputStream resourceAsStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("schema.sql");

        return new BufferedReader(new InputStreamReader(resourceAsStream));
    }

    private Statement getStatement() throws SQLException {
        Connection connection = DataSource.getConnection();
        return connection.createStatement();
    }
}
