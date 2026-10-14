public class ControleAgencia {
  private ListaCandidatos candidatos = new ListaCandidatos();

    // ---------- carga de dados ----------

    /** Lê o arquivo e calcula os potenciais companheiros de todos os candidatos. */
    public void carregar(String nomeArquivo) throws IOException {
        candidatos = LerCandidatos.ler(nomeArquivo);
        recalcularCompanheiros();
    }

    public void gerarArquivoTeste(int quantidade, String arquivoNomes, String arquivoSaida) throws IOException {
        GeradorCandidatos.gerar(quantidade, arquivoNomes, arquivoSaida);
    }

    public int getTotalCandidatos() {
        return candidatos.getTamanho();
    }

    private void recalcularCompanheiros() {
        Candidato[] todos = candidatos.paraVetor();
        for (int i = 0; i < todos.length; i++) {
            todos[i].limparCompanheiros();
            for (int j = 0; j < todos.length; j++) {
                if (todos[i].verificaPotencialCompanheiro(todos[j])) {
                    todos[i].inserePotencialCompanheiro(todos[j]);
                }
            }
        }
    }  
  // ---------- Tarefa 1: grau de interesse desejado ----------

    public int getGrauDesejado() {
        return GrauInteresse.getGrauDesejado();
    }

    public int getGrauMaximo() {
        return GrauInteresse.getGrauMaximo();
    }

    /** Retorna false (sem alterar nada) se o grau for inválido. */
    public boolean atualizarGrauDesejado(int grau) {
        if (!GrauInteresse.isGrauValido(grau)) {
            return false;
        }
        GrauInteresse.setGrauDesejado(grau);
        recalcularCompanheiros();
        return true;
    }
  // ---------- Tarefa 2: companheiros de um candidato ----------

    /** Busca por id (se o texto for número) ou por nome exato (ignora maiúsculas). */
    public ListaCandidatos buscarCandidato(String texto) {
        ListaCandidatos encontrados = new ListaCandidatos();
        texto = texto.trim();
        try {
            int id = Integer.parseInt(texto);
            Candidato candidato = candidatos.buscarPorId(id);
            if (candidato != null) {
                encontrados.inserirFim(candidato);
            }
            return encontrados;
        } catch (NumberFormatException erro) {
            // não é número: busca pelo nome
        }
        Candidato[] todos = candidatos.paraVetor();
        for (int i = 0; i < todos.length; i++) {
            if (todos[i].getNome().equalsIgnoreCase(texto)) {
                encontrados.inserirFim(todos[i]);
            }
        }
        return encontrados;
    }

    public ListaCandidatos companheirosDe(Candidato candidato) {
        ListaCandidatos companheiros = new ListaCandidatos();
        int[] ids = candidato.listaCompanheiros().paraVetor();
        for (int i = 0; i < ids.length; i++) {
            companheiros.inserirFim(candidatos.buscarPorId(ids[i]));
        }
        return companheiros;
    }
      // ---------- Tarefa 3: K candidatos com mais companheiros ----------

    public ListaCandidatos topK(int k) {
        Candidato[] ordenados = candidatos.paraVetor();
        if (k > ordenados.length) {
            k = ordenados.length;
        }
        for (int i = 0; i < k; i++) {                    // seleção parcial dos K maiores
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

}
