package kernel;

import weka.core.matrix.Matrix;

public class Main {

    public static void main(String[] args) {
        double[][] data = {{1, 2, 3},
                           {4, 5, 6},
                            {7, 8, 9}};
        Matrix matrix = new Matrix(data);

        double[] d = {13, 45, 44};
        Matrix m = new Matrix(d, 3);

        matrix = Util.replaceColumn(matrix, m, 1);
        System.out.println(matrix.toMatlab());
    }
}
