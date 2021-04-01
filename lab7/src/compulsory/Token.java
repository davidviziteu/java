package compulsory;

import java.util.Random;

public class Token {
    private int x, y, value;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public Token(){
        x = 0;
        y = 0;
        value = 0;
    }

    public static Token newToken(){
        return new Token();
    }

    public Token setX(int x){
        this.x = x;
        return this;
    }

    public Token setY(int y){
        this.y = y;
        return this;
    }

    public Token setValue(int v){
        value = v;
        return this;
    }
    public Token setRandomValue(int max){
        var rand = new Random();
        value = rand.nextInt(max);
        return this;
    }
}
