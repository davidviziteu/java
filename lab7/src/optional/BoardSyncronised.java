package optional;

import compulsory.Token;

import java.util.LinkedList;
import java.util.List;

public class BoardSyncronised {
    List<Token> tokenList;
    int n;
    static BoardSyncronised instance = null;

    BoardSyncronised(){
        tokenList = new LinkedList<>();
    }

    public static BoardSyncronised getBoard(){
        if(instance == null) {
            instance = new BoardSyncronised();
            return instance;
        }
        return instance;
    }

    public void makeTokens(int n){
        this.n = n;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j) {
                if(i != j)
                    tokenList.add(Token.newToken().setRandomValue(i+j).setX(i).setY(j));
            }
        }
    }
    synchronized public void startGame(){
        synchronized (this){
            PlayerOptional.getAllPlayers().get(0).notify();
        }
    }
    synchronized public void getTurn() throws InterruptedException {
        synchronized (this){
            this.wait();
        }
    }

    synchronized public Token getToken(int x, int y){
        for (int i = 0 ; i < tokenList.size(); ++i) {
            var token = tokenList.get(i);
            if (token.getX() == x && token.getY() == y) {
                tokenList.remove(i);
                return token;
            }
        }
        return null;
    }

    synchronized public void nextPlayer(int currentPlayerId){
        synchronized (this){

            var allPlayers = PlayerOptional.getAllPlayers();
            allPlayers.get((currentPlayerId + 1) % allPlayers.size()).notify();
        }
    }

    public void printAvailableTokens(){
        tokenList.forEach(t -> {
            System.out.print("Token "+ t.getValue() + " (" + t.getX() + ", " + t.getY() + ")\n");
        });
    }

    public int getTokensLeft(){
        return tokenList.size();
    }
    public int getN(){
        return n;
    }
}
