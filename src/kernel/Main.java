package kernel;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.opencv.core.*;
import weka.core.matrix.Matrix;


public class Main{

    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        double[][] data = {{1, 2, 3},
                            {4, 5, 7,},
                            {8, 9, 0}};
        Matrix matrix = new Matrix(data);
        double[] d = matrix.getRowPackedCopy();

        for (int i = 0; i < d.length; i++) {
            System.out.println(d[i]);
        }
    }

}
