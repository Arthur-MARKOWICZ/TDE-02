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
        inverso.putAll(MORSE_CODE);
    }

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

    public String decodificar(String entrada) {
        StringBuilder palavra = new StringBuilder();
        for (String cod : entrada.trim().split(" ")) {
            palavra.append(decodificarLetra(cod));
        }
        return palavra.toString();
    }

    public String codificar(String texto) {
        StringBuilder morse = new StringBuilder();
        for (char c : texto.toUpperCase().toCharArray()) {
            if (c == ' ') {
                morse.append("   ");
            } else if (inverso.containsKey(c)) {
                morse.append(inverso.get(c)).append(" ");
            }
        }
        return morse.toString().trim();
    }

    private char decodificarLetra(String codigo) {
        Node atual = root;
        for (int i = 0; i < codigo.length(); i++) {
            char simbolo = codigo.charAt(i);
            if (simbolo != '.' && simbolo != '-') {
                System.out.println("[ERRO] Símbolo inválido '" + simbolo + "' no código: " + codigo);
                return '?';
            }
            atual = (simbolo == '.') ? atual.left : atual.right;
            if (atual == null) {
                System.out.println("[ERRO] Caminho inválido no código Morse: " + codigo.substring(0, i+1));
                return '?';
            }
        }
        return atual.letter;
    }

    public void imprimirArvoreLetras() {
        System.out.println("===== ÁRVORE MORSE (SOMENTE LETRAS) =====");
        imprimirLetras(root, "", true);
        System.out.println("=========================================\n");
    }

    public void imprimirArvoreMista() {
        System.out.println("===== ÁRVORE MORSE (LETRA + CÓDIGO MORSE) =====");
        imprimirMista(root, "", true, "");
        System.out.println("==========================================\n");
    }

    public void imprimirArvoreMorse() {
        System.out.println("===== ÁRVORE MORSE (SOMENTE CÓDIGO MORSE) =====");
        imprimirMorse(root, "", true, "");
        System.out.println("============================================\n");
    }

    private void imprimirLetras(Node no, String prefixo, boolean ultimo) {
        if (no == null) return;
        System.out.println(prefixo + (ultimo ? "└─" : "├─") + (no.letter == '*' ? "*" : no.letter));

        String novoPrefixo = prefixo + (ultimo ? "  " : "│ ");
        if (no.left != null || no.right != null) {
            if (no.right != null && no.left != null) {
                imprimirLetras(no.right, novoPrefixo, false);
                imprimirLetras(no.left, novoPrefixo, true);
            } else if (no.right != null)
                imprimirLetras(no.right, novoPrefixo, true);
            else
                imprimirLetras(no.left, novoPrefixo, true);
        }
    }

    private void imprimirMista(Node no, String prefixo, boolean ultimo, String codigo) {
        if (no == null) return;
        String conteudo = (no.letter == '*') ? "*" : no.letter + " (" + codigo + ")";
        System.out.println(prefixo + (ultimo ? "└─" : "├─") + conteudo);

        String novoPrefixo = prefixo + (ultimo ? "  " : "│ ");
        if (no.left != null || no.right != null) {
            if (no.right != null && no.left != null) {
                imprimirMista(no.right, novoPrefixo, false, codigo + "-");
                imprimirMista(no.left, novoPrefixo, true, codigo + ".");
            } else if (no.right != null)
                imprimirMista(no.right, novoPrefixo, true, codigo + "-");
            else
                imprimirMista(no.left, novoPrefixo, true, codigo + ".");
        }
    }

    private void imprimirMorse(Node no, String prefixo, boolean ultimo, String codigo) {
        if (no == null) return;
        String conteudo = codigo.isEmpty() ? "*" : codigo;
        System.out.println(prefixo + (ultimo ? "└─" : "├─") + conteudo);

        String novoPrefixo = prefixo + (ultimo ? "  " : "│ ");
        if (no.left != null || no.right != null) {
            if (no.right != null && no.left != null) {
                imprimirMorse(no.right, novoPrefixo, false, codigo + "-");
                imprimirMorse(no.left, novoPrefixo, true, codigo + ".");
            } else if (no.right != null)
                imprimirMorse(no.right, novoPrefixo, true, codigo + "-");
            else
                imprimirMorse(no.left, novoPrefixo, true, codigo + ".");
        }
    }
}
