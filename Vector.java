public class Vector {

    int[] elementos;

    public Vector(int tamanho) {
        elementos = new int[tamanho];
    }

    public void set(int indice, int valor) {
        elementos[indice] = valor;
    }

    public int get(int indice) {
        return elementos[indice];
    }

    public void mostrar() {
        for (int i = 0; i < elementos.length; i++) {
            System.out.print(elementos[i] + " ");
        }
        System.out.println();
    }




}
public class Main {

    public static void main(String[] args) {
        Vector meuVetor = new Vector(5);

        meuVetor.set(0, 10);
        meuVetor.set(1, 20);
        meuVetor.set(2, 30);
        meuVetor.set(3, 40);
        meuVetor.set(4, 50);

        System.out.println("Elementos do vetor:");
        meuVetor.mostrar();

        int valorNaPosicao2 = meuVetor.get(2);
        System.out.println("Valor na posição 2: " + valorNaPosicao2);
    }
}
