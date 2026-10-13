/**
 * Issue: TAD ListaIdentificadores.
 * Lista de identificadores (inteiros) construída sobre ListaEncadeada.
 */
public class ListaIdentificadores {

    private final ListaEncadeada<Integer> lista = new ListaEncadeada<>();

    public void inserirFim(int identificador) {
        lista.inserirFim(identificador);
    }

    public boolean contem(int identificador) {
        ListaEncadeada<Integer>.Iterador iterador = lista.getIterador();

        while (iterador.hasNext()) {
            if (iterador.proximo() == identificador) {
                return true;
            }
        }
        return false;
    }

    public int getTamanho() {
        return lista.getTamanho();
    }

    public boolean isVazia() {
        return lista.isVazia();
    }

    public void limpar() {
        lista.limpar();
    }

    /** Identificador da posição informada, ou -1 se a posição for inválida. */
    public int getIdentificador(int posicao) {
        Integer identificador = lista.getElemento(posicao);

        if (identificador == null) {
            return -1;
        }
        return identificador;
    }

    public int[] paraVetor() {
        int[] identificadores = new int[lista.getTamanho()];
        ListaEncadeada<Integer>.Iterador iterador = lista.getIterador();
        int indice = 0;
        
        while (iterador.hasNext()) {
            identificadores[indice] = iterador.proximo();
            indice++;
        }
        return identificadores;
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}
