package Components.Games;

import Components.Player.Player;

public class SeasonLeader {

    private Player player; // Holds the player who led in a specific season category
    private double amount; // The statistical amount that the player led with
    private GameEnums.LeaderType type; // The category in which the player led (e.g., points, rebounds)

    // Constructor: Creates a new SeasonLeader with specified player, amount, and type
    public SeasonLeader(Player player, double amount, GameEnums.LeaderType type) {
        this.player = player;
        this.amount = amount;
        this.type = type;
    }

    // Overloaded constructor for handling integer amounts (e.g., total threes made)
    public SeasonLeader(Player player, int threes, GameEnums.LeaderType leaderType) {
        this.player = player;
        this.amount = threes;
        this.type = leaderType;
    }

    // Returns the player who is the season leader
    public Player getPlayer() {
        return player;
    }

    // Generates a string representation of the season leader, including player name, team, and stats
    @Override
    public String toString() {
        String header;
        switch (this.type) {
            case THREES:
                header = "--- Leader in Threes Made During Season ---\n";
                break;
            default:
                header = String.format("--- Leader in %s Per Game ---\n", this.type.toString());
                break;
        }
        return header + String.format("--- %s (%s) - %s ---\n",
                this.player.getName(),
                this.player.getTeam().getName(),
                this.amount);
    }
}

