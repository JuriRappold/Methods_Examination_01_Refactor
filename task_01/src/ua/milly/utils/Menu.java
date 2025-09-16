package utils;
import java.util.Scanner;
import games.DiceGame21;
import games.RockPaperScissor;

public class Menu {
    final static private Scanner input = new Scanner(System.in);

    public void run(){
        String choice;
        Avatar.AvatarName = "Small dino";
        printMenu();
        do{
            choice = getChoice();
            switch (choice){
                case "0":
                    Avatar.setAvatar();
                case "1":
                    Avatar.print(Avatar.AvatarName);
                    break;
                case "2":
                    Today.print();
                    break;
                case "3a":
                    RockPaperScissor game1 = new RockPaperScissor();
                    game1.play();
                    break;
                case "3b":
                    DiceGame21 game2 = new DiceGame21();
                    game2.play();
                    break;
                case "m":
                    printMenu();
                    break;
                case "q":
                    System.out.println("okay, okay.. quitting");
                    break;
                default:
                    System.out.println("Wrong input. Please, try again");
            }}
        while (!choice.equals("q"));
    }

    public String getChoice(){
        System.out.print("Choose: ");
        return input.nextLine().trim();
    }

    public void printMenu(){
        String menuText = """
             -----------------
            | 0) Choose a friend to play!
            | 1) Show an avatar
            
                 G A M E S
            | 3A) Scissors Paper Stone!
            | 3B) Play Dice
            
                 E X T R A
            | 2) What's the time?
            | m) Print menu
            | q) Quit
             -----------------""";

        System.out.println(menuText);

    }

}
