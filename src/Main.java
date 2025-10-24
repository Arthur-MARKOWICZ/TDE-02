import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        Scanner sc = new Scanner(System.in);

        System.out.println("Árvore Morse construída:\n");
        arvore.imprimirArvore();

        System.out.println("\nDigite o código Morse (separe letras com espaço):");
        String entrada = sc.nextLine().toUpperCase();

        System.out.println("\nPalavra decodificada: " + arvore.decodificar(entrada));
        sc.close();
    }
}
