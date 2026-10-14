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
}
