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
    
    // Recalcula a lista de potenciais companheiros de todos os candidatos.
    public void calcularCompanheiros() {
        Candidato[] todos = paraVetor();
        for (int i = 0; i < todos.length; i++) {
            todos[i].limparCompanheiros();
            for (int j = 0; j < todos.length; j++) {
                if (todos[i].verificaPotencialCompanheiro(todos[j])) {
                    todos[i].inserePotencialCompanheiro(todos[j]);
                }
            }
        }
    }

    // Potenciais companheiros do candidato, convertidos de identificadores em candidatos.
    public ListaCandidatos companheirosDe(Candidato candidato) {
        ListaCandidatos companheiros = new ListaCandidatos();
        int[] ids = candidato.listaCompanheiros().paraVetor();
        for (int i = 0; i < ids.length; i++) {
            companheiros.inserirFim(buscarPorId(ids[i]));
        }
        return companheiros;
    }

    // Os K candidatos com mais potenciais companheiros (empate: menor id primeiro).
    public ListaCandidatos comMaisCompanheiros(int k) {
        Candidato[] ordenados = paraVetor();
        if (k > ordenados.length) {
            k = ordenados.length;
        }
        for (int i = 0; i < k; i++) {
            int indiceMelhor = i;
            for (int j = i + 1; j < ordenados.length; j++) {
                int companheirosJ = ordenados[j].numeroCompanheiros();
                int companheirosMelhor = ordenados[indiceMelhor].numeroCompanheiros();
                boolean temMais = companheirosJ > companheirosMelhor;
                boolean empateComIdMenor = companheirosJ == companheirosMelhor
                        && ordenados[j].getId() < ordenados[indiceMelhor].getId();
                if (temMais || empateComIdMenor) {
                    indiceMelhor = j;
                }
            }
            Candidato auxiliar = ordenados[i];
            ordenados[i] = ordenados[indiceMelhor];
            ordenados[indiceMelhor] = auxiliar;
        }
        ListaCandidatos resultado = new ListaCandidatos();
        for (int i = 0; i < k; i++) {
            resultado.inserirFim(ordenados[i]);
        }
        return resultado;
    }

    public ListaCandidatos semCompanheiros() {
        ListaCandidatos resultado = new ListaCandidatos();
        Candidato[] todos = paraVetor();
        for (int i = 0; i < todos.length; i++) {
            if (todos[i].numeroCompanheiros() == 0) {
                resultado.inserirFim(todos[i]);
            }
        }
        return resultado;
    }

    public ListaCandidatos comInteresse(int area) {
        ListaCandidatos resultado = new ListaCandidatos();
        Candidato[] todos = paraVetor();
        for (int i = 0; i < todos.length; i++) {
            if (todos[i].temInteresse(area)) {
                resultado.inserirFim(todos[i]);
            }
        }
        return resultado;
    }

    public ListaCandidatos residentesEm(String sigla) {
        ListaCandidatos resultado = new ListaCandidatos();
        Candidato[] todos = paraVetor();
        for (int i = 0; i < todos.length; i++) {
            if (todos[i].reside(sigla)) {
                resultado.inserirFim(todos[i]);
            }
        }
        return resultado;
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
