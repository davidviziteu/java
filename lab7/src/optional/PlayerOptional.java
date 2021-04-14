package optional;


import compulsory.Token;

import java.util.ArrayList;


//base class
public abstract class PlayerOptional extends Thread{
    ArrayList<Token> playerTokens;

    public String getPlayerName() {
        return new String(this.name + "[" + id + "] ");
    }

    String name;
    int id;
    static int nextId = 0;
    static BoardSyncronised board;
    static int currentTurn;
    public static ArrayList<PlayerOptional> getAllPlayers() {
        return allPlayers;
    }

    static ArrayList<PlayerOptional> allPlayers = new ArrayList<>();


    PlayerOptional(){
        name = "";
        playerTokens = new ArrayList<>();
        board = BoardSyncronised.getBoard();
        id = nextId;
        ++nextId;
        allPlayers.add(this);
        currentTurn = 0;
    }

    public static void nextTurn(){
        currentTurn = (currentTurn + 1) % allPlayers.size();
    }

    public PlayerOptional setPlayerName(String name){
        this.name = name;
        return this;
    }

    public void awakeTimekeeper(){
        var tk = TimeKeeper.getInstance();
        synchronized (tk){
            tk.notify();
        }
    }

    public PlayerOptional grabToken(Token newToken){
        playerTokens.add(newToken);
        return this;
    }
}
