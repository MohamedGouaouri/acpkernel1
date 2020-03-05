package kernel;

import weka.core.matrix.Matrix;

public class Util {

    // TODO: 05/03/2020 make it secure
    public static Matrix getColumnVector(Matrix data, int n){
        assert n < data.getColumnDimension();
        double[] v = new double[data.getRowDimension()];
        for (int i = 0; i < data.getRowDimension(); i++) {
            v[i] = data.get(i, n);
        }
        return new Matrix(v, data.getRowDimension());
    }

    // TODO: 05/03/2020 make it secure
    public static Matrix fillToDuplicatedMatrix(Matrix vector, int n){
        // create new matrix filled with zero of dimension len(v) x n
        Matrix matrix = new Matrix(vector.getRowDimension(), n);
        for (int i = 0; i < matrix.getRowDimension(); i++) {

            for (int j = 0; j < matrix.getColumnDimension(); j++) {
                matrix.set(i, j, vector.get(i, 0));
            }
        }
        return matrix;
    }

    // TODO: 05/03/2020  Make it secure
    public static void replaceColumn(Matrix matrix, Matrix column, int n){
        for (int i = 0; i < matrix.getRowDimension() ; i++) {
            matrix.set(i, n, column.get(i, 0));
        }
        //return matrix;
    }
}
