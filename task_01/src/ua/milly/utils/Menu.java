package ua.milly.utils;
import java.util.Scanner;
import java.util.Map;
import ua.milly.games.DiceGame;
import ua.milly.games.TicTacToe;
import ua.milly.games.GameRock;

public class Menu {
    private final static Scanner input = new Scanner(System.in);
    public void run(){ 
        Avatar.AvatarName = "Small dino";
        Map<String, Runnable> actions = Map.of(

            "0", () -> Avatar.setAvatar(),
            "1", () -> Avatar.getASCII_Art(Avatar.AvatarName),
            "2", Today::print,
            "3a", () -> new GameRock().play(),
            "3b", () -> {System.out.println("Press enter to start.");new DiceGame().Start();},
            "3c", () -> new TicTacToe().play(),
            "m", this::printMenu,
            "q", () -> System.out.println("Quitting...")
        );
        getChoice(actions);
    }

    private void getChoice(Map<String, Runnable> actions){
        printMenu(); //Print the menu each time we ask for an input
        System.out.print("Choose: ");
        String choice = input.nextLine().trim().toLowerCase();

        actions.getOrDefault(choice,
        () -> System.out.println("Wrong input. Please, try again"))
        .run();

        if (!choice.equals("q")) {
            getChoice(actions);
        }
    }



    private void printMenu(){
        String menuText = """
             -----------------
            | 0) Choose a friend to play!
            | 1) Show an avatar
            
                 G A M E S
            | 3A) Scissors Paper Stone!
            | 3B) Play Dice
            | 3C) Play TicTacToe
            
                 E X T R A
            | 2) What's the time?
            | m) Print menu
            | q) Quit
             -----------------""";

        System.out.println(menuText);

    }

}
