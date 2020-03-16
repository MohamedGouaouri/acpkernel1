package kernel;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.opencv.core.*;
import weka.core.matrix.Matrix;
import java.io.File;



public class Main{


    public static void main(String[] args){
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            //launch(args);
            Matrix dataSet = importerImages("orl");
            dataSet.minusEquals(Util.fillToDuplicatedMatrix(calculerVisageMoyen(dataSet), 200));
            Matrix classes = getClasses(dataSet);
            //Matrix eigenvectors = dataSet.svd().getU();
    //      eigenvectors.timesEquals(200);
    //      Matrix singularValues = dataSet.svd().getS();
    //      Matrix eigenvalues = squareDiagonal(singularValues);
            System.out.println(Util.getColumnVector(dataSet, 1).toMatlab());

    }

    public static Matrix reduireDimensions(Matrix eigenvectors, Matrix eigenvalues){

        double perc = 0.9;
        double trace = eigenvalues.trace();
        double s = 0;
        int i = 0;
        int cols = eigenvalues.getColumnDimension();
        int rows = eigenvalues.getRowDimension();
        while ((s <= perc * trace) && (i < cols) && (i < rows)){
            s += eigenvalues.get(i, i);
            i++;
        }

        Matrix out = new Matrix(eigenvectors.getRowDimension(), i);
        for (int j = 0; j < i; j++) {
            Util.replaceColumn(out, Util.getColumnVector(eigenvectors, j), j);
        }
        return out;
    }

    public static Matrix importerImages(String path){
         int trainImagesNumber = 5;
         int height = 112;
         int width = 92;

        File directory = new File(path);
        Matrix total = new Matrix(height * width, directory.listFiles().length * trainImagesNumber);
        int i = 0;
        int j = 0;
        File[] images = new File[10];
        for (File fd : directory.listFiles()) {
            images = fd.listFiles();
            for (i = 0; i < trainImagesNumber; i++) {
                Matrix mat = ImageMat.imageToVector(images[i].getPath());
                //Util.replaceColumn(dataSet, mat, i + j);
                Util.replaceColumn(total,mat,i+j);
            }
            j = j + trainImagesNumber;
        }

        return total;
    }


    public static Matrix calculerVisageMoyen(Matrix dataSet){
        Matrix mean = new Matrix(dataSet.getRowDimension(), 1);
        int columnDim = dataSet.getColumnDimension();
        double s;
        for (int i = 0; i < dataSet.getRowDimension(); i++) {
            s = 0;
            for (int j = 0; j < dataSet.getColumnDimension(); j++) {
                s += dataSet.get(i, j);
            }
            mean.set(i, 0, Math.floor(s/columnDim));
        }
        return mean;
    }


    public static Matrix mean(Matrix... matrices){
        Matrix mean = new Matrix(matrices[0].getRowDimension(), 1);

        for (int i = 0; i <matrices.length ; i++) {
            mean.plusEquals(matrices[i]);
        }
        mean.timesEquals(0.2);

        return  mean;
    }


    public static Matrix getClasses(Matrix dataSet){
        Matrix centers = new Matrix(dataSet.getRowDimension(), Math.floorDiv(dataSet.getColumnDimension(), 5));

        int j = 0;
        for (int i = 0; i < dataSet.getColumnDimension() - 4; i+=5) {
            Matrix vector1 = Util.getColumnVector(dataSet, i);
            Matrix vector2 = Util.getColumnVector(dataSet, i+1);
            Matrix vector3 = Util.getColumnVector(dataSet, i+2);
            Matrix vector4 = Util.getColumnVector(dataSet, i+3);
            Matrix vector5 = Util.getColumnVector(dataSet, i+4);
            Util.replaceColumn(centers, mean(vector1, vector2, vector3, vector4, vector5), j);

            j++;
        }

        return centers;
    }

//    @Override
//    public void start(Stage stage) throws Exception {
//        ScrollPane scrollPane = new ScrollPane();
//        VBox vbox = new VBox();
//        scrollPane.setContent(vbox);
//
//        Matrix dataSet = importerImages("orl");
//        //dataSet.minusEquals(Util.fillToDuplicatedMatrix(calculerVisageMoyen(dataSet), 200));
//        Matrix classes = getClasses(dataSet);
//
//          Matrix eigenvectors = dataSet.svd().getU();
//          eigenvectors.timesEquals(200);
////        Matrix singularValues = dataSet.svd().getS();
////        Matrix eigenvalues = squareDiag(singularValues);
//
//        //System.out.println(Util.getColumnVector(eigenvectors, 100));
//
//        for (int i = 0; i < 200; i++) {
//            vbox.getChildren().add(new ImageView(ImageMat.vectorToImage(Util.getColumnVector(eigenvectors, i))));
//        }
//
//        Scene scene = new Scene(scrollPane, 300, 300);
//        stage.setScene(scene);
//        stage.show();
//    }



}

