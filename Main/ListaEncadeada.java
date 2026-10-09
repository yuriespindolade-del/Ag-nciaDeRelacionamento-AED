/**
 * Issue: Lista Encadeada Genérica / Base.
 * Lista simplesmente encadeada genérica com célula sentinela (cabeça) e
 * ponteiro tail.
 */
public class ListaEncadeada<T> {
    private class Celula {
        private T dado;
        private Celula proximo;

        private Celula(T dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    public class Iterador {
        private Celula celulaAtual;

        private Iterador(Celula celulaInicial) {
            this.celulaAtual = celulaInicial;
        }

        public boolean hasNext() {
            if (celulaAtual != null)
                return true;
            else
                return false;
        }

        public T proximo() {
            T dado = celulaAtual.dado;
            celulaAtual = celulaAtual.proximo;
            return dado;
        }
    }

    private final Celula sentinela;
    private Celula tail;
    private int tamanho;

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

    public Iterador getIterador() {
        return new Iterador(sentinela.proximo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterador iterador = getIterador();

        while (iterador.hasNext()) {
            sb.append(iterador.proximo());
            if (iterador.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }
}
