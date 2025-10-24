import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        Scanner sc = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("1 - Decodificar código Morse -> palavra");
            System.out.println("2 - Codificar palavra -> código Morse");
            System.out.println("3 - Mostrar árvore de letras");
            System.out.println("4 - Mostrar árvore de código Morse");
            System.out.println("5 - Mostrar árvore mista (letra + código)");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = sc.nextLine().trim();

            switch (opcao) {
                case "1":
                    System.out.print("Digite o código Morse (use . e -): ");
                    String morse = sc.nextLine().trim();
                    System.out.println("→ Decodificado: " + arvore.decodificar(morse));
                    break;

                case "2":
                    System.out.print("Digite a palavra (A-Z): ");
                    String palavra = sc.nextLine().trim();
                    if (palavra.matches("[A-Za-z ]+")) {
                        System.out.println("→ Em código Morse: " + arvore.codificar(palavra));
                    } else {
                        System.out.println("[ERRO] A palavra deve conter apenas letras A-Z ou espaços!");
                    }
                    break;

                case "3":
                    arvore.imprimirArvoreLetras();
                    break;

                case "4":
                    arvore.imprimirArvoreMorse();
                    break;

                case "5":
                    arvore.imprimirArvoreMista();
                    break;

                case "6":
                    rodando = false;
                    System.out.println("Encerrando o programa. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Digite um número de 1 a 6.");
            }
        }

        sc.close();
    }
}
