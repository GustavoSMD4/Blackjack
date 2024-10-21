
public class Carta {
    private String naipe;
    private String rank;
    private int valor;

    public Carta(String naipe, String rank, int valor) {
        this.naipe = naipe;
        this.rank = rank;
        this.valor = valor;
    }

    public String getCarta(){
        return String.format("%s %s", rank, naipe);
    }

    public String getNaipe() {
        return naipe;
    }

    public String getRank() {
        return rank;
    }

    public int getValor() {
        return valor;
    }

    

}