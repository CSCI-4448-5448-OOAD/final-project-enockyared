package Components.Simulations;

import Components.Player.Player;

import java.util.Objects;

public class PlayerStats {

    // Fields
    private Player player; // The player whose statistics are being tracked
    private int totalPoints = 0; // Total points scored by the player
    private int threePointers = 0; // Total three-pointers made by the player
    private int fieldGoals = 0; // Total field goals made by the player
    private int rebounds = 0; // Total rebounds grabbed by the player
    private int assists = 0; // Total assists made by the player

    // Constructor
    public PlayerStats(Player player, int threePointers, int twoPointers, int rebounds, int assists) {
        this.player = player;
        this.totalPoints = ((threePointers * 3) + (twoPointers * 2));
        this.threePointers = threePointers;
        this.fieldGoals = threePointers + twoPointers;
        this.rebounds = rebounds;
        this.assists = assists;
    }

    // Getter for three-pointers
    public int getThreePointers() {
        return threePointers;
    }

    // Getter for field goals
    public int getFieldGoals() {
        return fieldGoals;
    }

    // Getter for rebounds
    public int getRebounds() {
        return rebounds;
    }

    // Getter for assists
    public int getAssists() {
        return assists;
    }

    // Getter for total points scored
    public int getTotalPoints() {
        return totalPoints;
    }

    // Update total points scored by the player
    public void updatePoints(int totalPoints) {
        this.totalPoints += totalPoints;
    }

    // Print player stats as a string
    @Override
    public String toString() {
        return "PlayerStats{" +
                "NAME=" + player.getName() +
                "--- 3PA=" + totalPoints +
                "--- 3PM=" + threePointers +
                "--- FG=" + fieldGoals +
                "--- REB=" + rebounds +
                "--- AST=" + assists +
                '}';
    }

    // Getter for the player associated with these stats
    public Player getPlayer() {
        return player;
    }

    // Equals method for player stats
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerStats that = (PlayerStats) o;
        return totalPoints == that.totalPoints &&
                threePointers == that.threePointers &&
                fieldGoals == that.fieldGoals &&
                rebounds == that.rebounds &&
                assists == that.assists &&
                Objects.equals(player, that.player);
    }

    // Hash method for player stats
    @Override
    public int hashCode() {
        return Objects.hash(player, totalPoints, threePointers, fieldGoals, rebounds, assists);
    }
}

