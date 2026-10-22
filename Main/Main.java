import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ControleAgencia controle = new ControleAgencia();
    private static final String ARQUIVO_DADOS = "candidatos.txt";
    private static final String ARQUIVO_NOMES = "nomes.txt";
    private static final int QUANTIDADE_PADRAO = 30;

    // Lê uma linha; se a entrada terminar (Ctrl+D / fim do arquivo), encerra o programa sem erro.
    private static String lerLinha() {
        if (!scanner.hasNextLine()) {
            System.out.println();
            System.out.println("Entrada encerrada. Saindo do sistema...");
            System.exit(0);
        }
        return scanner.nextLine();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(lerLinha().trim());
            } catch (NumberFormatException erro) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return lerLinha().trim();
    }

    private static void carregarDados() {
        try {
            controle.carregar(ARQUIVO_DADOS);
            System.out.println(controle.getTotalCandidatos() + " candidatos carregados de '" + ARQUIVO_DADOS
                    + "'. Grau inicial: " + controle.getGrauDesejado());
        } catch (IOException erro) {
            System.out.println("Erro ao ler '" + ARQUIVO_DADOS + "': " + erro.getMessage());
        }
    }

    private static void atualizarGrauInteresse() {
        System.out.println("Grau atual: " + controle.getGrauDesejado()
                + " (válido de 1 a " + controle.getGrauMaximo() + ")");
        int novoGrau = lerInteiro("Novo grau de interesse desejado: ");
        if (controle.atualizarGrauDesejado(novoGrau)) {
            System.out.println("Grau atualizado para " + novoGrau + ". Potenciais companheiros recalculados.");
        } else {
            System.out.println("Grau fora do intervalo. Nada foi alterado.");
        }
    }

    private static void mostrarCompanheiros() {
        ListaCandidatos encontrados = controle.buscarCandidato(lerTexto("Digite o id ou o nome do candidato: "));
        if (encontrados.getTamanho() == 0) {
            System.out.println("Nenhum candidato encontrado.");
        } else if (encontrados.getTamanho() > 1) {
            System.out.println("Há " + encontrados.getTamanho() + " candidatos com esse nome. Use o id:");
            System.out.println(encontrados);
        } else {
            Candidato candidato = encontrados.getCandidato(0);
            ListaCandidatos companheiros = controle.companheirosDe(candidato);
            System.out.println("Candidato: " + candidato + " (" + candidato.getEstado() + ")");
            System.out.println("Grau desejado: " + controle.getGrauDesejado());
            System.out.println("Número de potenciais companheiros: " + companheiros.getTamanho());
            System.out.println(companheiros);
        }
    }

    private static void mostrarRankingCompatibilidade() {
        int k = lerInteiro("Quantos candidatos mostrar no ranking (K): ");
        if (k < 1) {
            System.out.println("K deve ser maior ou igual a 1.");
            return;
        }
        ListaCandidatos maiores = controle.rankingCompatibilidade(k);
        System.out.println("Ranking de compatibilidade - os " + maiores.getTamanho() + " candidatos mais compatíveis:");
        Candidato[] candidatos = maiores.paraVetor();
        for (int i = 0; i < candidatos.length; i++) {
            System.out.println("  " + candidatos[i] + " (" + candidatos[i].numeroCompanheiros() + " potenciais companheiros)");
        }
    }

    private static void mostrarSemCompanheiros() {
        ListaCandidatos semCompanheiros = controle.semCompanheiros();
        System.out.println("Candidatos sem nenhum potencial companheiro:");
        System.out.println(semCompanheiros);
        System.out.println("Total: " + semCompanheiros.getTamanho());
    }

    private static void mostrarPorArea() {
        System.out.println("Áreas: 1:ESPORTES 2:ARTES 3:MÚSICA 4:CINEMA 5:TECNOLOGIA 6:ANIMAIS 7:GASTRONOMIA 8:CIÊNCIAS");
        int area = lerInteiro("Código da área: ");
        if (!controle.isAreaValida(area)) {
            System.out.println("Área inválida.");
            return;
        }
        ListaCandidatos interessados = controle.comInteresse(area);
        System.out.println("Candidatos com interesse em " + controle.getNomeArea(area) + ":");
        System.out.println(interessados);
        System.out.println("Total: " + interessados.getTamanho());
    }

    private static void mostrarPorEstado() {
        String sigla = lerTexto("Sigla do estado (ex.: MG): ").toUpperCase();
        ListaCandidatos residentes = controle.residentesEm(sigla);
        System.out.println("Candidatos que residem em " + sigla + ":");
        System.out.println(residentes);
        System.out.println("Total: " + residentes.getTamanho());
    }

    // Gera um candidatos.txt novo (dados e nomes aleatórios) a cada execução. //
    private static void gerarDados(int quantidade) {
        try {
            controle.gerarArquivoTeste(quantidade, ARQUIVO_NOMES, ARQUIVO_DADOS);
            System.out.println("Arquivo '" + ARQUIVO_DADOS + "' gerado com " + quantidade + " candidatos.");
        } catch (IOException erro) {
            System.out.println("Erro ao gerar arquivo: " + erro.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== AGÊNCIA DE RELACIONAMENTO =====");
        System.out.println("1 - Inserir/atualizar grau de interesse desejado");
        System.out.println("2 - Número e lista de potenciais companheiros de um candidato");
        System.out.println("3 - Ranking de compatibilidade (candidatos com mais potenciais companheiros)");
        System.out.println("4 - Candidatos sem nenhum companheiro");
        System.out.println("5 - Candidatos com interesse em uma área");
        System.out.println("6 - Candidatos que residem em um estado");
        System.out.println("0 - Sair");
    }

    public static void main(String[] args) {
        // Gera um candidatos.txt novo (sobrescrevendo o anterior) a cada execução.
        gerarDados(QUANTIDADE_PADRAO);
        carregarDados();

        int opcao;
        do {
            mostrarMenu();
            opcao = lerInteiro("Opção: ");
            System.out.println();
            switch (opcao) {
                case 1:
                    atualizarGrauInteresse();
                    break;
                case 2:
                    mostrarCompanheiros();
                    break;
                case 3:
                    mostrarRankingCompatibilidade();
                    break;
                case 4:
                    mostrarSemCompanheiros();
                    break;
                case 5:
                    mostrarPorArea();
                    break;
                case 6:
                    mostrarPorEstado();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
