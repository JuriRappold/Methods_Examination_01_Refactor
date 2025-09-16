package games;
import java.util.Random;

public class Dice {
    private final int sides;
    private int lastRoll;
    static final private String[] diceGraphics = {"⚀", "⚁", "⚂", "⚃", "⚄", "⚅"};

    public Dice (int sides){
        this.sides = sides;
    }

    public int roll(){
        Random r = new Random();
        int roll = r.nextInt(sides);
        this.lastRoll = roll+1;
        return roll + 1;
    }

    public int getValue(){
        return lastRoll;
    }

    @Override
    public String toString(){
        return "Dice rolled: "+diceGraphics[lastRoll-1]+"("+lastRoll+")";
    }




}