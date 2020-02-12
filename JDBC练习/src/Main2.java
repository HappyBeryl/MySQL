import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class Main2 {
    private static final DataSource datasource;

    static {
        //进行服务器的配置
        MysqlDataSource mysqlDataSource = new MysqlDataSource();

        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);

        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("");

        mysqlDataSource.setDatabaseName("JDBCDemo");

        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");

        datasource = mysqlDataSource;
    }

    public static void main(String[] args) throws SQLException {
        createTable();
        insert();
        select();
    }

    //建一个user表
    public static void createTable() throws SQLException {
        String sql = "CREATE TABLE users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "username VARCHAR(255)" +
                ")";
        try(Connection connection = datasource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            }
        }
    }

    //插入
    public static void insert() throws SQLException {
        String[] str = {"a","b","c","d"};

        String sql = "INSERT INTO users (username) VALUES (?)"; //占位符填充

        try(Connection connection = datasource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
                for(String name : str) {
                    statement.setString(1,name);
                    statement.executeUpdate();
                    try (ResultSet rs = statement.getGeneratedKeys()) {
                        rs.next();
                        int id = rs.getInt(1);
                        System.out.println("插入成功: " + id + ", " + name);
                    }
                }
            }
        }
    }

    //查询
    public static void select() throws SQLException {
        String sql = "SELECT id, username FROM users";
        try (Connection c = datasource.getConnection()) {
            try (PreparedStatement statement = c.prepareStatement(sql)) {
                try (ResultSet rs = statement.executeQuery()) {
                    System.out.println("查询成功");
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String username = rs.getString("username");
                        System.out.println(id + ", " + username);
                    }
                }
            }
        }
    }

}
