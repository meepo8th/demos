package util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author meepoth
 * @date 2017/11/23
 */
public class GenerateDatabaseDesign {
    private static HashMap<String, Boolean> hasInit = new HashMap<>();
    private String connectStr;
    private String connectType;

    //数据库类型配置，如新增需配置加载类与查询sql
    private static HashMap<String, String> typeClass = new HashMap() {{
        put(GenerateConstant.TYPE_MYSQL, GenerateConstant.MY_SQL_CLASS);
    }};

    private static HashMap<String, String> typeColumnSql = new HashMap() {{
        put(GenerateConstant.TYPE_MYSQL, GenerateConstant.MY_SQL_SQL_COLUMN);
    }};

    private static HashMap<String, String> typeTableSql = new HashMap() {{
        put(GenerateConstant.TYPE_MYSQL, GenerateConstant.MY_SQL_SQL_TABLE);
    }};

    private static HashMap<String, String> typeTableAllSql = new HashMap() {{
        put(GenerateConstant.TYPE_MYSQL, GenerateConstant.MY_SQL_SQL_TABLE_ALL);
    }};

    private static HashMap<String, String> typeIndexSql = new HashMap() {{
        put(GenerateConstant.TYPE_MYSQL, GenerateConstant.MY_SQL_SQL_INDEX);
    }};

    private static HashMap<String, HashMap<String, String>> typeConvert = new HashMap() {{
        put(GenerateConstant.TYPE_MYSQL, GenerateConstant.typeMySqlConvert);
    }};


    public GenerateDatabaseDesign(String connectStr, String connectType) {
        this.connectStr = connectStr;
        this.connectType = connectType;
        init();
    }

    public GenerateDatabaseDesign(String connectStr) {
        this.connectStr = connectStr;
        this.connectType = GenerateConstant.TYPE_MYSQL;
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


    /**
     * 获取所有表名
     *
     * @param
     * @return
     */
    private List<String> generateAllTable(String user) {
        List<String> allTables = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connectStr); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(String.format(typeTableAllSql.get(connectType), user))) {
            while (rs.next()) {
                allTables.add(rs.getString("TABLE_NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTables;
    }

    /**
     * 生成表的设计
     *
     * @param tableName
     * @return
     */
    private String generateTableString(String user, String tableName) {
        StringBuffer sb = new StringBuffer("");
        try (Connection conn = DriverManager.getConnection(connectStr); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(String.format(typeTableSql.get(connectType), user, tableName))) {
            String columnFormat = "- %s %s\r\n";
            String name;
            String comment;
            while (rs.next()) {
                name = rs.getString("TABLE_NAME");
                comment = rs.getString("TABLE_COMMENT");
                sb.append(String.format(columnFormat, name, comment));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("\r\n");
        return sb.toString();
    }

    /**
     * 生成表的列设计
     *
     * @param tableName
     * @return
     */
    private String generateColumnString(String user, String tableName) {
        StringBuffer sb = new StringBuffer("|列名|类型|列注释|\r\n|----|------|----|\r\n");
        try (Connection conn = DriverManager.getConnection(connectStr); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(String.format(typeColumnSql.get(connectType), user, tableName))) {
            String columnFormat = "|%s|%s|%s|\r\n";
            String name;
            String comment;
            String type;
            while (rs.next()) {
                name = rs.getString("name");
                comment = rs.getString("comment");
                type = rs.getString("type");
                sb.append(String.format(columnFormat, name, type, comment));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("\r\n");
        return sb.toString();
    }

    /**
     * 生成表的索引设计
     *
     * @param tableName
     * @return
     */
    private String generateIndexString(String userName, String tableName) {
        StringBuffer sb = new StringBuffer("|索引名称|索引类型|索引字段|索引注释|\r\n|----|------|----|---|\r\n");
        try (Connection conn = DriverManager.getConnection(connectStr); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(String.format(typeIndexSql.get(connectType), userName, tableName))) {
            String columnFormat = "|%s|%s|%s|%s|\r\n";
            String name = "";
            String comment = "无";
            String cols = "";
            String type = "";
            while (rs.next()) {
                name = rs.getString("Key_name");
                type = rs.getString("Non_unique");
                if (name.equals(rs.getString("Key_name"))) {
                    cols += ("," + rs.getString("Column_name"));
                } else {
                    sb.append(String.format(columnFormat, name, "0".equals(type) ? "唯一索引" : "普通索引", cols, comment));
                    cols = rs.getString("Column_name");
                }
            }
            if (!sb.toString().contains(String.format(columnFormat, name, "0".equals(type) ? "唯一索引" : "普通索引", cols, comment))) {
                sb.append(String.format(columnFormat, name, "0".equals(type) ? "唯一索引" : "普通索引", cols, comment));
            }
            sb.append("\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("\r\n");
        return sb.toString();
    }

    private void createAndWriteFile(String path, String tableName, String classContent, String suffix, String log) {
        String sqlFileName = path + "/" + LeanItStringUtil.capFirst(LeanItStringUtil.transLateUnderLine2Upper(tableName)) + suffix;
        File sqlFile = new File(sqlFileName);
        if (sqlFile.exists()) {
            sqlFile.delete();
        }
        BufferedWriter bufferedWriter = null;
        try {
            sqlFile.createNewFile();
            bufferedWriter = new BufferedWriter(new FileWriter(sqlFile));
            bufferedWriter.write(classContent);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedWriter) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.format(log, sqlFileName);
    }

    /**
     * 生成一个表的设计
     *
     * @param user
     * @param tableName
     * @return
     */
    public String generateTableDesign(String user, String tableName) {
        StringBuffer tableDesign = new StringBuffer();
        tableDesign.append(generateTableString(user, tableName));
        tableDesign.append(generateColumnString(user, tableName));
        tableDesign.append(generateIndexString(user, tableName));
        return tableDesign.toString();
    }

    /**
     * 生成所有表的设计
     *
     * @param user
     * @return
     */
    public String generateAllTableDesign(String user) {
        StringBuffer tableDesign = new StringBuffer();
        List<String> tables = generateAllTable(user);
        for (String table : tables) {
            tableDesign.append(generateTableDesign(user, table));
        }
        return tableDesign.toString();
    }

    public void generateClassFile(String path, String user) {
        String classContent = generateAllTableDesign(user);
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        createAndWriteFile(path, user, classContent, ".md", "%s md file complete\r\n");
    }

    public static void main(String[] args) throws Exception {
        String connectStr = "jdbc:mysql://192.168.2.205:3306/database_user?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=Hongkong&user=root&password=root";
        GenerateDatabaseDesign generateDatabaseDesign = new GenerateDatabaseDesign(connectStr);
        generateDatabaseDesign.generateClassFile("f:/databaseDesign", "database_user");
    }
}
