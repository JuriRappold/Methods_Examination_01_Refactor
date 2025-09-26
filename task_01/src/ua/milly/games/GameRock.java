package ua.milly.games;
import ua.milly.utils.Avatar;
import ua.milly.utils.Game_Options;

import java.security.SecureRandom;
import java.util.*;
import java.util.function.UnaryOperator;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.stream.Collectors;

public class GameRock {
    private SecureRandom randomNumbers = new SecureRandom();
    private Scanner input = new Scanner(System.in);
    private boolean playing = true;
    private int sumPlayer = 0;
    private int sumAvatar = 0;

    private String playerTurn = """
                    Score: You %d - %s %d
                    Select rock ✊ [r], scissor ✂️ [s], paper ✋ [p] or quit [q]:
                    """;

    private final String[] valid_inputs = {"r", "p", "s", "q"};

    private Dictionary<String, Game_Options> choices = new Hashtable<>();
    private void populate_dictionary(){
        choices.put("r",new Game_Options("r","rock","✊"));
        choices.put("s",new Game_Options("s", "scissors", "✂️"));
        choices.put("p",new Game_Options("p", "paper","✋"));
    }

    private final UnaryOperator<String> map_random_to_choice = i -> switch (i) {//
        case "1" -> "r";
        case "2" -> "s";
        case "3" -> "p";
        case null, default -> "bad";
    };




    // validates input of player
    private boolean validate_input(String in){
         return Arrays.stream(valid_inputs).anyMatch(v -> v.equals(in.toLowerCase().trim()));
    }

    //generates choice for the avatar
    private String choice_avatar(){
        //     | generates a random number between 1 & 3 (inclusive) as an IntStream |
        return randomNumbers.ints(1,1,4)
                            .mapToObj(String::valueOf)
                            .map(map_random_to_choice)
                            .collect(Collectors.joining());
    }

    //Asks for choice & validates it
    private String choice_player(){
        System.out.printf(playerTurn, sumPlayer, Avatar.AvatarName, sumAvatar);
        String choice = input.nextLine();
        if(validate_input(choice)){
            return choice.toLowerCase().trim();
        } else {
            throw new RuntimeException();
        }

    }

    private String compare_choices(String p, String a){
        int p_int = p.hashCode();
        int a_int = a.hashCode();
        String resultString = "\nYou did %s and %s did %s. +1 point to %s";

        if(p_int == a_int){//unentschieden
            return String.format("It is a tie!! You both had %s", choices.get(p).icon());
        }
        else if (p_int == 114) {//player has rock
            if(a_int == 115){//avatar has scissors
                //player wins with rocks
                sumPlayer++;
                return String.format(resultString, choices.get(p).icon(), Avatar.AvatarName, choices.get(a).icon(), "You");
            }
            else {//avatar wins w/ paper
                sumAvatar++;
                return String.format(resultString, choices.get(p).icon(), Avatar.AvatarName, choices.get(a).icon(), Avatar.AvatarName);
            }
        }
        else if (p_int == 112) {
            if(a_int == 114){
                sumPlayer++;
                return String.format(resultString, choices.get(p).icon(), Avatar.AvatarName, choices.get(a).icon(), "You");
            }
            else{
                sumAvatar++;
                return String.format(resultString, choices.get(p).icon(), Avatar.AvatarName, choices.get(a).icon(), Avatar.AvatarName);
            }

        }
        else{
            if(a_int == 112){//avatar has paper
                //player wins w/ scissors
                sumPlayer++;
                return String.format(resultString, choices.get(p).icon(), Avatar.AvatarName, choices.get(a).icon(), "You");
            }
            else{
                //avatar wins w/ scissors
                sumAvatar++;
                return String.format(resultString, choices.get(p).icon(), Avatar.AvatarName, choices.get(a).icon(), Avatar.AvatarName);
            }
        }
    }

    public void play(){
        populate_dictionary();
        String helloMessage = """
                Welcome to a game of rock ✊, scissor ✂️, paper ✋!
                You will play against %s and we keep score.
                """;
        System.out.printf(helloMessage, Avatar.AvatarName);
        do{
            String player_choice="";
            try {
                player_choice = choice_player();
                if(player_choice.equals("q")){
                    playing = false;
                }
                else{
                    System.out.println(compare_choices(player_choice, choice_avatar()));
                }
            } catch (RuntimeException e) {
                System.out.println("Not a valid input. Please try again");

            }
        } while (playing);
    }

}

