/*
 * Issue: TAD Candidato.
 */
public class Candidato {
    private static int proximoId = 1; // Garante um Id único.

    private final int id;
    private String nome;
    private String estado;
    private char sexo;                 // 'F' ou 'M'
    private char sexoInteresse;        // 'F', 'M' ou 'I'
    private final ListaIdentificadores interesses = new ListaIdentificadores();
    private final ListaIdentificadores companheiros = new ListaIdentificadores();

    public Candidato() {
        this.id = proximoId;
        proximoId++;
    }

    // Atualiza os campos do cadastro (a lista de interesses é reiniciada).
    public void atualizarCadastro(String nome, String estado, char sexo, char sexoInteresse) {
        setNome(nome);
        setEstado(estado);
        setSexo(sexo);
        setSexoInteresse(sexoInteresse);
        interesses.limpar();
    }

    // Adiciona uma área (1 a 8) sem duplicar. Retorna false se a área for inválida.
    public boolean adicionarInteresse(int area) {
        if (!GrauInteresse.isAreaValida(area)) {
            return false;
        }
        if (!interesses.contem(area)) {
            interesses.inserirFim(area);
        }
        return true;
    }

    public void limparCompanheiros() {
        companheiros.limpar();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        this.nome = nome.trim();
    }

    public String getEstado() {
        return estado;
    }

    // A sigla deve ter exatamente duas letras (ex.: MG).
    public void setEstado(String estado) {
        if (estado == null) {
            throw new IllegalArgumentException("Estado inválido.");
        }
        String sigla = estado.trim().toUpperCase();
        if (sigla.length() != 2 || !Character.isLetter(sigla.charAt(0)) || !Character.isLetter(sigla.charAt(1))) {
            throw new IllegalArgumentException("Estado inválido: '" + estado + "'.");
        }
        this.estado = sigla;
    }

    public char getSexo() {
        return sexo;
    }

    // Aceita 'F' ou 'M'.
    public void setSexo(char sexo) {
        char sexoMaiusculo = Character.toUpperCase(sexo);
        if (sexoMaiusculo != 'F' && sexoMaiusculo != 'M') {
            throw new IllegalArgumentException("Sexo inválido: '" + sexo + "'.");
        }
        this.sexo = sexoMaiusculo;
    }

    public char getSexoInteresse() {
        return sexoInteresse;
    }

    // Aceita 'F', 'M' ou 'I' (indiferente).
    public void setSexoInteresse(char sexoInteresse) {
        char interesseMaiusculo = Character.toUpperCase(sexoInteresse);
        if (interesseMaiusculo != 'F' && interesseMaiusculo != 'M' && interesseMaiusculo != 'I') {
            throw new IllegalArgumentException("Sexo de interesse inválido: '" + sexoInteresse + "'.");
        }
        this.sexoInteresse = interesseMaiusculo;
    }

    public ListaIdentificadores getInteresses() {
        return interesses;
    }

    /*
     * Verdadeiro se este candidato e 'outro' podem ser potenciais companheiros:
     * mesmo estado, opção sexual atendida pelos dois e grau de interesse em comum
     * maior ou igual ao grau desejado.
     */
    public boolean verificaPotencialCompanheiro(Candidato outro) {
        if (id == outro.getId()) {
            return false;
        }
        if (!estado.equals(outro.getEstado())) {
            return false;
        }
        if (!atendeOpcaoSexual(outro) || !outro.atendeOpcaoSexual(this)) {
            return false;
        }
        if (calcularGrauInteresseComum(outro) >= GrauInteresse.getGrauDesejado())
            return true;
        else
            return false;
    }

    // Insere o identificador de 'outro' na lista de companheiros deste candidato.
    public void inserePotencialCompanheiro(Candidato outro) {
        companheiros.inserirFim(outro.getId());
    }

    public int numeroCompanheiros() {
        return companheiros.getTamanho();
    }

    public ListaIdentificadores listaCompanheiros() {
        return companheiros;
    }

    public boolean temInteresse(int idArea) {
        return interesses.contem(idArea);
    }

    public boolean reside(String sigla) {
        if (estado.equalsIgnoreCase(sigla.trim()))
            return true;
        else
            return false;
    }

    // Soma dos pesos das áreas de interesse em comum entre este candidato e 'outro'
    public int calcularGrauInteresseComum(Candidato outro) {
        int grau = 0;
        int[] areas = interesses.paraVetor();
        for (int indice = 0; indice < areas.length; indice++) {
            if (outro.temInteresse(areas[indice])) {
                grau = grau + GrauInteresse.getPeso(areas[indice]);
            }
        }
        return grau;
    }

    private boolean atendeOpcaoSexual(Candidato outro) {
        if (sexoInteresse == 'I') {
            return true;
        }
        if (sexoInteresse == outro.getSexo())
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
