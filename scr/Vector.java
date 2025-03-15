package scr;
public class Vector {
    private int[] elements; // Elementos do vetor

    // Construtor
    public Vector(int[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("O array de elementos não pode ser nulo.");
        }
        this.elements = elements;
    }

    // Método para obter o tamanho do vetor
    public int size() {
        return elements.length;
    }

    // Método para obter o valor em uma posição específica
    public int get(int index) {
        if (index < 0 || index >= elements.length) {
            throw new IllegalArgumentException("Índice fora dos limites do vetor.");
        }
        return elements[index];
    }

    // Método para definir o valor em uma posição específica
    public void set(int index, int value) {
        if (index < 0 || index >= elements.length) {
            throw new IllegalArgumentException("Índice fora dos limites do vetor.");
        }
        elements[index] = value;
    }

    // Método para imprimir o vetor
    public void show() {
        for (int i = 0; i < elements.length; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
}