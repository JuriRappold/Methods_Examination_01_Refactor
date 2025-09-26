package ua.milly.games;
import ua.milly.utils.Avatar;
import java.util.Scanner;
import java.util.Random;

public class DiceGame21 {
    Dice die = new Dice(6);
    Scanner input = new Scanner(System.in);
    boolean playing = true;
    Random r = new Random();

    public void play(){
        playing = true;
        int sumMe = 0;
        int sumFriend = 0;
        String instructions = """
                ----------------------------
                |Players in turn aim to score 21, or as near as possible to it!
                |So player can throw the dice as many times as desired and adding up the numbers thrown.                ----------------------------
                |A player who totals over 21 is bust and is out of the game!!!!
                |The player whose total is nearest 21, wins the game.
                | In the case of having 21, you win!
                ---------------------------
                G O O D            L U C K
                """;
        System.out.println(instructions);


        while(playing) {
            System.out.println("Choosing who's playing first by rolling a dice...");
            if (isPlayerFirst()) {
                System.out.printf("The die's value is %d, you play first!%n", die.getValue());
                sumMe = playerTurn();
                if (playing){
                System.out.printf("Now is %s's turn!", Avatar.AvatarName);
                sumFriend = friendTurn();}
            } else {
                System.out.printf("The die's value is %d, %s plays first!%n", die.getValue(), Avatar.AvatarName);
                sumFriend = friendTurn();
                if(playing){
                System.out.println("Now it's your turn!");
                sumMe = playerTurn();}
            }

            if (playing){
            String thinking = """
                    Calculating the winner...
                    You: %d
                    %s: %d
                    """;

            System.out.printf(thinking, sumMe, Avatar.AvatarName, sumFriend);
            String winner = sumFriend > sumMe ? "Hehe! %s wins!" : "You win! %n%n%s is now crying in the corner... what have you done..";
            System.out.printf(winner, Avatar.AvatarName);}

            playing = isOngoing();

        }
    }

    private boolean isOngoing(){
        System.out.print("\nPlay once more? [Y/N]");
        String choice = input.nextLine().toLowerCase();
        return choice.equals("y");
    }

    private boolean isPlayerFirst(){
        return die.roll()%2 == 0;
    }

    private int playerTurn(){
        int sum = 0;
        String choice = "";
        do{
            die.roll();
            System.out.println(die.toString());
            sum += die.getValue();
            if (sum<21){
                System.out.printf("The sum is %d. Roll once more [Y/N]?", sum);
                choice = input.nextLine().toLowerCase();
            }
            else if (sum == 21){
                System.out.println("The sum is 21. Wow! You won the game!");
                playing = false;
            }
            else{
                System.out.printf("The sum is %d. What a shame for you... %s wins!", sum, Avatar.AvatarName);
                playing = false;
                return sum;
            }
        }
        while (!choice.equals("n") && playing);
        return sum;
    }

    private int friendTurn(){


        int sum = 0;
        while(sum < 21-r.nextInt(6)){
            die.roll();
            System.out.println(die.toString());
            sum += die.getValue();
            if (sum<21){
                System.out.printf("The sum is %d. Will %s roll once more?%n", sum, Avatar.AvatarName);
                thinking(sum);
            }
            else if (sum == 21){
                System.out.printf("The sum is 21. Wow! %s won the game!",Avatar.AvatarName);
                playing = false;
                return sum;
            }
            else{
                System.out.printf("The sum is %d. What a shame... You win!", sum);
                playing = false;
            }
        }
        if (playing){
            System.out.println("Dino stops rolling the dice!");
        }
    return sum;
    }

    private void thinking(int sum){
        int time = 500;
        if (sum < 16){
             time = r.nextInt(5)*time;
        }
        else if (sum < 19){
            time = r.nextInt(8)*time;
        }
        else{
            time = r.nextInt(8)*time*2;
        }

        try {Thread.sleep(time);} catch (Exception e) {System.out.println("z z z... oh!");}
    }
}


