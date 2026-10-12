public class Candidato {

    private final int id;
    private String nome;
    private String estado;
    private char sexo;                 // 'F' ou 'M'
    private char sexoInteresse;        // 'F', 'M' ou 'I'
    private final ListaIdentificadores interesses = new ListaIdentificadores();
    private final ListaIdentificadores companheiros = new ListaIdentificadores();

    // ---------- criação ----------

    public Candidato(int id) {
        this.id = id;
        this.nome = "";
        this.estado = "";
        this.sexo = 'F';
        this.sexoInteresse = 'I';
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

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
