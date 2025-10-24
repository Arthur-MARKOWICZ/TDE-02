public class No<TIPO> {
    private TIPO valor;
    private No<TIPO> esquerda;
    private No<TIPO> direita;

    public No (TIPO novoValor){
        this.valor = novoValor;
        this.esquerda = null;
        this.direita = null;
    }

    public TIPO getValor() {
        return valor;
    }

    public void setValor(TIPO valor) {
        this.valor = valor;
    }

    public No<TIPO> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No<TIPO> esquerda) {
        this.esquerda = esquerda;
    }

    public No<TIPO> getDireita() {
        return direita;
    }

    public void setDireita(No<TIPO> direita) {
        this.direita = direita;
    }
}
