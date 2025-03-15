public class Main {
    public static void main(String[] args) {
int[] elements = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Matrix matrix = new Matrix(3, 3, elements);

        matrix.printMatrix();
        System.out.println("Elemento na posição (1, 1): " + matrix.get(1, 1));
        
        matrix.set(1, 1, 99);
        matrix.printMatrix();
    }
    }
}