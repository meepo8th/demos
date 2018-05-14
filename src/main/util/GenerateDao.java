package util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

/**
 * Created by meepoth on 2017/11/23.
 */
public class GenerateDao {
    private static HashMap<String, Boolean> hasInit = new HashMap<>();
    private String connectStr;
    private String connectType;

    //数据库类型配置，如新增需配置加载类与查询sql
    private static HashMap<String, String> typeClass = new HashMap() {{
        put(GenerateConstant.TYPE_MYSQL, GenerateConstant.MY_SQL_CLASS);
    }};

    private static HashMap<String, String> typeSql = new HashMap() {{
        put(GenerateConstant.TYPE_MYSQL, GenerateConstant.MY_SQL_SQL);
    }};

    private static HashMap<String, HashMap<String, String>> typeConvert = new HashMap() {{
        put(GenerateConstant.TYPE_MYSQL, GenerateConstant.typeMySqlConvert);
    }};


    public GenerateDao(String connectStr, String connectType) {
        this.connectStr = connectStr;
        this.connectType = connectType;
        init();
    }

    public GenerateDao(String connectStr) {
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

    public void generateClassFile(String tableName) {
        String outpath = "/generateClass/";
        generateClassFile(outpath, tableName);
    }

    public void generateSqlFile(String tableName) {
        String outpath = "/generateSql/";
        generateSqlFile(outpath, tableName);
    }

    public void generateClassFile(String path, String tableName) {
        String classContent = generateClassString(tableName);
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        createAndWriteFile(path, tableName, classContent, ".java", "%s class file complete\r\n");
    }

    public void generateSqlFile(String path, String tableName) {
        String classContent = generatSqlString(tableName);
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        createAndWriteFile(path, tableName, classContent, ".sql", "%s sql file complete\r\n");
    }

    private String generatSqlString(String tableName) {
        Connection conn = null;
        StringBuffer sb = new StringBuffer();
        try {
            conn = DriverManager.getConnection(connectStr);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(String.format(typeSql.get(connectType), tableName));// executeQuery会返回结果的集合，否则返回空值
            String columnFormat = "    %s,%s\r\n";
            String name;
            String fieldName;

            while (rs.next()) {
                name = rs.getString("name");
                fieldName = ( "get"+LeanItStringUtil.capFirst(LeanItStringUtil.transLateUnderLine2Upper(name))+"();");
                sb.append(String.format(columnFormat, name, fieldName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private void createAndWriteFile(String path, String tableName, String classContent, String s, String s2) {
        String sqlFileName = path + "/" + LeanItStringUtil.capFirst(LeanItStringUtil.transLateUnderLine2Upper(tableName)) + s;
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
        System.out.format(s2, sqlFileName);
    }

    public String generateClassString(String tableName) {
        Connection conn = null;
        StringBuffer sb = new StringBuffer();
        sb.append("public class " + LeanItStringUtil.capFirst(LeanItStringUtil.transLateUnderLine2Upper(tableName)) + "{");
        try {
            conn = DriverManager.getConnection(connectStr);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(String.format(typeSql.get(connectType), tableName));// executeQuery会返回结果的集合，否则返回空值
            String columnFormat = "    %s\r\n    private %s %s;//%s\r\n";
            String sqlType = "";
            String type = "";
            String name = "";
            String comment = "";
            String columnNames = "";
            String columnValues = "";
            while (rs.next()) {
                name = rs.getString("name");
                columnNames += ("," + name);
                columnValues += (String.format(",#{item.%s,jdbcType=%s}", LeanItStringUtil.transLateUnderLine2Upper(name), rs.getString("type").toUpperCase()));
                type = (typeConvert.get(connectType)).get(rs.getString("type").toUpperCase());
                comment = rs.getString("comment");
                if (type.split("#").length > 1) {
                    sqlType = type.split("#")[1];
                    type = type.split("#")[0];
                } else {
                    sqlType = "";
                }
                sb.append(String.format(columnFormat, sqlType, type, LeanItStringUtil.transLateUnderLine2Upper(name), comment));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String connectStr = "jdbc:mysql://192.168.2.205:3306/openfire?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=Hongkong&user=root&password=root";
        String[] tables = new String[]{"t_disease",
                "t_parse_disease",
                "t_parse_symptom",
                "t_parse_properties",
                "t_parse_symptom_properties",
        };
        GenerateDao generateDao = new GenerateDao(connectStr);
        for (String table : tables) {
            generateDao.generateClassFile(table);
            generateDao.generateSqlFile(table);
        }
    }
}
