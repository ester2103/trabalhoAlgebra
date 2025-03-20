package src;

public class Vector {
    private int[] elements;

    public Vector(int[] elements) {
        this.elements = elements;
    }

    // Retorna o tamanho do vetor
    public int size() {
        return elements.length;
    }

    // Retorna o elemento na posição i
    public int getElement(int i) {
        if (i < 0 || i >= elements.length) {
            throw new IllegalArgumentException("Índice fora dos limites do vetor.");
        }
        return elements[i];
    }

    // Define o elemento na posição i
    public void setElement(int i, int value) {
        if (i < 0 || i >= elements.length) {
            throw new IllegalArgumentException("Índice fora dos limites do vetor.");
        }
        elements[i] = value;
    }

    // Imprime o vetor
    public void show() {
        for (int element : elements) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
