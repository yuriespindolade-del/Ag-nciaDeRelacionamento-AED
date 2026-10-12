/**
 * Issue: TAD / Lista de Candidatos do Sistema.
 * Lista de Candidato construída sobre ListaEncadeada.
 */
public class ListaCandidatos {
    private final ListaEncadeada<Candidato> lista = new ListaEncadeada<>();

    public void inserirFim(Candidato candidato) {
        lista.inserirFim(candidato);
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

    public Candidato getCandidato(int posicao) {
        return lista.getElemento(posicao);
    }

    /** Busca pelo identificador interno; retorna null se não existir. */
    public Candidato buscarPorId(int id) {
        ListaEncadeada<Candidato>.Iterador iterador = lista.getIterador();
        while (iterador.hasNext()) {
            Candidato candidato = iterador.proximo();
            if (candidato.getId() == id) {
                return candidato;
            }
        }
        return null;
    }

    public Candidato[] paraVetor() {

        Candidato[] candidatos = new Candidato[lista.getTamanho()];
        ListaEncadeada<Candidato>.Iterador iterador = lista.getIterador();
        int indice = 0;
        while (iterador.hasNext()) {
            candidatos[indice] = iterador.proximo();
            indice++;
        }
        return candidatos;
    }

    @Override
    public String toString() {
        if (lista.isVazia()) {
            return "  (Sem Candidatos)";
        }
        StringBuilder sb = new StringBuilder();
        ListaEncadeada<Candidato>.Iterador iterador = lista.getIterador();
        while (iterador.hasNext()) {
            sb.append("  ").append(iterador.proximo());
            if (iterador.hasNext()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
