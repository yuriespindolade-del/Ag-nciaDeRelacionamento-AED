public class Candidato {

    private final int id;
    private String nome;
    private String estado;
    private char sexo;                 // 'F' ou 'M'
    private char sexoInteresse;        // 'F', 'M' ou 'I'
    private final ListaIdentificadores interesses = new ListaIdentificadores();
    private final ListaIdentificadores companheiros = new ListaIdentificadores();

    // ---------- criação e atualização ----------

    public Candidato(int id) {
        this.id = id;
        this.nome = "";
        this.estado = "";
        this.sexo = 'F';
        this.sexoInteresse = 'I';
    }

    /** Atualiza os campos do cadastro (a lista de interesses é reiniciada). */
    public void atualizarCadastro(String nome, String estado, char sexo, char sexoInteresse) {
        setNome(nome);
        setEstado(estado);
        setSexo(sexo);
        setSexoInteresse(sexoInteresse);
        interesses.limpar();
    }

    /** Adiciona uma área (1 a 8) sem duplicar. Retorna false se a área for inválida. */
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

    // ---------- getters e setters ----------

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado.trim().toUpperCase();
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = Character.toUpperCase(sexo);
    }

    public char getSexoInteresse() {
        return sexoInteresse;
    }

    public void setSexoInteresse(char sexoInteresse) {
        this.sexoInteresse = Character.toUpperCase(sexoInteresse);
    }

    public ListaIdentificadores getInteresses() {
        return interesses;
    }

    // ---------- operações exigidas pelo enunciado ----------

    public static boolean verificaPotencialCompanheiro(Candidato candidato, Candidato outro) {
        return ValidadorPares.verifica(candidato, outro);
    }

    public static void inserePotencialCompanheiro(Candidato candidato, Candidato outro) {
        candidato.companheiros.inserirFim(outro.getId());   // usa o getter, não acessa o campo
    }

    public static int numeroCompanheiros(Candidato candidato) {
        return candidato.companheiros.getTamanho();
    }

    public static ListaIdentificadores listaCompanheiros(Candidato candidato) {
        return candidato.companheiros;
    }

    public static boolean temInteresse(Candidato candidato, int idArea) {
        return candidato.interesses.contem(idArea);
    }

    public static boolean reside(Candidato candidato, String sigla) {
        if (candidato.estado.equalsIgnoreCase(sigla.trim()))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
