package compulsory;

import java.util.LinkedList;
import java.util.List;

public class Board {
    List<Token> tokenList;
    int n;
    static Board instance = null;

    Board(){
        tokenList = new LinkedList<>();
    }

    public static Board getBoard(){
        if(instance == null) {
            instance = new Board();
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

    public Token getToken(int x, int y){
        for (int i = 0 ; i < tokenList.size(); ++i) {
            var token = tokenList.get(i);
            if (token.getX() == x && token.getY() == y) {
                tokenList.remove(i);
                return token;
            }
        }
        return null;
    }

    public int getTokensLeft(){
        return tokenList.size();
    }
    public int getN(){
        return n;
    }
}
