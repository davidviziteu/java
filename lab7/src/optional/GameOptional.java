package optional;


import java.io.IOException;
import java.util.ArrayList;

import static optional.BotPlayer.newBotPlayer;
import static optional.HumanPlayer.newHumanPlayer;

public class GameOptional {
    public static void main(String[] args) throws InterruptedException {
        var board = BoardSyncronised.getBoard();
        int n = 3;
        board.makeTokens(n);
        var eu = newHumanPlayer().setPlayerName("David");
        var bot = newBotPlayer().setPlayerName("Bot Boris");
        var playerList = new ArrayList<PlayerOptional>();
        var timekeeper = TimeKeeper.getInstance();
        timekeeper.setTime(2);
        playerList.add(eu);
        playerList.add(bot);
        var t1 = new Thread(eu);
        var t2 = new Thread(bot);
        var t3 = new Thread(timekeeper);
        t3.setDaemon(true);
        t3.start();
        t1.start();
        t2.start();
        try{
            t3.join();
            t1.join();
            t2.join();
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        var scoreComputer = new WinnerCalculator(playerList);
        scoreComputer.printWinner();
        System.exit(0);
        System.out.println("Board tokens left: " + board.getTokensLeft());
    }
}
