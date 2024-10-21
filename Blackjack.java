import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
    private Baralho baralho;
    private List<Jogador> jogadores;
    private Jogador dealer;
    private Scanner scan;
    private boolean dealerGodMod;

    public Blackjack(List<String> nomesJogadores, int qtdeBaralhos, Scanner scan, boolean dealerGodmod) {
        this.baralho = new Baralho(qtdeBaralhos);
        this.jogadores = new ArrayList<>();
        this.scan = scan;
        this.dealerGodMod = dealerGodmod;
        this.dealer = new Jogador();
        dealer.setNomeJogador("Dealer");
        dealer.setDealer(true);

        for (int i = 0; i < nomesJogadores.size(); i++) {
            Jogador jogador = new Jogador();
            jogador.setNomeJogador(nomesJogadores.get(i));
            jogadores.add(jogador);
        }

        this.jogar();
        this.jogadaDealer();
        this.verificarVencedor();
    }

    private void jogar() {
        dealer.addCard(baralho.deal());

        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);
            jogador.addCard(baralho.deal());
            jogador.addCard(baralho.deal());

            if (jogador.getValorMao() == 21) {
                System.out.println(String.format("%s tem blackjack!", jogador.getNomeJogador()));
                continue;
            }

            dealer.printMao();

            while (this.verificarJogadorPodeContinuar(jogador)) {
                System.out.println("");
                jogador.printMao();
                System.out.println("Deseja comprar? s ou n");
                String continuar = scan.nextLine();

                if (continuar.toLowerCase().equals("s")) {
                    jogador.addCard(baralho.deal());
                    jogador.printMao();

                    if (jogador.getValorMao() > 21) {
                        System.out.println(String.format("%s estourou", jogador.getNomeJogador()));
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("");
                    break;
                }
            }
        }
    }

    private void jogadaDealer() {
        if (dealerGodMod) {
            while (dealer.getValorMao() != 21) {
                this.jogadaDealerGodMod();
            }
        } else {
            while (verificarDealerPodeContinuar()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                dealer.printMao();
                dealer.addCard(baralho.deal());
                dealer.printMao();
            }

            dealer.printMao();
        }
    }

    private void jogadaDealerGodMod() {
        List<Carta> cartas = dealer.getCards();

        if (dealer.getValorMao() < 21) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            dealer.printMao();
            dealer.addCard(baralho.deal());
            dealer.printMao();
            return;
        }

        if (dealer.getValorMao() > 21) {

            if (!cartas.isEmpty()) {
                cartas.remove(cartas.size() - 1);
            }

            dealer.printMao();
            dealer.addCard(baralho.deal());
            dealer.printMao();
            return;
        }

    }

    private boolean verificarJogadorPodeContinuar(Jogador maoJogador) {
        if (maoJogador.getValorMao() == 21) {
            return false;
        }

        if (maoJogador.getValorMao() > 21) {
            return false;
        }

        return true;
    }

    private boolean verificarDealerPodeContinuar() {
        if (dealer.getValorMao() == 21) {
            return false;
        }

        if (dealer.getValorMao() > 21) {
            return false;
        }

        if (dealer.getValorMao() >= 17) {
            return false;
        }

        return true;
    }

    private void verificarVencedor() {
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);

            if (jogador.getCards().size() == 2 && jogador.getValorMao() == 21) {
                if (dealer.getCards().size() == 2 && dealer.getValorMao() == 21) {
                    System.out.println(
                            String.format("%s tem blackjack, dealer tem blackjack, empate!", jogador.getNomeJogador()));
                    continue;
                }
            }

            if (jogador.getCards().size() == 2 && jogador.getValorMao() == 21) {
                System.out.println(
                        String.format("%s tem blackjack, dealer tem %s, jogador ganhou!", jogador.getNomeJogador(),
                                dealer.getValorMao()));
                continue;
            }

            if (dealer.getCards().size() == 2 && dealer.getValorMao() == 21) {
                System.out.println(
                        String.format("%s tem %d, dealer tem blackjack, dealer ganhou!", jogador.getNomeJogador(),
                                jogador.getValorMao()));
                continue;
            }

            if (jogador.getValorMao() > 21) {
                System.out.println(String.format("%s estourou, dealer Ganhou!", jogador.getNomeJogador()));
                continue;
            }

            if (jogador.getValorMao() < 21 && dealer.getValorMao() > 21) {
                System.out.println(String.format("Dealer estourou, %s ganhou!", jogador.getNomeJogador()));
                continue;
            }

            if (jogador.getValorMao() == dealer.getValorMao()) {
                System.out.println(String.format("%s tem %d, Dealer tem %d. Empate!", jogador.getNomeJogador(),
                        jogador.getValorMao(), dealer.getValorMao()));

                continue;
            }

            if (jogador.getValorMao() < dealer.getValorMao() && dealer.getValorMao() <= 21) {
                System.out.println(String.format("%s tem %d, Dealer tem %d. Dealer ganhou!", jogador.getNomeJogador(),
                        jogador.getValorMao(), dealer.getValorMao()));
                continue;
            }

            if(jogador.getValorMao() > dealer.getValorMao() && dealer.getValorMao() <= 21){
                System.out.println(String.format("%s tem %d, Dealer tem %d. Jogador ganhou!", jogador.getNomeJogador(),
                        jogador.getValorMao(), dealer.getValorMao()));
                continue;
            }
        }
    }

}
