/**
 * Issue: TAD ListaIdentificadores.
 * Lista de identificadores (inteiros) construída sobre ListaEncadeada.
 */
public class ListaIdentificadores {

    private final ListaEncadeada<Integer> lista = new ListaEncadeada<Integer>();

    public void inserirFim(int identificador) {
        lista.inserirFim(identificador);
    }

    public boolean contem(int identificador) {
        return lista.contem(identificador);
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
        return identificador.intValue();
    }

    public int[] paraVetor() {
        Integer[] objetos = lista.paraVetor(new Integer[lista.getTamanho()]);
        int[] identificadores = new int[objetos.length];
        for (int indice = 0; indice < objetos.length; indice++) {
            identificadores[indice] = objetos[indice].intValue();
        }
        return identificadores;
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}
