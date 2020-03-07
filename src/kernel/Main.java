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


public class Main extends Application{

    public static void main(String[] args){
        Application.launch(args);
    }


    public static Mat mean(Matrix matrix1, Matrix matrix2, Matrix matrix3){
        Matrix mean = (matrix1.plusEquals(matrix2)).plusEquals(matrix3);
        for (int i = 0; i < mean.getRowDimension(); i++) {
            for (int j = 0; j < mean.getColumnDimension(); j++) {
                mean.set(i, j, Math.floor(mean.get(i, j) / 3));
            }
        }
        return Util.matrixToMatcv(mean);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat matrix1 = ImageMat.imageToMatcv("orl/PERSON1/1.bmp");
        Mat matrix2 = ImageMat.imageToMatcv("orl/PERSON2/1.bmp");
        Mat matrix3 = ImageMat.imageToMatcv("orl/PERSON3/1.bmp");
        Mat mean = mean(Util.matcvToMatrix(matrix1), Util.matcvToMatrix(matrix2), Util.matcvToMatrix(matrix3));

        Image image1 = ImageMat.matcvToImage(matrix1);
        Image image2 = ImageMat.matcvToImage(matrix2);
        Image image3 = ImageMat.matcvToImage(matrix3);
        Image meanImage = ImageMat.matcvToImage(mean);

        Group group = new Group();
        HBox hBox = new HBox();

        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        ImageView imageView3 = new ImageView(image3);
        ImageView imageView4 = new ImageView(meanImage);
        hBox.getChildren().addAll(imageView1, imageView2, imageView3, imageView4);
        group.getChildren().add(hBox);

        Scene scene = new Scene(group, 200, 200);
        stage.setScene(scene);
        stage.show();
    }
}
