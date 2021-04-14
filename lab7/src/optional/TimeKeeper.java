package optional;
//also keeper and turns;

import java.util.ArrayList;

public class TimeKeeper extends Thread {
    int gameTime;
    volatile static boolean gameOn;
    static TimeKeeper instance = null;
    static public TimeKeeper getInstance(){
        if(instance == null)
            instance = new TimeKeeper();
        return instance;
    }
    TimeKeeper(){
        gameTime = -1;
    }
    public void setTime(int time){
        gameTime = time;
    }

    @Override
    public void run() {
        gameOn = true;
        long startTime = System.currentTimeMillis();
        try {
            synchronized (this){
                wait(gameTime * 1000L);
            }
            gameOn = false;
            if(BoardSyncronised.getBoard().getTokensLeft() == 0)
                System.out.println("\nNo tokens left;");
            System.out.println("\nTime's up");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("The game took " + (endTime - startTime) + " milliseconds");
    }
}
