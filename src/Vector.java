package src;

public class Vector {
    private int[] elements;

    public Vector(int[] elements) {
        this.elements = elements;
    }

    public int size() {
        return elements.length;
    }

    public int get(int i) {
        return elements[i];
    }

    public void show() {
        for (int element : elements) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}