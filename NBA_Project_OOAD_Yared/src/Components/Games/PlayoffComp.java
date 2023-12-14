package Components.Games;

import Components.Team.Team;

public class PlayoffComp {

    private final Team winner; // Stores the team that won the playoff round
    private final int winnerWins; // Stores the number of wins by the winning team
    private final Team loser; // Stores the team that lost the playoff round
    private final int loserWins; // Stores the number of wins by the losing team
    private final GameEnums.RoundType roundType; // Indicates the type of playoff round

    // Constructor: Initializes a playoff competition with the specified teams, wins, and round type
    public PlayoffComp(Team winner, int winnerWins, Team loser, int loserWins, GameEnums.RoundType roundType) {
        this.winner = winner;
        this.winnerWins = winnerWins;
        this.loser = loser;
        this.loserWins = loserWins;
        this.roundType = roundType;
    }

    // Returns the winning team of the playoff round
    public Team getWinner() {
        return winner;
    }

    // Generates a string representation of the playoff competition results
    @Override
    public String toString() {
        // First, create the strings for each part of the output
        String header = String.format("--- %s Playoff Results ---", roundType);
        String result = String.format("--- Winner: %s (%s-%s) over %s (%s-%s) ---",
                winner.getName(), winnerWins, loserWins, loser.getName(), loserWins, winnerWins);

        // Determine the length of the longest line for the border
        int maxLength = Math.max(header.length(), result.length());

        // Build the boxed output
        StringBuilder boxedOutput = new StringBuilder();
        boxedOutput.append("-".repeat(maxLength)).append("\n"); // Top border
        boxedOutput.append(header).append("\n");
        boxedOutput.append(result).append("\n");
        boxedOutput.append("-".repeat(maxLength)).append("\n"); // Bottom border

        return boxedOutput.toString();
    }
}

