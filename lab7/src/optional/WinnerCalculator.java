package optional;

import compulsory.Token;

import java.util.ArrayList;

public class WinnerCalculator {
    private ArrayList<PlayerOptional> players;

    WinnerCalculator(ArrayList<PlayerOptional> players) {
        this.players = players;
    }

    public int getScore(PlayerOptional player) {
        return player.playerTokens.stream().mapToInt(Token::getValue).sum();
    }

    public void printWinner() {
        int maxim = -1;
        String winnerPlayer = "";
        for (PlayerOptional player : players) {
            if (getScore(player) > maxim) {
                maxim = getScore(player);
                System.out.println("Player" + player.getPlayerName() + " has " + maxim + " points");
                winnerPlayer = player.name;
            }
        }
        System.out.println("Winner is " + winnerPlayer);
    }
}