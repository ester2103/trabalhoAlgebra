package src;

public class Vector {
    private double[] elements; // Usando double para maior precisão

    public Vector(double[] elements) {
        this.elements = elements;
    }

    // Retorna o tamanho do vetor
    public int size() {
        return elements.length;
    }

    // Retorna o elemento na posição i
    public double getElement(int i) {
        if (i < 0 || i >= elements.length) {
            throw new IllegalArgumentException("Índice fora dos limites do vetor.");
        }
        return elements[i];
    }

    // Define o elemento na posição i
    public void setElement(int i, double value) {
        if (i < 0 || i >= elements.length) {
            throw new IllegalArgumentException("Índice fora dos limites do vetor.");
        }
        elements[i] = value;
    }

    // Imprime o vetor
    public void show() {
        for (double element : elements) {
            System.out.printf("%.6f ", element); // Formatação para 6 casas decimais
        }
        System.out.println();
    }
}