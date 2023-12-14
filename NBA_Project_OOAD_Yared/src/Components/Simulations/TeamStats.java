package Components.Simulations;

import Components.Team.Team;

import java.util.ArrayList;
import java.util.Objects;

public class TeamStats {

    // Fields
    private final Team team; // --- Team whose statistics are being tracked
    private int score; // --- Total points scored by the team
    private int fieldGoals; // --- Total field goals made by the team (twos + threes)
    private int threePointers; // --- Total three-pointers made by the team
    private int rebounds; // --- Total rebounds grabbed by the team
    private int assists; // --- Total assists made by the team
    private ArrayList<PlayerStats> statsList; // --- List of individual player stat lines for this team's roster

    // Constructor
    public TeamStats(Team team){
        this.team = team;
        this.score = 0;
        this.fieldGoals = 0;
        this.threePointers = 0;
        this.rebounds = 0;
        this.assists = 0;
        statsList = new ArrayList<>();
    }

    // Getter for team
    public Team getTeam() {
        return team;
    }

    // Getter for score
    public int getScore(){
        return score;
    }

    // Updates score
    public void updateScore(int score){
        this.score += score;
    }

    // Getter for field goals
    public int getFieldGoals() {
        return fieldGoals;
    }

    // Updates field goals
    public void updateFieldGoals(int fieldGoals) {
        this.fieldGoals += fieldGoals;
    }

    // Getter for three-pointers
    public int getThreePointers(){
        return threePointers;
    }

    // Updates three-pointers
    public void updateThreePointers(int threePointers) {
        this.threePointers += threePointers;
    }

    // Getter for rebounds
    public int getRebounds() {
        return rebounds;
    }

    // Updates rebounds
    public void updateRebounds(int rebounds) {
        this.rebounds += rebounds;
    }

    // Getter for assists
    public int getAssists() {
        return assists;
    }

    // Updates assists
    public void updateAssists(int assists) {
        this.assists += assists;
    }

    // Updates stats list
    public void updateList(PlayerStats stats){
        statsList.add(stats);
    }

    // Removes a stat from team stats
    public void removeStat(PlayerStats stats){
        statsList.remove(stats);
        score -= stats.getTotalPoints();
        rebounds -= stats.getRebounds();
        assists -= stats.getAssists();
        threePointers -= stats.getThreePointers();
        fieldGoals -= stats.getFieldGoals();
    }

    // Getter for stats list
    public ArrayList<PlayerStats> getStatsList() {
        return statsList;
    }

    // Updates all stats within this object
    public void updateStats(PlayerStats stats){
        updateList(stats);
        updateScore(stats.getTotalPoints());
        updateThreePointers(stats.getThreePointers());
        updateFieldGoals(stats.getFieldGoals());
        updateRebounds(stats.getRebounds());
        updateAssists(stats.getAssists());
    }

    // Equals method for team stats
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamStats teamStats = (TeamStats) o;
        return score == teamStats.score &&
                fieldGoals == teamStats.fieldGoals &&
                threePointers == teamStats.threePointers &&
                rebounds == teamStats.rebounds &&
                assists == teamStats.assists &&
                Objects.equals(team, teamStats.team) &&
                Objects.equals(statsList, teamStats.statsList);
    }

    // Hash method
    @Override
    public int hashCode() {
        return Objects.hash(team, score, fieldGoals, threePointers, rebounds, assists, statsList);
    }

    // Finds the length of the longest player name in stats array
    public int longestName(){
        int largest = statsList.get(0).getPlayer().getName().length();
        for(PlayerStats stats : statsList){
            if(stats.getPlayer().getName().length() > largest){
                largest = stats.getPlayer().getName().length();
            }
        }
        return largest + 5;
    }

    // Generates a box score as a string
    @Override
    public String toString() {
        StringBuilder boxScore = new StringBuilder();
        int longestName = longestName();

        // Calculate the width of the widest line
        int lineLength = longestName + "Name Points Rebounds Assists 3PM FGM".length() + 20; // Adjust 30 as needed for spacing

        // Top border
        boxScore.append("-".repeat(lineLength)).append("\n");
        boxScore.append(String.format("--- %s Box Score ---\n", team.getName()));
        boxScore.append("-".repeat(lineLength)).append("\n");
        boxScore.append(String.format("--- %s %"+longestName+"s %10s %10s %5s %6s\n", "Name", "Points",
                "Rebounds", "Assists", "3PM", "FGM"));

        for (PlayerStats stats : statsList) {
            int spacing = longestName - stats.getPlayer().getName().length();
            boxScore.append("-".repeat(lineLength)).append("\n");
            boxScore.append(String.format("--- %s %"+spacing+"s %8s %11s %9s %5s\n", stats.getPlayer().getName(),
                    stats.getTotalPoints(), stats.getRebounds(), stats.getAssists(),
                    stats.getThreePointers(), stats.getFieldGoals()));
        }

        boxScore.append("-".repeat(lineLength)).append("\n");
        int totalSpacing = longestName - 6;
        boxScore.append(String.format("--- %s %"+totalSpacing+"s %8s %11s %9s %5s\n", "TOTALS", score, rebounds,
                assists, threePointers, fieldGoals));

        // Bottom border
        boxScore.append("-".repeat(lineLength)).append("\n");

        return boxScore.toString();
    }

}

