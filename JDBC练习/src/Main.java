import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.util.Scanner;
import java.sql.*;
/**
 * jdbc实现简易的博客系统
 */
public class Main {
    private static int globalUserId = -1;
    private static String globalUsername = null;

    // 定义为全局变量，减少代码的冗余
    private static final String url = "jdbc:mysql://127.0.0.1:3306/csdnusejdbc?useSSL=false&characterEncoding=utf8";
    private static final String mysqlUsername = "root";
    private static final String mysqlPassword = "";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            menu();
            int select  = scanner.nextInt();
            scanner.nextLine(); //把换行读走
            switch (select) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    publish();
                    break;
            }
        }
    }

    //发表文章

    private static void publish() throws SQLException {
        if (globalUserId == -1) {
            System.out.println("Qing xian denglu");
            return;
        }
        // 需要用户名输入标题 + 正文
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        String content = scanner.nextLine();

        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("");
        mysqlDataSource.setDatabaseName("csdnusejdbc");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");
        DataSource dataSource = mysqlDataSource;

        String sql = "INSERT INTO articles (author_id, title, content) VALUES (?, ?, ?)";
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setInt(1, globalUserId);
                statement.setString(2, title);
                statement.setString(3, content);

                statement.executeUpdate();

                System.out.println("published success");
            }
        }
    }


    //用户登录
    private static void login() throws SQLException {
        // 需要用户名输入用户名 + 密码
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        String password = scanner.nextLine();

        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("");
        mysqlDataSource.setDatabaseName("java20_0211");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");
        DataSource dataSource = mysqlDataSource;

        /*
        方法1：
        try(Connection connection = DriverManager.getConnection(url,mysqlUsername,mysqlPassword)) {
            try(Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "SELECT id, username FROM users WHERE username = '%s' AND password = '%s'",
                        username, password
                );
                System.out.println(sql);

                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    if (!resultSet.next()) {
                        System.out.println("登录失败");
                    } else {
                        int id = resultSet.getInt("id");
                        String usernameInTable = resultSet.getString("username");
                        System.out.println("登录成功: " + id + ", " + usernameInTable);
                    }
                }
            }
        }
        */


            // 方法2
           // ? placeholder 占位符
            String sql = "SELECT id, username FROM users WHERE username = ? AND password = ?";
            try(Connection connection = DriverManager.getConnection(url,mysqlUsername,mysqlPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // 类似 ResultSet
                // 1. 各种各样的类型
                // 2. 下标从 1 开始
                statement.setString(1, username);
                statement.setString(2, password);

                // MySQL Driver 时打印 SQL 的小技巧
                // JDBC 规定中，PrepareStatement 是无法打印填充完占位符后的 SQL
                // PrepareStatement 的实现类 com.mysql.jdbc.PreparedStatement
                // 有个方法 asSql 干这个事情的
                // 利用向下转型完成
                com.mysql.jdbc.PreparedStatement mysqlStatement = (com.mysql.jdbc.PreparedStatement) statement;
                System.out.println(mysqlStatement.asSql());

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (!resultSet.next()) {
                        System.out.println("登录失败");
                    } else {
                        int id = resultSet.getInt("id");
                        String usernameInTable = resultSet.getString("username");
                        globalUserId = id;
                        globalUsername = usernameInTable;
                        System.out.println("登录成功: " + id + ", " + usernameInTable);
                    }
                }
            }
        }
    }

    //注册用户
    private static void register() throws SQLException {
        //输入用户名和密码
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        /*
        方法1：
        Connection connection = DriverManager.getConnection(url,mysqlUsername,mysqlPassword);
        Statement statement = connection.createStatement();

        String sql = String.format(
                "INSERT INTO users(username,password) VALUES ('%s','%s')",username,password);
        System.out.println(sql);
        statement.executeUpdate(sql);

        statement.close();
        connection.close();

        */


        //方法2：引入 try-with-resource
    try(Connection connection = DriverManager.getConnection(url,mysqlUsername,mysqlPassword)) {
        try(Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "INSERT INTO users (username,password) VALUES ('%s','%s')",username,password);
            System.out.println(sql);
            statement.executeUpdate(sql);
        }
    }
        System.out.println("用户注册成功");

    }

    private static void menu() {
        System.out.println("=====================");
        System.out.println("1. 用户注册");
        System.out.println("2. 用户登录");
        System.out.println("3. 发表文章");
        System.out.println("4. 文章列表页");
        System.out.println("5. 文章详情页");
        System.out.println("=====================");
    }

}
