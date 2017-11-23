package util;


import java.sql.*;
import java.util.HashMap;

/**
 * Created by meepoth on 2017/11/23.
 */
public class GenerateDao {
    private static HashMap<String, Boolean> hasInit = new HashMap<>();
    private String connectStr;
    private String connectType;
    public static final String TYPE_MYSQL = "MYSQL";
    private static final String MY_SQL_CLASS = "com.mysql.jdbc.Driver";
    private static final String MY_SQL_SQL = "select column_name name, column_comment comment,data_type type from information_schema.columns where table_name ='%s' order by table_name";

    //数据库类型配置，如新增需配置加载类与查询sql
    private static HashMap<String, String> typeClass = new HashMap() {{
        put(TYPE_MYSQL, MY_SQL_CLASS);
    }};

    private static HashMap<String, String> typeSql = new HashMap() {{
        put(TYPE_MYSQL, MY_SQL_SQL);
    }};
    private static HashMap<String, String> typeMyConvert = new HashMap() {{
        put("VARCHAR", "String");
        put("CHAR", "String");
        put("BLOB", "byte[]");
        put("TEXT", "String");
        put("INTEGER", "Long");
        put("INT", "Long");
        put("TINYINT", "Integer");
        put("SMALLINT", "Integer");
        put("MEDIUMINT", "Integer");
        put("BIT", "Boolean");
        put("BIGINT", "BigInteger");
        put("FLOAT", "Float");
        put("DOUBLE", "Double");
        put("DECIMAL", "BigDecimal");
        put("BOOLEAN", "Integer");
        put("ID", "Long");
        put("DATE", "Date#@SqlType(type = \"date\", format = \"%Y-%m-%d\")");
        put("TIME", "Date#@SqlType(type = \"date\", format = \"%T\")");
        put("DATETIME", "Date#@SqlType(type = \"date\", format = \"%Y-%m-%d %T\")");
        put("TIMESTAMP", "Date#@SqlType(type = \"date\", format = \"%Y-%m-%d %T\")");
        put("YEAR", "Date#@SqlType(type = \"date\", format = \"%Y\")");
    }};
    private static HashMap<String, HashMap> typeConvert = new HashMap() {{
        put(TYPE_MYSQL, typeMyConvert);
    }};


    public GenerateDao(String connectStr, String connectType) {
        this.connectStr = connectStr;
        this.connectType = connectType;
        init();
    }

    public GenerateDao(String connectStr) {
        this.connectStr = connectStr;
        this.connectType = TYPE_MYSQL;
        init();
    }

    private void init() {
        if (null == hasInit.get(connectType) || hasInit.get(connectType)) {
            System.out.format("init %s start\r\n", connectType);
            try {
                Class.forName(typeClass.get(connectType));
                hasInit.put(connectType, true);
                System.out.format("init %s end\r\n", connectType);
            } catch (ClassNotFoundException e) {
                System.out.format("init %s failed\r\n", connectType);
            }
        }
    }


    public static void main(String[] args) throws Exception {

        Connection conn = null;

        String sql;

        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值

        // 避免中文乱码要指定useUnicode和characterEncoding

        String url = "jdbc:mysql://115.28.243.207:3306/totalstation?useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull&user=totalstation&password=totalstation@207";

        try {

            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，

            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以

            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动

            // or:

            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();

            // or：

            // new com.mysql.jdbc.Driver();

            System.out.println("成功加载MySQL驱动程序");

            // 一个Connection代表一个数据库连接

            conn = DriverManager.getConnection(url);

            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等

            Statement stmt = conn.createStatement();

            sql = "createtable student(NO char(20),name varchar(20),primary key(NO))";

            int result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功

            if (result != -1) {

                System.out.println("创建数据表成功");

                sql = "insert into student(NO,name) values('2016001','刘大')";

                result = stmt.executeUpdate(sql);

                sql = "insert into student(NO,name) values('2016002','陈二')";

                result = stmt.executeUpdate(sql);

                sql = "select * from student";

                ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值

                System.out.println("学号\t姓名");

                while (rs.next()) {

                    System.out.println(rs.getString(1) + "\t" + rs.getString(2));// 入如果返回的是int类型可以用getInt()

                }

            }

        } catch (SQLException e) {

            System.out.println("MySQL操作错误");

            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            conn.close();

        }

    }
}
