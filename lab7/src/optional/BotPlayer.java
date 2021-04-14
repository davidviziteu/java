package optional;

import compulsory.Token;

import java.io.IOException;
import java.util.Random;

public class BotPlayer extends PlayerOptional {

    public static PlayerOptional newBotPlayer() {
        return new BotPlayer();
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
                System.out.println("Bot's turn");
                if (board.getTokensLeft() == 0) {
                    System.out.print("Bot player " + this.name + " got " + playerTokens.size() + " tokens: ");
                    playerTokens.forEach(t -> {
                        System.out.print("(x: " + t.getX() + ", y: " + t.getY() + ", val: " + t.getValue() + "); ");
                    });
                    PlayerOptional.nextTurn();
                    synchronized (PlayerOptional.getAllPlayers().get(PlayerOptional.currentTurn)) {
                        PlayerOptional.getAllPlayers().get(PlayerOptional.currentTurn).notifyAll();
                    }
                    awakeTimekeeper();
                    return;
                }
                var rand = new Random();
                Token token;
                do {
                    var x = rand.nextInt(board.getN());
                    var y = rand.nextInt(board.getN());
                    token = board.getToken(x, y); // sincronized
                } while (token == null);
                playerTokens.add(token);
                PlayerOptional.nextTurn();
                System.out.println("bot[" + this.id + "] " + this.name + " set next turn to: " + PlayerOptional.currentTurn);
                synchronized (PlayerOptional.getAllPlayers().get(PlayerOptional.currentTurn)) {
                    PlayerOptional.getAllPlayers().get(PlayerOptional.currentTurn).notify();
                }
            }
        } catch (InterruptedException e) {
            if(!this.isInterrupted())
                e.printStackTrace();
        }
    }
}
