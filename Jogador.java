import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private List<Carta> cards;
    private String nomeJogador;
    private boolean dealer;

    public Jogador() {
        cards = new ArrayList<>();
        this.dealer = false;
    }

    public void printCards() {
        for (Carta carta : cards) {
            System.out.println(carta.getCarta());
        }
    }

    public void printMao() {
        System.out.println(String.format("Cartas de %s", nomeJogador));
        this.printCards();
        System.out.println(String.format("%s tem %d", nomeJogador, this.getValorMao()));
    }

    public void addCard(Carta card) {
        cards.add(card);
    }

    public int getValorMao() {
        int value = 0;
        int aces = 0;

        for (Carta card : cards) {
            value += card.getValor();
            if (card.getValor() == 11)
                aces++;
        }

        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }

        return value;
    }

    public List<Carta> getCards() {
        return cards;
    }

    public void setCards(List<Carta> cards) {
        this.cards = cards;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public boolean isDealer() {
        return dealer;
    }

    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }

}
