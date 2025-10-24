
public class Arvore<TIPO extends Comparable<TIPO>> {
    private No<TIPO> raiz;

    public Arvore() {
        this.raiz = null;
    }

    public No<TIPO> getRaiz() { return raiz; }

    public void imprimirArvore() {
        imprimirArvore(this.raiz, 0);
    }

    private void imprimirArvore(No<TIPO> no, int nivel) {
        if (no == null) return;

        imprimirArvore(no.getDireita(), nivel + 1);

        for (int i = 0; i < nivel; i++) {
            System.out.print("   ");
        }
        System.out.println(no.getValor());

        imprimirArvore(no.getEsquerda(), nivel + 1);
    }


    public void adicionar(TIPO valor) {
        No<TIPO> novoNo = new No<>(valor);
        if (raiz == null) {
            raiz = novoNo;
            return;
        }
        No<TIPO> atual = raiz;
        No<TIPO> pai = null;
        while (true) {
            pai = atual;
            int cmp = valor.compareTo(atual.getValor());
            if (cmp < 0) { // vai para esquerda
                atual = atual.getEsquerda();
                if (atual == null) {
                    pai.setEsquerda(novoNo);
                    return;
                }
            } else { // >= vai para direita
                atual = atual.getDireita();
                if (atual == null) {
                    pai.setDireita(novoNo);
                    return;
                }
            }
        }
    }

    public boolean remover(TIPO valor) {
        No<TIPO> atual = raiz;
        No<TIPO> pai = null;

       
        while (atual != null) {
            int cmp = valor.compareTo(atual.getValor());
            if (cmp == 0) break;
            pai = atual;
            if (cmp < 0) atual = atual.getEsquerda();
            else atual = atual.getDireita();
        }

        if (atual == null) return false;

        
        if (atual.getEsquerda() != null && atual.getDireita() != null) {
            No<TIPO> succ = atual.getDireita();
            No<TIPO> paiSucc = atual;
            while (succ.getEsquerda() != null) {
                paiSucc = succ;
                succ = succ.getEsquerda();
            }
            atual.setValor(succ.getValor());
            pai = paiSucc;
            atual = succ;
        }

      
        No<TIPO> filho = (atual.getEsquerda() != null) ? atual.getEsquerda() : atual.getDireita();

        if (pai == null) {
            raiz = filho;
        } else if (pai.getEsquerda() == atual) {
            pai.setEsquerda(filho);
        } else {
            pai.setDireita(filho);
        }

        return true;
    }
    public String decodificarLetra(String codigo){
        return ;
    }
    public String decodificarMensagem(String codigo){
        return ;
    }
}
