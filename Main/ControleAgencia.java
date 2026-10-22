import java.io.IOException;

/**
 * Classe de controle: coordena a Main e o modelo (criação dos objetos via arquivo,
 * recálculo dos pares após mudanças e repasse das consultas).
 * A lógica de busca e filtro fica nas classes de origem (ListaCandidatos, Candidato...).
 * Não imprime nada: a Main exibe os resultados via toString().
 */
public class ControleAgencia {

    private ListaCandidatos candidatos = new ListaCandidatos();

    // ---------- carga de dados ----------

    /** Cria os candidatos a partir do arquivo e calcula os potenciais companheiros. */
    public void carregar(String nomeArquivo) throws IOException {
        candidatos = LerCandidatos.ler(nomeArquivo);
        candidatos.calcularCompanheiros();
    }

    public void gerarArquivoTeste(int quantidade, String arquivoNomes, String arquivoSaida) throws IOException {
        GeradorCandidatos.gerar(quantidade, arquivoNomes, arquivoSaida);
    }

    public int getTotalCandidatos() {
        return candidatos.getTamanho();
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
        candidatos.calcularCompanheiros();
        return true;
    }

    // ---------- Tarefa 2: companheiros de um candidato ----------

    /** Interpreta o texto digitado: número = id, qualquer outra coisa = nome. */
    public ListaCandidatos buscarCandidato(String texto) {
        try {
            int id = Integer.parseInt(texto.trim());
            ListaCandidatos encontrados = new ListaCandidatos();
            Candidato candidato = candidatos.buscarPorId(id);
            if (candidato != null) {
                encontrados.inserirFim(candidato);
            }
            return encontrados;
        } catch (NumberFormatException erro) {
            return candidatos.buscarPorNome(texto);
        }
    }

    public ListaCandidatos companheirosDe(Candidato candidato) {
        return candidatos.companheirosDe(candidato);
    }

    // ---------- Tarefas 3 a 6: consultas (repasse à lista) ----------

    /** Ranking de compatibilidade: os k candidatos com mais potenciais companheiros. */
    public ListaCandidatos rankingCompatibilidade(int k) {
        return candidatos.comMaisCompanheiros(k);
    }

    public ListaCandidatos semCompanheiros() {
        return candidatos.semCompanheiros();
    }

    public boolean isAreaValida(int area) {
        return GrauInteresse.isAreaValida(area);
    }

    public String getNomeArea(int area) {
        return GrauInteresse.getNomeArea(area);
    }

    public ListaCandidatos comInteresse(int area) {
        return candidatos.comInteresse(area);
    }

    public ListaCandidatos residentesEm(String sigla) {
        return candidatos.residentesEm(sigla);
    }
}
