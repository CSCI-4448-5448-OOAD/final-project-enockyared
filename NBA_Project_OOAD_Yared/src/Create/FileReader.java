package Create;

import Components.Player.Player;
import Components.Player.Position;
import Components.Player.Role;
import Components.Player.Type;
import Components.Team.Team;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;
import java.awt.*;
import java.util.*;

public class FileReader {

    /** Collection of teams created from the file */
    private ArrayList<Team> teams;
    /** Team currently being assembled */
    private Team workingTeam;

    /**
     * Initializes a FileReader instance
     */
    public FileReader() {
        teams = new ArrayList<>();
    }

    /**
     * Interactively chooses a file
     * @return String path of the selected file
     */
    public String selectFile() {
        FileDialog fileChooser = new FileDialog((Frame)null, "Choose File");
        fileChooser.setMode(FileDialog.LOAD);
        fileChooser.setVisible(true);
        return fileChooser.getDirectory() + fileChooser.getFile();
    }

    /**
     * Verifies the structure of a line in the file
     * @param lineComponents Components of a line
     * @return True if the format is correct
     */
    public boolean isLineFormatCorrect(ArrayList<String> lineComponents) {
        boolean isValid = false;
        switch (lineComponents.get(0)) {
            case "----------------------------------------":
            case "**********************************************":
                isValid = lineComponents.size() == 1;
                break;
            case "Team":
                if (lineComponents.size() == 3) {
                    String teamName = lineComponents.get(1);
                    if (GameInputs.Team.isValidTeamName(teamName)) {
                        try {
                            Integer.parseInt(lineComponents.get(2));
                            isValid = true;
                        } catch (NumberFormatException ignore) {
                        }
                    }
                }
                break;
            case "Player":
                if(lineComponents.size() == 6){ //line size must be 5
                    //second element must be player age
                    try {
                        Integer.parseInt(lineComponents.get(2));
                        if(GameInputs.Player.TYPE_SET.contains(lineComponents.get(3))){ //valid type
                            if(GameInputs.Player.ROLE_SET.contains(lineComponents.get(4))){ //valid role
                                if(GameInputs.Player.POSITION_SET.contains(lineComponents.get(5))){ //valid position
                                    isValid = true;
                                }
                            }
                        }
                    } catch (NumberFormatException ignored){
                    }
                }
                break;
        }
        if (!isValid) {
            System.out.println("File read error at: " + lineComponents);
        }
        return isValid;
    }

    /**
     * Checks the overall structure of the file
     * @return True if the file structure is correct
     */
    public boolean isFileStructureValid(List<String> fileLines) {
        int lineCounter = 0;
        boolean isTeamOpen = false;
        boolean isFileValid = false;

        for (int currentLineNumber = 0; currentLineNumber < fileLines.size(); currentLineNumber++) {
            String line = fileLines.get(currentLineNumber);
            ArrayList<String> lineParts = new ArrayList<>(Arrays.asList(line.split(",")));

            if (isLineFormatCorrect(lineParts)) {
                String firstElement = lineParts.get(0);
                lineCounter++;
                if (firstElement.equals("----------------------------------------")) {
                    lineCounter = 0;
                    isTeamOpen = true;
                } else if (firstElement.equals("**********************************************")) {
                    if (lineCounter == 11) {
                        isTeamOpen = false;
                        isFileValid = true;
                    } else {
                        System.out.printf("Line %s error: Incorrect team data%n", currentLineNumber + 1);
                        return false;
                    }
                } else if (lineCounter == 1 && !firstElement.equals("Team")) {
                    System.out.printf("Line %s error: Expected 'Team'%n", currentLineNumber + 1);
                    return false;
                } else if (lineCounter >= 2 && lineCounter <= 10 && !firstElement.equals("Player")) {
                    System.out.printf("Line %s error: Expected 'Player'%n", currentLineNumber + 1);
                    return false;
                }
            } else {
                System.out.printf("Unexpected error at line %s%n", currentLineNumber + 1);
                return false;
            }
        }

        if (isTeamOpen) {
            System.out.println("Incomplete team detected, missing the Ending Team marker");
            return false;
        }

        return isFileValid;
    }


    /**
     * Constructs a league from the file data
     */
    public void constructLeague(List<String> fileLines) {
        for (String dataLine : fileLines) {
            ArrayList<String> lineItems = new ArrayList<>(Arrays.asList(dataLine.split(",")));
            String lineType = lineItems.get(0);

            switch (lineType) {
                case "Team":
                    workingTeam = new Team(lineItems.get(1), Integer.parseInt(lineItems.get(2)));
                    break;
                case "Player":
                    Player newPlayer = new Player(
                            lineItems.get(1),
                            Integer.parseInt(lineItems.get(2)),
                            Type.valueOf(lineItems.get(3)),
                            Role.valueOf(lineItems.get(4)),
                            Position.valueOf(lineItems.get(5))
                    );
                    workingTeam.addPlayer(newPlayer);
                    break;
                case "**********************************************":
                    teams.add(workingTeam);
                    break;
            }
        }
    }


    /**
     * Processes a file to check its format and build teams
     * @param filePath Path of the file to process
     */
    public void processFile(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            if (isFileStructureValid(lines)) {
                constructLeague(lines);
            } else {
                System.out.println("Correct the file errors and restart the program.");
                System.exit(0);
            }
        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        }
    }

    /**
     * Starts the file processing and team building
     * @return List of teams
     */
    public ArrayList<Team> execute() {
        System.out.println("Select a text file containing team data.");
        System.out.println("Example file located in this directory");
        System.out.println("Press any key to proceed.");
        System.out.print(" ");
        new Scanner(System.in).nextLine();
        String filePath = selectFile();
        processFile(filePath);
        return teams;
    }
}



