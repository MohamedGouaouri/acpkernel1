package kernel;


import weka.core.matrix.Matrix;

import java.util.ArrayList;

public class Acp {

    private int totalTrainImagesNumber = 200;
    private int trainImagesNumber = 5;

    // TODO: 03/03/2020 give a value to threshold
    private double threshold;
    private double[][] dataSet;

    private EigenSpace eigenSpace;

    // PCA constructor
    public Acp(double[][] dataSet){
        this.dataSet = dataSet;
    }

    // TODO: 03/03/2020 implement this method
    public void importerImages(){

    }
    // TODO: 03/03/2020 implement this method
    public double[] calculerVisageMoyen(double[][] dataSet){
        return null;
    }


    // TODO: 03/03/2020 implement this method
    // create the eigenspace from dataSet
    public EigenSpace creerEigenSpace(double[][] eigenVectors, int dim){
        return null;
    }

    // dimensionality reduction
    public double[][] reduireDimension(double[][] eigenVectors){
        return null;
    }


    // import a new face
    public double[] importerVisage(String path){
        return null;
    }

    // data projection on the new space
    public double[][] projectData(EigenSpace eigenSpace, double[][] dataSet){
        double[][] projectedData = new double[dataSet.length][eigenSpace.getDimension()];
        for (int i = 0; i < dataSet.length; i++){
            double[] coordinates = eigenSpace.getCoordinates(dataSet[i]);
            projectedData[i] = coordinates;
        }
        return projectedData;
    }

    // our main method used to train the model
    public double[][] trainModel(){

        // import images
        importerImages();

        // calculate the mean
        double[] mean = calculerVisageMoyen(dataSet);

        // create data set matrix
        Matrix dataSetMatrix = new Matrix(dataSet);

        // subtract the mean from the data set
        dataSetMatrix.minus(new Matrix(mean, 1));

        // get covariance matrix eigenvalues and eigenvectors
        Matrix eigenVectors = dataSetMatrix.svd().getU();

        // create the eigenspace
        int dim = reduireDimension(eigenVectors.getArray()).length; // eigenspace dimension
        eigenSpace = creerEigenSpace(eigenVectors.getArray(), dim);

        // data projection on the new space
        double[][] projectedDataSet = projectData(eigenSpace, dataSet);

        return projectedDataSet;
    }


    // recognize the new image
    public Result recognize(Image inputFace, double[][] projectedDataSet){

        assert eigenSpace != null;

        double[] faceData = inputFace.imageToVector();
        // calculate distances
        ArrayList<Double> distances = new ArrayList<>();
        for (int i = 0; i < totalTrainImagesNumber; i++){
            distances.add(eigenSpace.calculateDistance(faceData, projectedDataSet[i]));
        }

        int elementsLessThanThresholdNum = 0;
        for (double d:distances
             ) {
            if (d <= threshold){
                elementsLessThanThresholdNum ++;
            }
        }
        if (elementsLessThanThresholdNum == 0){
            return Result.REJETE;
        }
        if (elementsLessThanThresholdNum == 1){
            return Result.RECONNUE;
        }
        else {
            return Result.CONFUSION;
        }
    }

    // setters and getters
    public int getTotalTrainImagesNumber() {
        return totalTrainImagesNumber;
    }

    public void setTotalTrainImagesNumber(int totalTrainImagesNumber) {
        this.totalTrainImagesNumber = totalTrainImagesNumber;
    }

    public int getTrainImagesNumber() {
        return trainImagesNumber;
    }

    public void setTrainImagesNumber(int trainImagesNumber) {
        this.trainImagesNumber = trainImagesNumber;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
}
