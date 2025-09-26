package ua.milly.games;
import ua.milly.utils.Avatar;

import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissor {
    //Attributes
    Scanner input = new Scanner(System.in);
    Random r = new Random();
    boolean playing = true;
    int sumPlayer = 0;
    int sumAvatar = 0;
    //store game icons/emojis in array? have all the strings be static?
    String helloMessage= """
                Welcome to a game of rock ✊, scissor ✂️, paper ✋!
                You will play against %s and we keep score. %n
                """;
    String playerTurn = """
                    Score: You %d - %s %d
                    Select rock ✊ [r], scissor ✂️ [s], paper ✋ [p] or quit [q]:
                    """;
    String resultString = "You did %s and %s did %s. +1 point to %s";
    //1)player's choice: rock/paper/scissors 2) avatar.name 3) avatar's choice: rock/paper/scissors 4) winner's name


    //Methods
    public void play(){
        //Start of the game
        System.out.printf(helloMessage,Avatar.AvatarName);

        //game play
        while(playing){
            //Current score & requesting a move by the player
            System.out.printf(playerTurn, sumPlayer, Avatar.AvatarName, sumAvatar);
            //reading the move from the player; could immediately check if it's a valid option, maybe w/ a stream & lambda
            String choicePlayer = input.nextLine().toLowerCase().trim();
            //avatar choice
            String choiceAvatar = ChoiceAvatar();
            // "only" now checks player input
            if(choicePlayer.equals("r")|| choicePlayer.equals("s")||choicePlayer.equals("p")){
                System.out.printf(calculateWinner(choicePlayer,choiceAvatar),Avatar.AvatarName);
            }
            //player quits
            else if(choicePlayer.equals("q")) {
                playing = false;
            }
            //player inputted wrong char
            else{
                    System.out.println("Wrong input. Please, write [s,q,p]: ");//or r?
            }
        }
    }
    /*
    could refactor it using a stream, but it's already pretty concise & very readable.
    Only thing I would do is add a case 2 ->
    maybe put it into the avatar class? probably not, its game specific
     */
    private String ChoiceAvatar(){
        int choice = r.nextInt(3);
        switch (choice){
            case 0 -> {return "r";}
            case 1 -> {return "s";}
            case 2 -> {return "p";}
            default -> {throw new RuntimeException("Something went wrong in [ChoiceAvatar()], please restart the program.");}
        }

    }

    // Rock, Scissor, Paper
    /*
    clean up the else-if statements; especially the strings could be used more efficiently
     */
    private String calculateWinner(String pl, String av){
        if (Objects.equals(pl, av)){
            return"Wow.. That's a tie!\n";
        }
        else if(Objects.equals(pl, "r") && Objects.equals(av, "s")){
            sumPlayer++;
            return"You did ✊ and %s did ✂️. +1 point to You!\n";
        }
        else if(Objects.equals(pl, "r") && Objects.equals(av, "p")){
            sumAvatar++;
            return"You did ✊ and %s did ✋\n";
        }
        else if(Objects.equals(pl, "s") && Objects.equals(av, "r")){
            sumAvatar++;
            return"You did ✂️ and %s did ✊\n";
        }
        else if(Objects.equals(pl, "s") && Objects.equals(av, "p")){
            sumPlayer++;
            return"You did ✂️ and %s did ✋. +1 point to You!\n";
        }
        else if(Objects.equals(pl, "p") && Objects.equals(av, "r")){
            sumPlayer++;
            return"You did ✋ and %s did ✊. +1 point to You!\n";
        }
        else if(Objects.equals(pl, "p") && Objects.equals(av, "s")){
            sumAvatar++;
            return"You did ✋ and %s did ✂️\n";
        }
        return "smth went wrong. no points!\n";
    }
}
