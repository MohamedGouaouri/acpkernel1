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

    public static Mat imageToMatcv(String path) {
        Imgcodecs imgcodecs = new Imgcodecs();
        Mat matrix = imgcodecs.imread(path);

        return matrix;
    }

    public static Image matcvToImage(Mat matrix){
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".bmp", matrix, matOfByte);
        byte[] byteArray = matOfByte.toArray();
        InputStream in = new ByteArrayInputStream(byteArray);
        javafx.scene.image.Image image = new javafx.scene.image.Image(in);
        return image;
    }

    public static Matrix imageToVector(){
        return null;
    }

}
