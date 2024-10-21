import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        List<String> jogadores = new ArrayList<>(); // Mover a lista para fora do loop
        Scanner scan = new Scanner(System.in);
        boolean godMod = false; // Variável para armazenar o estado do god mod

        // Adiciona jogadores apenas se a lista estiver vazia
        while (true) {
            if (jogadores.isEmpty()) {
                while (true) {
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Nome do jogador ou 'c' para continuar: ");
                    String continuar = scan.nextLine();

                    if (continuar.toLowerCase().equals("c")) {
                        break;
                    } else {
                        jogadores.add(continuar);
                    }
                }

                // Pergunta sobre o dealer god mod apenas uma vez
                System.out.println("Dealer god mod? 's' para sim");
                String dealerGodMod = scan.nextLine();
                godMod = dealerGodMod.toLowerCase().equals("s");
            }

            // Inicia o jogo de Blackjack
            new Blackjack(jogadores, 4, scan, godMod);

            System.out.println("Jogar novamente? 's' para sim");
            String jogarNovamente = scan.nextLine();

            if (!jogarNovamente.toLowerCase().equals("s")) {
                scan.close();
                break; // Encerra o loop se não quiser jogar novamente
            }

            // Se decidir jogar novamente, não faz nada com a lista de jogadores nem com o god mod
        }
    }
}
