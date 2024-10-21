import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    private List<Carta> baralho;

    public Baralho(int qtdeBaralhos) {
        if (qtdeBaralhos > 0) {
            this.gerarCartas(qtdeBaralhos);
        } else {
            this.gerarCartas(1);
        }
    }

    public List<Carta> getBaralho() {
        return baralho;
    }

    public Carta deal() {
        return baralho.remove(baralho.size() - 1);
    }

    private void gerarCartas(int qtdeBaralhos) {
        this.baralho = new ArrayList<>();
        String[] naipes = { "Copas", "Ouros", "Espadas", "Paus" };
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

        for (int x = 0; x < qtdeBaralhos; x++) {
            for (String suit : naipes) {
                for (int i = 0; i < ranks.length; i++) {
                    int valorCarta = i < 8 ? i + 2 : i < 12 ? 10 : 11;
                    baralho.add(new Carta(suit, ranks[i], valorCarta));
                }
            }
        }

        Collections.shuffle(baralho);
    }

}
