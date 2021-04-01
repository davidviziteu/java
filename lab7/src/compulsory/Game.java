package compulsory;

public class Game {
    public static void main(String[] args) throws InterruptedException {
        var board = Board.getBoard();
        int n = 10;
        board.makeTokens(n);
        var t1 = new Thread(Player.newPlayer().setName("David"));
        var t2 = new Thread(Player.newPlayer().setName("Andrei"));
        var t3 = new Thread(Player.newPlayer().setName("Andreea"));
        var t4 = new Thread(Player.newPlayer().setName("Mirela"));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        while(board.getTokensLeft() != 0){
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }
        System.out.println("Board tokens left: " + board.getTokensLeft());
    }
}
