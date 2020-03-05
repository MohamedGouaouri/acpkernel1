package kernel;

import weka.core.matrix.Matrix;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        double[][] data = { {1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 9}};
        Matrix matrix = new Matrix(data);
        Util.replaceColumn(matrix, new Matrix(3, 1), 0);
        System.out.println(matrix.toMatlab());

        System.out.println(String.format("orl/p1/%d.bmp", 3));




    }
}
