package Engine;

import Create.Menu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * File that user should run, will prompt and pass execution onto BasketballSimulator
 */
public class GameEngine {

    /**
     * Prompts the user to input their play option
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------- NBA 2K PREDICTIONS --------");
        System.out.println("------- ENOCK YARED -------");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("-------------------------------------");
        System.out.println(" ");
        System.out.println(" Each team has 9 players from the year of 2022");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ------- MAIN MENU ------- ");
        System.out.println("1) Load Live Data from 2022 Roster " );
        System.out.println("2) Create your own League (MyCareer Option)");
        String input = "";
        Set<String> validInputSet = new HashSet<>(Arrays.asList("1", "2")); //set in case more modes are added
        while(!validInputSet.contains(input)){
            System.out.print(" ");
            input = scanner.nextLine();
            if(!validInputSet.contains(input)){
                System.out.println("Error: Enter displayed number as input");
                System.out.println("");
            }
        }
        new Menu(input); //pass operation to Controller
    }
}
