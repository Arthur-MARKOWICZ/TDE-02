import java.util.Map;

public class Arvore{
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

    public Arvore() {
        MORSE_CODE.forEach(this::inserir);
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

    /** Decodifica uma única letra em Morse */
    private char decodificarLetra(String codigo) {
        Node atual = root;
        for (char simbolo : codigo.toCharArray()) {
            atual = (simbolo == '.') ? atual.left : atual.right;
            if (atual == null) return '?';
        }
        return atual.letter;
    }

    /** Imprime a árvore de forma hierárquica no console */
    public void imprimirArvore() {
        imprimirRec(root, 0);
    }

    private void imprimirRec(Node no, int nivel) {
        if (no == null) return;
        imprimirRec(no.right, nivel + 1);
        System.out.println("  ".repeat(nivel) + no.letter);
        imprimirRec(no.left, nivel + 1);
    }
}

