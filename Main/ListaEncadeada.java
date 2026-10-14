/*
 * Issue: Lista Encadeada Genérica / Base.
 * Lista simplesmente encadeada genérica com célula sentinela (cabeça) e ponteiro tail.
 */
public class ListaEncadeada<T> {
    private final Celula sentinela;
    private Celula tail;
    private int tamanho;

    private class Celula {
        private T dado;
        private Celula proximo;

        private Celula(T dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    public ListaEncadeada() {
        sentinela = new Celula(null);
        tail = sentinela;
        tamanho = 0;
    }

    public void inserirFim(T dado) {
        Celula novaCelula = new Celula(dado);
        tail.proximo = novaCelula;
        tail = novaCelula;
        tamanho++;
    }

    public T getElemento(int posicao) {
        if (posicao < 0 || posicao >= tamanho) {
            return null;
        }

        Celula celulaAtual = sentinela.proximo;

        for (int indice = 0; indice < posicao; indice++) {
            celulaAtual = celulaAtual.proximo;
        }
        return celulaAtual.dado;
    }

    public int getTamanho() {
        return tamanho;
    }

    public boolean isVazia() {
        if (tamanho == 0)
            return true;
        else
            return false;
    }

    public void limpar() {
        sentinela.proximo = null;
        tail = sentinela;
        tamanho = 0;
    }

    // Verdadeiro se algum elemento da lista é igual (equals) ao dado informado.
    public boolean contem(T dado) {
        Celula celulaAtual = sentinela.proximo;
        while (celulaAtual != null) {
            if (celulaAtual.dado.equals(dado)) {
                return true;
            }
            celulaAtual = celulaAtual.proximo;
        }
        return false;
    }

    /*
     * Copia os elementos para o vetor recebido (que deve ter getTamanho() posições)
     * e o devolve, permitindo percorrer a lista com um for normal em O(n).
     */
    public T[] paraVetor(T[] vetor) {
        Celula celulaAtual = sentinela.proximo;
        int indice = 0;
        while (celulaAtual != null && indice < vetor.length) {
            vetor[indice] = celulaAtual.dado;
            celulaAtual = celulaAtual.proximo;
            indice++;
        }
        return vetor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Celula celulaAtual = sentinela.proximo;

        while (celulaAtual != null) {
            sb.append(celulaAtual.dado);
            if (celulaAtual.proximo != null) {
                sb.append(", ");
            }
            celulaAtual = celulaAtual.proximo;
        }
        sb.append("]");

        return sb.toString();
    }
}
