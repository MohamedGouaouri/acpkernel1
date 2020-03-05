package kernel;
//la classe acp

import nz.ac.waikato.cs.weka.Utils;
import weka.core.matrix.Matrix;

import java.util.ArrayList;
import java.util.Iterator;

public class Acp {

    private int totalTrainImagesNumber = 200;
    private int trainImagesNumber = 5;

    // TODO: 03/03/2020 give a value to threshold
    private double threshold;
    private Matrix dataSet;
    private Matrix mean;

    private EigenSpace eigenSpace = null;

    public Acp(Matrix dataSet){
        this.dataSet = dataSet;
    }

    // TODO: 03/03/2020 implement this method
    public void importFaces(){}

    // TODO: 03/03/2020 implement this method
    public Matrix calculerVisageMoyen(Matrix dataSet){
        return null;
    }


    // TODO: 05/03/2020 Implement this method
    // used to calculate the reduced dimension of the new eigenspace
    public int getEigenSpaceDimension(Matrix eigenvectors){
        return 0;
    }


    // TODO: 05/03/2020 Implement this method
    // create the eigenspace from dataSet
    public EigenSpace creerEigenSpace(Matrix eigenvectors, int dim){
        return null;
    }



    // project data onto the new eigenspace
    public Matrix projectData(EigenSpace eigenSpace, Matrix dataSet){
        // creating empty matrix that will hold the projected data on the new face space
        Matrix projectedData = new Matrix(eigenSpace.getDimension(), dataSet.getColumnDimension());

        for (int i = 0; i < dataSet.getColumnDimension() ; i++) {
                // coordinates matrix is a p x 1 matrix
                Matrix coordinates = eigenSpace.getCoordinates(Util.getColumnVector(dataSet, i));
                // insert coordinates matrix in the projected dataMatrix
                Util.replaceColumn(projectedData, coordinates, i);
        }

        return projectedData;
    }

    // our main method used to train the model
    public Matrix trainModel(){
        // import faced from database
        importFaces();

        // calculate mean
        mean = calculerVisageMoyen(dataSet);

        // subtract mean from data set
        dataSet.minusEquals(Util.fillToDuplicatedMatrix(mean, dataSet.getColumnDimension()));

        // calculate the eigenvectors of the covariance matrix
        Matrix eigenvectors = dataSet.svd().getU();
        // the reduced eigenspace dimension
        int dim = getEigenSpaceDimension(eigenvectors);

        // create the eigenspace
        eigenSpace = creerEigenSpace(eigenvectors, dim);

        // project data onto the new eigenspace
        Matrix projectedDataSet = projectData(eigenSpace, dataSet);

        return projectedDataSet;
    }


    // recognize the new image
    public Result recognize(Image inputFace){

        // suppose our model was trained

        // convert input face to vector
        Matrix inputFaceMatrix = inputFace.imageToVector();

        // subtract mean from inputFaceMatrix
        inputFaceMatrix.minusEquals(mean);

        // project the inputFaceMatrix onto the eigen space
        Matrix projectedInputFaceMatrix = projectData(eigenSpace, inputFaceMatrix);

        // calculate distances
        ArrayList<Double> distances = new ArrayList<>();
        for (int i = 0; i < dataSet.getColumnDimension() ; i++) {
            distances.add(eigenSpace.calculateDistance(Util.getColumnVector(dataSet, i), projectedInputFaceMatrix));
        }

        int foundFaces = 0;
        Iterator<Double> iterator = distances.iterator();
        while (iterator.hasNext()){
            if (iterator.next() <= threshold){
                foundFaces++;
            }
        }
        if (foundFaces == 0){
            return Result.REJETE;
        }
        if (foundFaces == 1){
            return Result.RECONNUE;
        }
        return Result.CONFUSION;
    }

}
