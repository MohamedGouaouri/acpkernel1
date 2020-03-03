package kernel;
//la classe eigenspace
public class EigenSpace {

    private int dimension;
    private double[][] basevectors;
    public EigenSpace(int dimension, double[][] basevectors){
        this.dimension = dimension;
        this.basevectors = basevectors;
    }
    public double calculateDistance(double[] v1, double[] v2){
        return 0;
    }

    public double[] getCoordinates(double[] v){
        return null;
    }

    // setters and getters


    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public double[][] getBasevectors() {
        return basevectors;
    }

    public void setBasevectors(double[][] basevectors) {
        this.basevectors = basevectors;
    }
}
