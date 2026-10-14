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

    public Candidato[] paraVetor() {
        return lista.paraVetor(new Candidato[lista.getTamanho()]);
    }

    // Busca pelo identificador interno; retorna null se não existir.
    public Candidato buscarPorId(int id) {
        Candidato[] todos = paraVetor();
        for (int i = 0; i < todos.length; i++) {
            if (todos[i].getId() == id) {
                return todos[i];
            }
        }
        return null;
    }

    // Candidatos com o nome exato informado (ignora maiúsculas/minúsculas).
    public ListaCandidatos buscarPorNome(String nome) {
        ListaCandidatos encontrados = new ListaCandidatos();
        Candidato[] todos = paraVetor();
        for (int i = 0; i < todos.length; i++) {
            if (todos[i].getNome().equalsIgnoreCase(nome.trim())) {
                encontrados.inserirFim(todos[i]);
            }
        }
        return encontrados;
    }

    // Uma linha " id - nome" por candidato.
    @Override
    public String toString() {
        if (lista.isVazia()) {
            return "(Sem Candidatos)";
        }
        StringBuilder sb = new StringBuilder();
        Candidato[] todos = paraVetor();
        for (int i = 0; i < todos.length; i++) {
            sb.append("  ").append(todos[i]);
            if (i < todos.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
