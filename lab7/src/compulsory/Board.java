package compulsory;

import java.util.LinkedList;
import java.util.List;

public class Board {
    List<Token> tokenList;
    int n;

    public Board(){
        tokenList = new LinkedList<>();
    }

    public void makeTokens(int n){
        this.n = n;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j) {
                if(i != j)
                    tokenList.add(Token.newToken().setRandomValue(i+j).setX(i).setY(i));
            }
        }
    }

    public Token getToken(int x, int y){
        for (Token token : tokenList) {
            if (token.x == x && token.y == y)
                return token;
        }
        return null;
    }
}
