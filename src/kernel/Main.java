package kernel;

import weka.core.matrix.Matrix;

public class Main {

    public static void main(String[] args) {
        double[][] data = { {1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 9}};
        Matrix matrix = new Matrix(data);
        Util.replaceColumn(matrix, new Matrix(3, 1), 0);
        System.out.println(matrix.toMatlab());
    }
}
