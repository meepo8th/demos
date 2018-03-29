package deeplearning.matrix;

/**
 * 矩阵异常
 *
 * @author admin
 * @date 2018/1/24
 */
public class MatrixException extends Exception {
    public static final String MULTI_WITHOUT_SAME_ROWS_COLS = "乘数矩阵的行数不等于被乘矩阵的行数";

    public MatrixException(String msg) {
        super(MULTI_WITHOUT_SAME_ROWS_COLS);
    }
}
