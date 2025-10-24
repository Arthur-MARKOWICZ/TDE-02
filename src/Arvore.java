import java.util.HashMap;
import java.util.Map;

public class Arvore {
    private final Node root = new Node('*');
    private static final Map<Character, String> MORSE_CODE = Map.ofEntries(
            Map.entry('A', ".-"),   Map.entry('B', "-..."), Map.entry('C', "-.-."),
            Map.entry('D', "-.."),  Map.entry('E', "."),    Map.entry('F', "..-."),
            Map.entry('G', "--."),  Map.entry('H', "...."), Map.entry('I', ".."),
            Map.entry('J', ".---"), Map.entry('K', "-.-"),  Map.entry('L', ".-.."),
            Map.entry('M', "--"),   Map.entry('N', "-."),   Map.entry('O', "---"),
            Map.entry('P', ".--."), Map.entry('Q', "--.-"), Map.entry('R', ".-."),
            Map.entry('S', "..."),  Map.entry('T', "-"),    Map.entry('U', "..-"),
            Map.entry('V', "...-"), Map.entry('W', ".--"),  Map.entry('X', "-..-"),
            Map.entry('Y', "-.--"), Map.entry('Z', "--..")
    );

    private final Map<Character, String> inverso = new HashMap<>();

    public Arvore() {
        MORSE_CODE.forEach(this::inserir);
        inverso.putAll(MORSE_CODE); // cria mapa reverso
    }

    /** Insere uma letra na árvore conforme o código Morse */
    private void inserir(char letra, String codigo) {
        Node atual = root;
        for (char simbolo : codigo.toCharArray()) {
            if (simbolo == '.') {
                if (atual.left == null) atual.left = new Node('*');
                atual = atual.left;
            } else {
                if (atual.right == null) atual.right = new Node('*');
                atual = atual.right;
            }
        }
        atual.letter = letra;
    }

    /** Decodifica uma sequência Morse (letras separadas por espaço) */
    public String decodificar(String entrada) {
        StringBuilder palavra = new StringBuilder();
        for (String cod : entrada.trim().split(" ")) {
            palavra.append(decodificarLetra(cod));
        }
        return palavra.toString();
    }

    /** Codifica texto normal em código Morse */
    public String codificar(String texto) {
        StringBuilder morse = new StringBuilder();
        for (char c : texto.toUpperCase().toCharArray()) {
            if (c == ' ') {
                morse.append("   "); // separação entre palavras
            } else if (inverso.containsKey(c)) {
                morse.append(inverso.get(c)).append(" ");
            }
        }
        return morse.toString().trim();
    }

    /** Decodifica uma única letra em Morse */
    private char decodificarLetra(String codigo) {
        Node atual = root;
        for (char simbolo : codigo.toCharArray()) {
            atual = (simbolo == '.') ? atual.left : atual.right;
            if (atual == null) return '?';
        }
        return atual.letter;
    }

    /** Imprime a árvore em letras */
    public void imprimirArvoreLetras() {
        System.out.println("===== ÁRVORE MORSE (LETRAS) =====");
        imprimirRec(root, 0);
        System.out.println("=================================\n");
    }

    /** Imprime a árvore mostrando o código Morse de cada letra */
    public void imprimirArvoreMorse() {
        System.out.println("===== ÁRVORE MORSE (LETRA + CÓDIGO) =====");
        imprimirRecMorse(root, "", 0);
        System.out.println("==========================================\n");
    }

    private void imprimirRec(Node no, int nivel) {
        if (no == null) return;
        imprimirRec(no.right, nivel + 1);
        System.out.println("  ".repeat(nivel) + no.letter);
        imprimirRec(no.left, nivel + 1);
    }

    private void imprimirRecMorse(Node no, String codigo, int nivel) {
        if (no == null) return;
        imprimirRecMorse(no.right, codigo + "-", nivel + 1);
        if (no.letter != '*')
            System.out.println("  ".repeat(nivel) + no.letter + " (" + MORSE_CODE.get(no.letter) + ")");
        else
            System.out.println("  ".repeat(nivel) + "*");
        imprimirRecMorse(no.left, codigo + ".", nivel + 1);
    }
}
