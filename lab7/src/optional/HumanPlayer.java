package optional;

import compulsory.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
public class HumanPlayer extends PlayerOptional {
    public static PlayerOptional newHumanPlayer() {
        return new HumanPlayer();
    }

    @Override
    public void run() {
        try {
            while (TimeKeeper.gameOn) {
                synchronized (this) {
                    while (PlayerOptional.currentTurn != this.id) {
                        wait();
                    }
                }
                if (board.getTokensLeft() == 0) {
                    System.out.print("You, " + this.name + " got " + playerTokens.size() + " tokens: ");
                    playerTokens.forEach(t -> {
                        System.out.print("(x: " + t.getX() + ", y: " + t.getY() + ", val: " + t.getValue() + "); ");
                    });
                    System.out.println();
                    PlayerOptional.nextTurn();
                    synchronized (PlayerOptional.getAllPlayers().get(PlayerOptional.currentTurn)) {
                        PlayerOptional.getAllPlayers().get(PlayerOptional.currentTurn).notifyAll();
                    }
                    awakeTimekeeper();
                    return;
                }
                var rand = new Random();
                Token token = null;
                System.out.println("Extract one of the available tokens: ");
                board.printAvailableTokens();
                do {
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    try {

                        System.out.print("Enter x for the token you wanna pick: ");
                        String text = in.readLine();
                        int x = Integer.parseInt(text.trim());
                        System.out.print("Enter y for the token you wanna pick: ");
                        text = in.readLine();
                        int y = Integer.parseInt(text.trim());
                        token = board.getToken(x, y);
                        if (token == null) {
                            System.out.println("Invalid token. Pls pick new one");
                        }
                    } catch (NumberFormatException e) {
                        if(!this.isInterrupted())
                            System.out.println("thats not a number");
                    }
                    if(isInterrupted())
                        return;
                } while (token == null);
                playerTokens.add(token);
                PlayerOptional.nextTurn();
                System.out.println("player " + this.name + " set next turn to: " + PlayerOptional.currentTurn);
                synchronized (PlayerOptional.getAllPlayers().get(PlayerOptional.currentTurn)) {
                    PlayerOptional.getAllPlayers().get(PlayerOptional.currentTurn).notify();
                }
            }
        } catch (InterruptedException | IOException e) {
            if(!this.isInterrupted())
                e.printStackTrace();
        }
    }
}
