package ua.milly.utils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
//make it a static class? we are not really having multiple avatars
public class Avatar {
    //Attributes
    public static String AvatarName;
    static final private Scanner input = new Scanner(System.in);
    private static final String[] avs = {"42", "Ankylosaurus", "Stegosaurus", "Triceratops", "Small dino", "GLaDOS"};
    private static final String AvatarMenu = """
             -----------------
            | 1) Ankylosaurus
            | 2) Stegosaurus
            | 3) Triceratops
            | 4) Smol dino
            | 5) GLaDOS
            | 9) I'm fine with my friend!
             -----------------
            """;

    // Methods
    public static void setAvatar(){
         int choice;
         boolean choosing = true;
         while (choosing){
             System.out.println(AvatarMenu);
             choice = getChoice();
             if (choice == 0 || choice == 42){
                 getASCII_Art(avs[0]);
                 choice = 0; //if u type 42, then everything will break!!
                 System.out.print("₳ⱤɆ ɎØɄ ₴ɄⱤɆ ɎØɄ ₩₳₦₮ ₮Ø ₱Ⱡ₳Ɏ ₩ł₮Ⱨ \uD835\uDFF0\uD835\uDFEE ? [Ɏ/₦]");
             }
             else if (choice == 9){
                 break;
             }
             else if (!(choice >= 0 && choice < avs.length)){ //there is a NOT in front of the condition
                 System.out.println("There is no friend like this :< ");
                 continue;
             }
             else{
                 getASCII_Art(avs[choice]);
                 System.out.printf("Are you sure you want to play with %s ? [Y/N]",avs[choice]);

             }

             input.nextLine();
             String answer = input.nextLine().trim().toLowerCase();
             if (answer.equals("y")){
                 AvatarName = avs[choice];
                 choosing = false;
             }

        }
         System.out.printf("You chose to play with %s!", AvatarName);
     }


    public static void getASCII_Art(String avatarName){//renamed to a more descriptive function name
        Path baseFolder = Path.of("task_01/resource");
        Path path = baseFolder.resolve("avatar_" + avatarName + ".txt");
        try{
            String ASCIIArt = Files.readString(path); //could store it as an attribute and then getASCII_Art it if the avatar wins
            System.out.println(ASCIIArt);
        }
        catch (Exception e) {
            System.err.println("Error finding the avatar :(");
        }
    }


    private static int getChoice(){
        int choice = -1;
        while(choice < 0) {
            System.out.print("With whom do you want to play? ");
            try {//try-catch blocks for all inputs??
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println("Please, write an integer :[");
                input.nextLine();
            }
        }
        return choice;
    }
}
