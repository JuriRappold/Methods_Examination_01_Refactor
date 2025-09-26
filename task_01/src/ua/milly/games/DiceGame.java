package ua.milly.games;
import ua.milly.utils.Avatar;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.random.RandomGenerator;
import java.util.function.Supplier;

public class DiceGame {
    
    public void Start(){
        EnterEater();        //Start the game
        System.out.println(instructions);
        GameState endvalues = RunGame(new GameState(0,0));
        //Show who won!
        System.out.printf(WinnerEvaluation.apply(endvalues.sumFriend(),endvalues.sumPlayer()),Avatar.AvatarName);
        //repeat the game?
        EndOfGame(endvalues.sumFriend(),endvalues.sumPlayer());
        if(UserYes.getAsBoolean())Start();
    }
        private GameState RunGame(GameState state){
        //Choosing who will play first
        //text for it
        System.out.println(whoIsFirst);
        //if Friend first, play
        if(IsFriendFirst.get()){
            int sumFriend = FriendPlay(0);
            //did Friend win?
            if (gaming.test(state.sumPlayer(),sumFriend)) return new GameState(state.sumPlayer(), sumFriend);
                else {
                    //he plays
                    int sumPlayer = PlayerPlay(0, true);
                    return new GameState(sumPlayer, sumFriend);
                }
        }
        else {
            int sumPlayer = PlayerPlay(0, true);
            if (gaming.test(state.sumPlayer(),0)) return new GameState(state.sumPlayer(), 0);
            else return new GameState(sumPlayer, FriendPlay(0));       
        }
    }


        private void EndOfGame(int sumPlayer, int sumFriend) {
        System.out.print("\nPlay once more? [Y/N]");
        }


    //functions

    private static Scanner input = new Scanner(System.in);
    static final private String[] diceGraphics = {"⚀", "⚁", "⚂", "⚃", "⚄", "⚅"};


    //idk whether it will be used USING FOR PRESENTATION FOR SURE
    //private static final Function<Integer, Integer> Roll = (Integer sides) -> RandomGenerator.getDefault().nextInt(sides) + 1;

    //roll the dice
    private static final Supplier<Integer> Roll6 = () -> RandomGenerator.getDefault().nextInt(6) + 1;

    private static final Supplier<Boolean> IsFriendFirst = () -> Roll6.get()%2==0;
    /* IF WE WANT THE SAME THING BUT IT'S LESS FUNCTIONAL TO HAVE OUTPUT IN FUNCTIONS
    {
    int roll = Roll6.get();
    System.out.printf("The die's value is %d %s%n", roll, diceGraphics[roll - 1]);
    return roll % 2 == 0;
};*/

    //Friend limit for friend to play
    private final int LIMIT = 21 - Roll6.get();

    //Friend's turn
    private int FriendPlay(int sum){
        if (sum > LIMIT) return sum;
        else{
            int roll = Roll6.get();
            System.out.printf("%s rolled %s(%d). The sum is %d.%n", Avatar.AvatarName, diceGraphics[roll-1],roll, sum+roll);
            return FriendPlay(sum + roll);
        }
    }

    //player's turn
    private int PlayerPlay(int sum, boolean playing){
        if (!playing) return sum;
        int roll = Roll6.get();
        System.out.printf("You rolled %s(%d). The sum is %d.\n", diceGraphics[roll-1],roll, sum+roll);
        if (!playing || sum+roll >= 21) return sum+roll;
        System.out.printf("Roll once more [Y/N]?");
        return PlayerPlay(sum + roll, UserYes.getAsBoolean());
    }

    private static final BiPredicate<Integer,Integer> gaming = (Integer sumPlayer, Integer sumFriend) -> sumPlayer >= 21 || sumFriend >= 21;

    private static final BiFunction<Integer,Integer,String> WinnerEvaluation = (Integer sumPlayer, Integer sumFriend) -> { 
        if ((sumPlayer <= 21 && (sumPlayer > sumFriend || sumFriend>21)))    return"\nPlayer wins!!!🎉";
        else if (sumFriend <= 21 && (sumFriend > sumPlayer || sumPlayer>21)) return "\n%s wins🎉🎉🎉";
        else if (sumFriend==sumPlayer)                                       return "\n Tie🤷";
        else                                                                 return "\nDon't do gambling, kids!";
        };

    private static final BooleanSupplier UserYes = () -> Character.toLowerCase(input.next().charAt(0))=='y';
    private static final void EnterEater() {
        if (input.hasNextLine()) input.nextLine();
    };


    //texts
    private static final String instructions = """
            ----------------------------
            !!!Milly's edition of dice game!!!
            |Players aim to score 21, or as near as possible to it!
            |So player can throw the dice as many times as desired and adding up the numbers thrown.
            |A player who totals over 21 is bust and is out of the game!!!!
            |The player whose total is nearest 21, wins the game.
            | In the case of having 21, you win!
            ---------------------------
            G O O D            L U C K
            """;
    
    private static final String whoIsFirst = "Choosing who will play first. Rolling the die..";
}
