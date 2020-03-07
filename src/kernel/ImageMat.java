package kernel;

import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import weka.core.matrix.Matrix;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

//la classe java
public class ImageMat{

    
    // construct an opencv matrix from an image
    public static Mat imageToMatcv(String path) {
        Imgcodecs imgcodecs = new Imgcodecs();
        Mat matrix = imgcodecs.imread(path);

        return matrix;
    }

    // construct javafx image from an opencv matrix
    public static Image matcvToImage(Mat matrix){
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".bmp", matrix, matOfByte);
        byte[] byteArray = matOfByte.toArray();
        InputStream in = new ByteArrayInputStream(byteArray);
        javafx.scene.image.Image image = new javafx.scene.image.Image(in);
        return image;
    }

    // construct weka one dimensional vector from an image
    // TODO: 07/03/2020 implement this 
    public static Matrix imageToVector(Image image){
        return null;
    }

}
