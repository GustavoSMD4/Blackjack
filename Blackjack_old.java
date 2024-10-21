
import java.util.Scanner;

public class Blackjack_old {
    private Baralho baralho;
    private Jogador maoJogador;
    private Jogador maoDealer;

    public Blackjack_old(int qtdeBaralhos) {
        this.baralho = new Baralho(qtdeBaralhos);
        this.maoJogador = new Jogador();
        this.maoDealer = new Jogador();
        maoDealer.setDealer(true);

        maoJogador.addCard(baralho.deal());
        maoDealer.addCard(baralho.deal());
        maoJogador.addCard(baralho.deal());

    }

    public void jogar() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Cartas dealer");
        maoDealer.printCards();
        System.out.println(String.format("Dealer tem %d", maoDealer.getValorMao()));

        if(maoJogador.getValorMao() == 21){
            System.out.println("");
            System.out.println("Cartas jogador");
            maoJogador.printCards();
            System.out.println("Jogador tem Blackjack!");

            maoDealer.addCard(baralho.deal());
            System.out.println("");
            System.out.println("Cartas do dealer");
            maoDealer.printCards();
            System.out.println(String.format("Dealer tem %d", maoDealer.getValorMao()));

            if(maoDealer.getValorMao() == 21){
                System.out.println("Empate, dealer também tem Blackjack");
                scan.close();
                return;
            }else{
                System.out.println("Jogador ganhou!");
                scan.close();
                return;
            }
        }

        while (this.verificarJogadorPodeContinuar()) {
            System.out.println("");
            System.out.println("Cartas jogador");
            maoJogador.printCards();
            System.out.println(String.format("Jogador tem %d", maoJogador.getValorMao()));
            System.out.println("comprar? s ou n");
            String continuar = scan.nextLine();
            System.out.println("");

            if (continuar.toLowerCase().equals("s")) {
                this.comprarCarta(maoJogador);
            } else {
                break;
            }
        }

        scan.close();

        if (maoJogador.getValorMao() > 21){

            System.out.println("Jogador estourou!");
            return;
        }

        this.jogadaDealer();
    }

    private void jogadaDealer() {
        System.out.println("Cartas dealer");
        this.comprarCarta(maoDealer);
        maoDealer.printCards();
        System.out.println(String.format("Dealer tem %d", maoDealer.getValorMao()));

        if(maoDealer.getValorMao() == 21){
            System.out.println(("Dealer tem Blackjack!"));
            return;
        }

        while (this.verificarDealerPodeContinuar()) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.comprarCarta(maoDealer);
            System.out.println("");
            maoDealer.printCards();
            System.out.println(String.format("Dealer tem %d", maoDealer.getValorMao()));
        }

        this.verificarVencedor();
    }

    public Jogador getMaoJogador() {
        return this.maoJogador;
    }

    public Jogador getMaoDealer() {
        return this.maoDealer;
    }

    public int getValorMaoJogador() {
        return maoJogador.getValorMao();
    }

    public int getValorMaoDealer() {
        return maoDealer.getValorMao();
    }

    public void comprarCarta(Jogador mao) {
        mao.addCard(baralho.deal());
    }

    private boolean verificarJogadorPodeContinuar() {
        if (maoJogador.getValorMao() == 21) {
            return false;
        }

        if (maoJogador.getValorMao() > 21) {
            return false;
        }

        return true;
    }

    private boolean verificarDealerPodeContinuar() {
        if (maoDealer.getValorMao() == 21) {
            return false;
        }

        if (maoDealer.getValorMao() > 21) {
            return false;
        }

        if (maoDealer.getValorMao() >= 17) {
            return false;
        }

        return true;
    }

    private void verificarVencedor(){
        if (maoJogador.getValorMao() > 21){
            System.out.println("Dealer Ganhou");
            return;
        }

        if(maoJogador.getValorMao() < 21 && maoDealer.getValorMao() > 21){
            System.out.println("Dealer estourou, jogador ganhou!");
            return;
        }

        if (maoDealer.getValorMao() > 21){
            System.out.println("Dealer estourou");
            return;
        }

        if(maoJogador.getValorMao() == maoDealer.getValorMao()){
            System.out.println(String.format("Jogador tem %d, Dealer tem %d. Empate!", maoJogador.getValorMao(), maoDealer.getValorMao()));

            return;
        }

        if(maoJogador.getValorMao() > maoDealer.getValorMao()){
            System.out.println(String.format("Jogador tem %d, Dealer tem %d. Jogador ganhou!", maoJogador.getValorMao(), maoDealer.getValorMao()));
            return;
        }else{
            System.out.println(String.format("Jogador tem %d, Dealer tem %d. Dealer ganhou!", maoJogador.getValorMao(), maoDealer.getValorMao()));
            return;
        }

    }

}
