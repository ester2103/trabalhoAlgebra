public class Matrix {
    private int rows;
    private int cols;
    private int[][] elements;

    public Matrix(int rows, int cols, int[] elements) {
        this.rows = rows;
        this.cols = cols;
        this.elements = new int[rows][cols];
        
        for (int i = 0; i < elements.length; i++) {
            this.elements[i / cols][i % cols] = elements[i];
        }
    }

    public int get(int i, int j) {
        return elements[i][j];
    }

    public void set(int i, int j, int value) {
        elements[i][j] = value;
    }

    public void printMatrix() {
        for (int[] row : elements) {
            for (int elem : row) {
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }
}