package Create;

import Components.Games.Game;
import Components.Games.League_NBA;
import Components.Team.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Menu {

    private ArrayList<Team> teamList;
    private Team userTeam;
    private League_NBA league;

    public Menu(String option) {
        this.teamList = new ArrayList<>();
        if ("1".equals(option)) {
            startFromFile();
        }
        chooseTeam();
        startLeague();
    }

    public void startFromFile() {
        FileReader fileReader = new FileReader();
        this.teamList = fileReader.execute();
    }


    /**
     * Prompts the user which team they would like to monitor
     */
    public void chooseTeam() {
        for (Team team : this.teamList) {
            System.out.println(team);
        }
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!GameInputs.Team.LAKERS_SET.contains(input) && !GameInputs.Team.WARRIORS_SET.contains(input) &&
                !GameInputs.Team.SIXERS_SET.contains(input) && !GameInputs.Team.CELTICS_SET.contains(input) &&
                !GameInputs.Team.NUGGETS_SET.contains(input) && !GameInputs.Team.SUNS_SET.contains(input) &&
                !GameInputs.Team.CLIPPERS_SET.contains(input) && !GameInputs.Team.TIMBERWOLVES_SET.contains(input) &&
                !GameInputs.Team.HEAT_SET.contains(input) && !GameInputs.Team.MAVERICKS_SET.contains(input) &&
                !GameInputs.Team.BUCKS_SET.contains(input)) {

            System.out.println("Enter team you wish to monitor");
            System.out.print("- ");
            input = scanner.nextLine();

            if (!GameInputs.Team.LAKERS_SET.contains(input) && !GameInputs.Team.WARRIORS_SET.contains(input) &&
                    !GameInputs.Team.SIXERS_SET.contains(input) && !GameInputs.Team.CELTICS_SET.contains(input) &&
                    !GameInputs.Team.NUGGETS_SET.contains(input) && !GameInputs.Team.SUNS_SET.contains(input) &&
                    !GameInputs.Team.CLIPPERS_SET.contains(input) && !GameInputs.Team.TIMBERWOLVES_SET.contains(input) &&
                    !GameInputs.Team.HEAT_SET.contains(input) && !GameInputs.Team.MAVERICKS_SET.contains(input) &&
                    !GameInputs.Team.BUCKS_SET.contains(input)) {

                System.out.println("Error: Enter correct team name");
                System.out.println(" ");
            }
        }

        if (GameInputs.Team.LAKERS_SET.contains(input)) {
            this.userTeam = findTeam("Lakers");
        } else if (GameInputs.Team.WARRIORS_SET.contains(input)) {
            this.userTeam = findTeam("Warriors");
        } else if (GameInputs.Team.SIXERS_SET.contains(input)) {
            this.userTeam = findTeam("Sixers");
        } else if (GameInputs.Team.CELTICS_SET.contains(input)) {
            this.userTeam = findTeam("Celtics");
        } else if (GameInputs.Team.NUGGETS_SET.contains(input)) {
            this.userTeam = findTeam("Nuggets");
        } else if (GameInputs.Team.SUNS_SET.contains(input)) {
            this.userTeam = findTeam("Suns");
        } else if (GameInputs.Team.CLIPPERS_SET.contains(input)) {
            this.userTeam = findTeam("Clippers");
        } else if (GameInputs.Team.TIMBERWOLVES_SET.contains(input)) {
            this.userTeam = findTeam("Timberwolves");
        } else if (GameInputs.Team.HEAT_SET.contains(input)) {
            this.userTeam = findTeam("Heat");
        } else if (GameInputs.Team.MAVERICKS_SET.contains(input)) {
            this.userTeam = findTeam("Mavericks");
        } else {
            // Default to Bucks if none of the above conditions are met
            this.userTeam = findTeam("Bucks");
        }
    }
    /**
     * Finds the team in teamList that the user chose to monitor
     * @param name Name of chosen team
     * @return Chosen team
     */
    public Team findTeam(String name){
        for(Team team : this.teamList){
            if(team.getName().equals(name)){
                return team;
            }
        }
        return null;
    }

    /**
     * Print out desired games from current season
     */
    public void printGames(){
        Scanner scanner;
        scanner = new Scanner(System.in);
        String input = "";
        HashMap<Integer, Game> gameMap = league.buildMap(this.userTeam);
        Set<String> validInputSet = new HashSet<>(Arrays.asList("done", "Done", "d", "D"));
        while(!validInputSet.contains(input)){
            System.out.println("Enter which game you would like to see the box score of (enter done/d to exit)");
            System.out.print("- ");
            input = scanner.nextLine();
            if(!validInputSet.contains(input)){
                try{
                    if(!gameMap.containsKey(Integer.parseInt(input))){
                        System.out.println("ERROR: Enter a valid game number");
                        System.out.println(" ");
                    } else{
                        System.out.println(" ");
                        Game game = gameMap.get(Integer.parseInt(input));
                        game.showCompleteResults();
                    }
                }catch (NumberFormatException e){
                    System.out.println("ERROR: Enter only a number");
                    System.out.println(" ");
                }
            }
        }
        System.out.println(" ");
    }

    /**
     * Prompt user if they would like to see the games of their chosen team
     * @return If user wants to see games or not
     */
    public boolean seeUserGames(){
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Set<String> yesInputSet = new HashSet<>(Arrays.asList("yes", "y", "Yes", "Y"));
        Set<String> noInputSet = new HashSet<>(Arrays.asList("no", "No", "n", "N"));
        while(!yesInputSet.contains(input) && !noInputSet.contains(input)){
            System.out.printf("Would you like to see the box scores from the (%s) schedule (Y/N)?%n",
                    this.userTeam.getName());
            System.out.print("- ");
            input = scanner.nextLine();
            if(!yesInputSet.contains(input) && !noInputSet.contains(input)){
                System.out.println("ERROR: You must enter Y/N");
                System.out.println(" ");
            }
        }
        System.out.println(" ");
        return yesInputSet.contains(input);
    }

    /**
     * Prompt user if they would like to simulate another season
     * @return If user wants to simulate another season or not
     */
    public boolean newSeasonPrompt(){
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Set<String> yesInputSet = new HashSet<>(Arrays.asList("yes", "y", "Yes", "Y"));
        Set<String> noInputSet = new HashSet<>(Arrays.asList("no", "No", "n", "N"));
        while(!yesInputSet.contains(input) && !noInputSet.contains(input)){
            System.out.println("Would you like to follow along with another season (Y/N)?");
            System.out.print("- ");
            input = scanner.nextLine();
            if(!yesInputSet.contains(input) && !noInputSet.contains(input)){
                System.out.println("ERROR: You must enter Y/N ");
                System.out.println();
            }
        }
        System.out.println();
        return yesInputSet.contains(input);
    }

    /**
     * Prompt user if they would like to monitor another team
     * @return If user wants to monitor another team or not
     */
    public boolean newTeamPrompt(){
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Set<String> yesInputSet = new HashSet<>(Arrays.asList("yes", "y", "Yes", "Y"));
        Set<String> noInputSet = new HashSet<>(Arrays.asList("no", "No", "n", "N"));
        while(!yesInputSet.contains(input) && !noInputSet.contains(input)){
            System.out.println("Would you like to follow along with a different team this season (Y/N)?");
            System.out.print("- ");
            input = scanner.nextLine();
            if(!yesInputSet.contains(input) && !noInputSet.contains(input)){
                System.out.println("ERROR: You must enter Y/N ");
                System.out.println();
            }
        }
        System.out.println();
        return yesInputSet.contains(input);
    }

    /**
     * Starts league and simulates
     */
    public void startLeague(){
        this.league = new League_NBA(this.teamList);
        boolean runLeague = true;
        while (runLeague){
            league.simulateNewSeason();
            if(seeUserGames()){
                printGames();
            }
            if(newSeasonPrompt()){
                if (newTeamPrompt()) {
                    chooseTeam();
                }
                league.addYear();
            } else{
                runLeague = false;
            }
        }
        System.out.println("Shutting Down");
    }
}
