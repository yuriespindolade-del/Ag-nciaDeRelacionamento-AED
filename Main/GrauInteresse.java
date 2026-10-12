/**
 * Issue: Cálculo do Grau de Interesse em Comum.
 * Áreas de interesse, pesos e grau de interesse desejado.
 */
public class GrauInteresse {
    public static final int NUMERO_AREAS = 8;
    private static int grauDesejado = 1;

    private static final String[] NOMES_AREAS = {
            "", "ESPORTES", "ARTES", "MÚSICA", "CINEMA",
            "TECNOLOGIA", "ANIMAIS", "GASTRONOMIA", "CIÊNCIAS"
    };

    // Peso 1: ESPORTES, MÚSICA, CINEMA | Peso 2: ARTES, ANIMAIS, GASTRONOMIA | Peso 3: TECNOLOGIA, CIÊNCIAS
    private static final int[] PESOS = { 0, 1, 2, 1, 1, 3, 2, 2, 3 };

    public static boolean isAreaValida(int area) {
        if (area >= 1 && area <= NUMERO_AREAS)
            return true;
        else
            return false;
    }

    public static int getPeso(int area) {
        if (!isAreaValida(area)) {
            return 0;
        }
        return PESOS[area];
    }

    public static String getNomeArea(int area) {
        if (!isAreaValida(area)) {
            return "";
        }
        return NOMES_AREAS[area];
    }

    public static int getGrauMaximo() {
        int soma = 0;
        for (int area = 1; area <= NUMERO_AREAS; area++) {
            soma = soma + PESOS[area];
        }
        return soma;
    }

    public static int getGrauDesejado() {
        return grauDesejado;
    }

    public static boolean isGrauValido(int grau) {
        if (grau >= 1 && grau <= getGrauMaximo())
            return true;
        else
            return false;
    }

    public static void setGrauDesejado(int grau) {
        if (isGrauValido(grau)) {
            grauDesejado = grau;
        }
    }
}
