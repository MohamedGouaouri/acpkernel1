package kernel;

import weka.core.matrix.Matrix;

//la classe java
public class Image extends javafx.scene.image.Image {

    public Image(String url) {
        super(url);
    }


    public Matrix imageToVector() {
        double[]  data = {1, 2, 3, 4 ,5, 6};

        return new Matrix(data, data.length);
    }

}
