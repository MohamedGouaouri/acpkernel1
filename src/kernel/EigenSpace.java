package kernel;

import weka.core.matrix.Matrix;

//la classe eigenspace
public class EigenSpace {

    private int dimension;
    private Matrix basevectors;
    public EigenSpace(int dimension, Matrix basevectors){
        this.dimension = dimension;
        this.basevectors = basevectors;
    }
    public double calculateDistance(Matrix v1, Matrix v2){
        return 0;
    }

    public Matrix getCoordinates(Matrix v){
        return null;
    }

    // setters and getters


    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public Matrix getBasevectors() {
        return basevectors;
    }

    public void setBasevectors(Matrix basevectors) {
        this.basevectors = basevectors;
    }
}
