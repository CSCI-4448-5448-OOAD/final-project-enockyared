package Components.Team;

import Components.Player.Player;
import Components.Player.Role;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a basketball team, holding information about players, team statistics, and achievements.
 */
public class Team {

    private String name;
    private int wins;
    private int losses;
    private ArrayList<Player> roster;
    private TeamType type;
    private int championships;

    /**
     * Initializes a team with a name and default values.
     *
     * @param name The name of the team
     */
    public Team(String name) {
        this.name = name;
        this.wins = 0;
        this.losses = 0;
        this.roster = new ArrayList<>();
        this.championships = 0;
    }

    /**
     * Initializes a team with a name and championship count.
     *
     * @param name          The name of the team
     * @param championships Number of championships won
     */
    public Team(String name, int championships) {
        this(name);
        this.championships = championships;
    }

    /**
     * Initializes a team with a name and a specific type.
     *
     * @param name The name of the team
     * @param type The type of the team
     */
    public Team(String name, TeamType type) {
        this(name);
        this.type = type;
    }

    /**
     * Getter for name
     *
     * @return Name of team
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     *
     * @param name New name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for wins
     *
     * @return Team wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Add a win
     */
    public void incrementWins() {
        wins = wins + 1;
    }

    /**
     * Getter for losses
     *
     * @return Team losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Add a loss
     */
    public void incrementLosses() {
        losses = losses + 1;
    }


    /**
     * Getter for team type
     *
     * @return Type of team
     */
    public TeamType getType() {
        return type;
    }

    /**
     * Updates team type
     */
    public void updateType() {
        int numOfSuper = 0;
        int numOfStar = 0;
        for (Player player : this.roster) {
            if (player.getRole() == Role.Superstar) {
                numOfSuper += 1;
            } else if (player.getRole() == Role.Star) {
                numOfStar += 1;
            }
        }
        if (numOfSuper > 2) { //3+ superstars
            this.type = TeamType.God_team;
        } else if (numOfSuper >= 2 && numOfStar >= 1) { //2+ superstars and 1+ star
            this.type = TeamType.Super_team;
        } else if ((numOfSuper == 1 || numOfSuper == 2) &&
                (numOfStar == 0 || numOfStar == 1 || numOfStar == 2)) { //1-2 superstars and 0-1 stars
            this.type = TeamType.Contending_team;
        } else if (numOfStar == 1 || numOfStar == 2) { //1 or 2 stars
            this.type = TeamType.Playoff_team;
        } else { //pure starters
            this.type = TeamType.Average_team;
        }
    }

    /**
     * Getter for team's championships
     *
     * @return Number of championships
     */
    public int getChampionships() {
        return championships;
    }

    /**
     * Increase amount of championships a team has won
     */
    public void addChampionship() {
        this.championships += 1;
    }

    /**
     * Getter for team roster
     *
     * @return Team roster
     */
    public ArrayList<Player> getRoster() {
        return roster;
    }

    /**
     * Add a player to roster
     *
     * @param player Player to be added
     */
    public void addPlayer(Player player) {
        roster.add(player);
        player.setTeam(this);
        this.updateType();
    }

    /**
     * Add multiple players to a team
     *
     * @param players Arraylist of players to be added
     */
    public void addMultiplePlayers(ArrayList<Player> players) {
        for (Player player : players) {
            addPlayer(player);
        }
    }

    /**
     * Remove a player from roster
     *
     * @param player Player to be removed
     */
    public void removePlayer(Player player) {
        roster.remove(player);
        player.setTeam(null);
    }

    /**
     * Equals method for Team
     *
     * @param o Team to be compared
     * @return If same team or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return wins == team.wins &&
                losses == team.losses &&
                Objects.equals(name, team.name) &&
                Objects.equals(type, team.type) &&
                Objects.equals(championships, team.championships) &&
                Objects.equals(roster, team.roster);
    }

    public int calculateLongestNameLength() {
        int largest = this.roster.get(0).getName().length();
        for (Player player : this.roster) {
            if (player.getName().length() > largest) {
                largest = player.getName().length();
            }
        }
        return largest + 5;
    }

    /**
     * Hash method for team
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, wins, losses, type, roster, championships);
    }

    /**
     * Converts the team and its roster to a string representation.
     *
     * @return A string representation of the team
     */
    @Override
    public String toString() {
        StringBuilder teamDetails = new StringBuilder();
        int longestNameLength = calculateLongestNameLength();

        // Calculate the width of the widest line
        String header = String.format("--- %s ---" + "%" + (longestNameLength + 1) + "s" + " %22s %17s %5s %5s %6s %6s %12s %10s",
                "---Name---", "Role---", "Type---", "Position---", "---3P", "---2P", "---Rebounds", "---Assists", "---Interior Defense", "---Perimeter Defense");
        int lineLength = header.length() + 20; // Adjust 20 as needed for spacing

        // Top border
        teamDetails.append("-".repeat(lineLength)).append("\n");
        teamDetails.append(String.format("--- Team: %s ---\n", this.name));
        teamDetails.append(String.format("--- Team Classification: %s ---\n", this.type));
        teamDetails.append(String.format("--- Total Championships: %s ---\n", championships));
        teamDetails.append("-".repeat(lineLength)).append("\n");

        // Header
        teamDetails.append(header).append("\n");
        teamDetails.append("-".repeat(lineLength)).append("\n");

        // Player details
        for (Player player : roster) {
            int spacing = longestNameLength - player.getName().length();
            String playerDetails = String.format("--- %s ---" + "%" + (spacing + 5) + "s" + " %22s %17s %5s %5s %6s %6s %12s %10s",
                    player.getName(), player.getRole(), player.getType(), player.getPosition(),
                    player.getRating().getOutsideShotRating(), player.getRating().getInsideShotRating(),
                    player.getRating().getReboundRating(), player.getRating().getPassingRating(),
                    player.getRating().getInsideDefenseRating(), player.getRating().getOutsideDefenseRating());

            teamDetails.append(playerDetails).append("\n");
            teamDetails.append("-".repeat(lineLength)).append("\n");
        }

        // Bottom border
        teamDetails.append("-".repeat(lineLength)).append("\n");

        return teamDetails.toString();
    }
}


