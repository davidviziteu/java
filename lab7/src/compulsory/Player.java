package compulsory;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

public class Player implements Runnable{
    ArrayList<Token> tokens = new ArrayList<>();
    String name;
    static Lock threadLock;
    Player(){
        name="";
    }

    public static Player newPlayer(){
        return new Player();
    }

    public Player setName(String name){
        this.name = name;
        return this;
    }

    public Player addToken(Token newToken){
        tokens.add(newToken);
        return this;
    }

    @Override
    public void run() {
        threadLock.lock();

    }
}
