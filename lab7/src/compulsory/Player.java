package compulsory;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Player implements Runnable{
    ArrayList<Token> playerTokens;
    String name;
    static ReentrantLock threadLock;
    static Board board;

    Player(){
        name = "";
        playerTokens = new ArrayList<>();
        board = Board.getBoard();
        threadLock = new ReentrantLock();
    }

    public static Player newPlayer(){
        return new Player();
    }

    public Player setName(String name){
        this.name = name;
        return this;
    }

    public Player grabToken(Token newToken){
        playerTokens.add(newToken);
        return this;
    }

    @Override
    public void run() {
        while(true){
            threadLock.lock();
            if(board.getTokensLeft() == 0){
                System.out.print("player " + this.name + " got " + playerTokens.size() + " tokens: ");
                playerTokens.forEach(t ->{
                    System.out.print("(x: " + t.getX() + ", y: " + t.getY() + ", val: " + t.getValue() + "); ");
                });
                System.out.println();
                threadLock.unlock();
                return;
            }
            var rand = new Random();
            Token token;
            do{
                var x = rand.nextInt(board.getN());
                var y = rand.nextInt(board.getN());
                token = board.getToken(x, y); // sincronized
            } while(token == null);
            playerTokens.add(token);
            threadLock.unlock();
        }
    }
}
