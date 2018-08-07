package util;

import java.util.HashMap;

/**
 * Created by admin on 2017/11/23.
 */
public class GenerateConstant {
    public static final String TYPE_MYSQL = "MYSQL";
    public static final String MY_SQL_CLASS = "com.mysql.jdbc.Driver";
    public static final String MY_SQL_SQL_COLUMN = "select column_name name, column_comment comment,data_type type from information_schema.columns where table_schema='%s' and table_name ='%s' order by table_name";
    public static final String MY_SQL_SQL_COLUMN_DESIGN = "select column_name name, column_comment comment,column_type type from information_schema.columns where table_schema='%s' and table_name ='%s' order by table_name";
    public static final String MY_SQL_SQL_TABLE = "select * from information_schema.`TABLES` where TABLE_SCHEMA='%s' and table_name='%s' order by table_name";
    public static final String MY_SQL_SQL_TABLE_ALL = "select * from information_schema.`TABLES` where TABLE_SCHEMA='%s' order by table_name";
    public static final String MY_SQL_SQL_INDEX = "show index from %s.%s";
    ;


    public static final HashMap<String, String> typeMySqlConvert = new HashMap() {{
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
        put("DATE", "String#@SqlType(type = \"date\", format = \"%Y-%m-%d\")");
        put("TIME", "String#@SqlType(type = \"date\", format = \"%T\")");
        put("DATETIME", "String#@SqlType(type = \"date\", format = \"%Y-%m-%d %T\")");
        put("TIMESTAMP", "String#@SqlType(type = \"date\", format = \"%Y-%m-%d %T\")");
        put("YEAR", "Date#@SqlType(type = \"date\", format = \"%Y\")");
    }};
}
