package kernel;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.opencv.core.*;
import weka.core.Utils;
import weka.core.matrix.Matrix;
import java.io.File;
import java.util.ArrayList;


public class Main{


    public static void main(String[] args){
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            Acp pca = new Acp(3500);
            pca.trainModel();
            System.out.println("Training finished");
            Result result = pca.recognize("orl/PERSON1/7.bmp");
            System.out.println(result);

    }

}

