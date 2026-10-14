import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Random;
/*
 * Issue: Gerador Auxiliar de Testes (GeradorCandidatos).
 * Gera um arquivo candidatos.txt com dados aleatórios. Não imprime nada.
 */
public class GeradorCandidatos {
    private static final String[] NOMES_PADRAO = {
            "Ana", "Bruno", "Carla", "Daniel", "Eduarda", "Felipe", "Gabriela", "Hugo",
            "Isabela", "João", "Karina", "Lucas", "Marina", "Nicolas", "Olívia", "Pedro",
            "Rafael", "Sofia", "Tiago", "Vitor", "Yasmin"
    };
    private static final String[] ESTADOS = { "MG", "SP", "RJ", "ES", "BA" };
    private static final char[] SEXOS = { 'F', 'M' };
    private static final char[] SEXOS_INTERESSE = { 'F', 'M', 'I' };

    private static String[] lerNomes(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) {
            return NOMES_PADRAO;
        }
        String[] nomes = new String[16];
        int quantidade = 0;
        try {
            BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));
            try {
                String linha = leitor.readLine();
                while (linha != null) {
                    linha = linha.trim();
                    if (linha.length() > 0) {
                        if (quantidade == nomes.length) {
                            String[] maior = new String[quantidade * 2];
                            for (int indice = 0; indice < quantidade; indice++) {
                                maior[indice] = nomes[indice];
                            }
                            nomes = maior;
                        }
                        nomes[quantidade] = linha;
                        quantidade++;
                    }
                    linha = leitor.readLine();
                }
            } finally {
                leitor.close();
            }
        } catch (IOException erro) {
            return NOMES_PADRAO;
        }
        if (quantidade == 0) {
            return NOMES_PADRAO;
        }
        String[] resultado = new String[quantidade];
        for (int indice = 0; indice < quantidade; indice++) {
            resultado[indice] = nomes[indice];
        }
        return resultado;
    }

    public static void gerar(int quantidade, String arquivoNomes, String arquivoSaida) throws IOException {
        if (quantidade < 1) {
            throw new IOException("o número de candidatos deve ser >= 1.");
        }
        String[] nomes = lerNomes(arquivoNomes);
        Random sorteador = new Random();
        PrintWriter saida = new PrintWriter(new OutputStreamWriter(new FileOutputStream(arquivoSaida)));
        try {
            saida.println(quantidade);
            for (int contador = 0; contador < quantidade; contador++) {
                saida.println("****");
                saida.println(nomes[sorteador.nextInt(nomes.length)] + " " + nomes[sorteador.nextInt(nomes.length)]);
                saida.println(ESTADOS[sorteador.nextInt(ESTADOS.length)]);
                saida.println(SEXOS[sorteador.nextInt(SEXOS.length)]);
                saida.println(SEXOS_INTERESSE[sorteador.nextInt(SEXOS_INTERESSE.length)]);

                int[] areas = { 1, 2, 3, 4, 5, 6, 7, 8 }; // embaralha as áreas 1 a 8
                for (int posicao = areas.length - 1; posicao > 0; posicao--) {
                    int sorteada = sorteador.nextInt(posicao + 1);
                    int auxiliar = areas[posicao];
                    areas[posicao] = areas[sorteada];
                    areas[sorteada] = auxiliar;
                }
                int numeroAreas = 1 + sorteador.nextInt(5); // de 1 a 5 áreas
                saida.println(numeroAreas);
                for (int indice = 0; indice < numeroAreas; indice++) {
                    saida.println(areas[indice]);
                }
            }
            saida.println("****");
        } finally {
            saida.close();
        }
    }
}
