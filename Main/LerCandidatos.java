import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Issue: Parser do arquivo candidatos.txt.
 * Formato: N, depois blocos separados por "****" com
 * nome, sigla, sexo, sexo_interesse, número_áreas e as áreas (uma por linha).
 * Erros são lançados como IOException (quem imprime é a Main).
 */
public class LerCandidatos {
    /* Lê a próxima linha útil, ignorando linhas vazias e os separadores "****". */
    private static String lerProximaLinha(BufferedReader leitor) throws IOException {
        String linha = leitor.readLine();
        while (linha != null) {
            linha = linha.trim();
            if (linha.startsWith("\uFEFF")) {
                linha = linha.substring(1);
            }
            if (linha.length() > 0 && !linha.equals("****")) {
                return linha;
            }
            linha = leitor.readLine();
        }
        throw new IOException("Fim do arquivo.");
    }

    private static int converterParaInteiro(String texto, String campo) throws IOException {
        try {
            return Integer.parseInt(texto.split("\\s+")[0]);
        } catch (NumberFormatException erro) {
            throw new IOException("Valor inválido para " + campo + ": '" + texto + "'.");
        }
    }

    public static ListaCandidatos ler(String nomeArquivo) throws IOException {
        ListaCandidatos candidatos = new ListaCandidatos();
        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(new FileInputStream(nomeArquivo)));
        try {
            int numeroCandidatos = converterParaInteiro(lerProximaLinha(leitor), "Número de candidatos");
            for (int posicao = 1; posicao <= numeroCandidatos; posicao++) {
                String nome = lerProximaLinha(leitor);
                String estado = lerProximaLinha(leitor);
                String sexo = lerProximaLinha(leitor).toUpperCase();
                String sexoInteresse = lerProximaLinha(leitor).toUpperCase();

                if (!sexo.equals("F") && !sexo.equals("M")) {
                    throw new IOException("Sexo inválido no candidato " + posicao + ": '" + sexo + "'.");
                }
                if (!sexoInteresse.equals("F") && !sexoInteresse.equals("M") && !sexoInteresse.equals("I")) {
                    throw new IOException("Sexo de interesse inválido no candidato " + posicao
                            + ": '" + sexoInteresse + "'.");
                }

                int numeroAreas = converterParaInteiro(lerProximaLinha(leitor), "Número de áreas");
                Candidato candidato = new Candidato();
                try {
                    candidato.atualizarCadastro(nome, estado, sexo.charAt(0), sexoInteresse.charAt(0));
                } catch (IllegalArgumentException erro) {
                    throw new IOException(erro.getMessage() + " (candidato " + posicao + ")");
                }
                for (int contador = 0; contador < numeroAreas; contador++) {
                    int area = converterParaInteiro(lerProximaLinha(leitor), "Área");
                    if (!candidato.adicionarInteresse(area)) {
                        throw new IOException("Área inválida no candidato " + posicao + ": " + area + ".");
                    }
                }
                candidatos.inserirFim(candidato);
            }
        } finally {
            leitor.close();
        }
        return candidatos;
    }
}
