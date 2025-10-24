import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        Scanner sc = new Scanner(System.in);

        arvore.imprimirArvoreLetras();
        arvore.imprimirArvoreMorse();

        System.out.println("Digite texto (A-Z) ou código Morse (com . e -):");
        String entrada = sc.nextLine().toUpperCase().trim();

        if (entrada.matches("[\\.\\-\\s]+")) {

            System.out.println("→ Decodificado: " + arvore.decodificar(entrada));
        } else {

            System.out.println("→ Em código Morse: " + arvore.codificar(entrada));
        }

        sc.close();
    }
}
