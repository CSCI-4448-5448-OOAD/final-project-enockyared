package Components.Simulations;

import Components.Player.Player;
import Components.Team.Team;

public class TeamTotals {

    // Fields
    private Team team; // --- Team whose totals are calculated
    private int passingAverage = 0; // --- Average passing rating of the team
    private int outsideDefenseAverage = 0; // --- Average outside defense rating of the team
    private int insideDefenseAverage = 0; // --- Average inside defense rating of the team
    private int reboundingAverage = 0; // --- Average rebounding rating of the team
    private int outsideScoringAverage = 0; // --- Average outside scoring rating of the team
    private int insideScoringAverage = 0; // --- Average inside scoring rating of the team

    // Constructor
    public TeamTotals(Team team){
        this.team = team;
        for(Player player : team.getRoster()){
            passingAverage += player.getRating().getPassingRating();
            outsideDefenseAverage += player.getRating().getOutsideDefenseRating();
            insideDefenseAverage += player.getRating().getInsideDefenseRating();
            reboundingAverage += player.getRating().getReboundRating();
            outsideScoringAverage += player.getRating().getOutsideShotRating();
            insideScoringAverage += player.getRating().getInsideShotRating();
        }
        int rosterSize = team.getRoster().size();
        passingAverage = passingAverage/rosterSize;
        outsideDefenseAverage = outsideDefenseAverage/rosterSize;
        insideDefenseAverage = insideDefenseAverage/rosterSize;
        reboundingAverage = reboundingAverage/rosterSize;
        outsideScoringAverage = outsideScoringAverage/rosterSize;
        insideScoringAverage = insideScoringAverage/rosterSize;
    }

    // Getter for team
    public Team getTeam() {
        return team;
    }

    // Getter for passing average
    public int getPassingAverage() {
        return passingAverage;
    }

    // Getter for outside defense
    public int getOutsideDefenseAverage() {
        return outsideDefenseAverage;
    }

    // Getter for inside defense
    public int getInsideDefenseAverage() {
        return insideDefenseAverage;
    }

    // Getter for rebounding average
    public int getReboundingAverage() {
        return reboundingAverage;
    }

    // Getter for outside scoring average
    public int getOutsideScoringAverage() {
        return outsideScoringAverage;
    }

    // Getter for inside scoring average
    public int getInsideScoringAverage() {
        return insideScoringAverage;
    }
}

