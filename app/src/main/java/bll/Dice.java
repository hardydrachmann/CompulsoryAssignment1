package bll;

import java.util.Random;

/**
 * Created by Hardy Drachmann on 29-02-2016.
 */
public class Dice {
    private Random randomNumber = new Random();
    private int face = 0;

    public Dice() {
        rollDice();
    }
    public int getFace() {
        return face + 1;
    }

    public void rollDice() {
        face = randomNumber.nextInt(6);
    }
}
