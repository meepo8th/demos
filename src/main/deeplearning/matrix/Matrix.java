package deeplearning.matrix;

import java.util.Arrays;

/**
 * 矩阵
 *
 * @author admin
 * @date 2018/1/24
 */
public class Matrix {
    private double[][] content;
    private int rows;
    private int cols;

    public Matrix(double[][] content) {
        setContent(content);
    }

    /**
     * 转置
     */
    public Matrix transpose() {
        double[][] transposedContent = new double[cols][rows];
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[i].length; j++) {
                transposedContent[j][i] = content[i][j];
            }
        }
        return new Matrix(transposedContent);
    }

    /**
     * 求逆
     */
    public Matrix inverse() {
        return new Matrix(content);
    }

    /**
     * 矩阵相乘
     *
     * @param multiMatrix
     * @return
     */
    public Matrix multiply(Matrix multiMatrix) throws MatrixException {
        if (rows != multiMatrix.getCols()) {
            throw new MatrixException(MatrixException.MULTI_WITHOUT_SAME_ROWS_COLS);
        }
        double[][] result = new double[rows][multiMatrix.cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                for(int k=0;k<cols;k++){
                    result[j][i]+=content[j][k]*multiMatrix.getContent()[k][j];
                }

            }
        }
        return new Matrix(result);
    }

    /**
     * 矩阵相乘
     *
     * @param multiMatrix
     * @return
     */
    public Matrix multiply(double[][] multiMatrix) throws MatrixException {
        return multiply(new Matrix(multiMatrix));
    }

    public void setContent(double[][] content) {
        if (null != content) {
            this.content = content;
            rows = content.length;
            if (rows > 0) {
                cols = content[0].length;
            }
        }
    }

    public double[][] getContent() {
        return content;
    }


    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


    @Override
    public String toString() {
        return "Matrix{" +
                "content=" + Arrays.deepToString(content) +
                ", rows=" + rows +
                ", cols=" + cols +
                '}';
    }

    public static void main(String[] args) throws MatrixException {
        Matrix matrix = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}});
        System.out.println("matrix:" + matrix);
        System.out.println("transpose:" + matrix.transpose());
        System.out.println("inverse:" + matrix.inverse());
        System.out.println("multiply transpose:" + matrix.multiply(matrix.transpose()));
        System.out.println("multiply inverse:" + matrix.multiply(new double[][]{{1,2},{3,4}}));
    }
}
