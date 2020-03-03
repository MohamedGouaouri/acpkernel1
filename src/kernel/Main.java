package kernel;

public class Main {

    public static void main(String[] args) {
        double[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[] b = {0, 0, 0};
        data[1] = b;
        System.out.println(data[1][1]);
    }
}
