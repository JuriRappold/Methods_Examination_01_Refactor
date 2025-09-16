package games;
import utils.Avatar;

import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissor {
    Scanner input = new Scanner(System.in);
    Random r = new Random();
    boolean playing = true;
    int sumPlayer = 0;
    int sumAvatar = 0;

    public void play(){
        String helloMessage= """
                Welcome to a game of rock ✊, scissor ✂️, paper ✋!
                You will play against %s and we keep score. %n
                """;
        System.out.printf(helloMessage,Avatar.AvatarName);


        String playerTurn = """
                    Score: You %d - %s %d
                    Select rock ✊ [r], scissor ✂️ [s], paper ✋ [p] or quit [q]:
                    """;
        while(playing){
            System.out.printf(playerTurn, sumPlayer, Avatar.AvatarName, sumAvatar);
            String choicePlayer = input.nextLine().toLowerCase().trim();
            String choiceAvatar = ChoiceAvatar();
            if(choicePlayer.equals("r")|| choicePlayer.equals("s")||choicePlayer.equals("p")){
                System.out.printf(calculateWinner(choicePlayer,choiceAvatar),Avatar.AvatarName);
            }
            else if(choicePlayer.equals("q")) {
                playing = false;
            }
            else{
                    System.out.println("Wrong input. Please, write [s,q,p]: ");
            }
        }
    }

    private String ChoiceAvatar(){
        int choice = r.nextInt(3);
        switch (choice){
            case 0 -> {return "r";}
            case 1 -> {return "s";}
            default -> {return "p";}
        }

    }
// Rock, Scissor, Paper
    private String calculateWinner(String pl, String av){
        if (Objects.equals(pl, av)){return"Wow.. That's a tie!\n";}
        else if(Objects.equals(pl, "r") && Objects.equals(av, "s")){sumPlayer++;return"You did ✊ and %s did ✂️. +1 point to You!\n";}
        else if(Objects.equals(pl, "r") && Objects.equals(av, "p")){sumAvatar++;return"You did ✊ and %s did ✋\n";}
        else if(Objects.equals(pl, "s") && Objects.equals(av, "r")){sumAvatar++;return"You did ✂️ and %s did ✊\n";}
        else if(Objects.equals(pl, "s") && Objects.equals(av, "p")){sumPlayer++;return"You did ✂️ and %s did ✋. +1 point to You!\n";}
        else if(Objects.equals(pl, "p") && Objects.equals(av, "r")){sumPlayer++;return"You did ✋ and %s did ✊. +1 point to You!\n";}
        else if(Objects.equals(pl, "p") && Objects.equals(av, "s")){sumAvatar++;return"You did ✋ and %s did ✂️\n";}
        return "smth went wrong. no points!\n";
    }
}
