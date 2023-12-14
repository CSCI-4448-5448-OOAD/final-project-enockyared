package Components.Games;

import Components.Simulations.GameSimulation;
import Components.Simulations.TeamStats;
import Components.Team.Team;

import java.util.Objects;

/**
 * Manages a basketball game, including team details, scores, and simulation.
 */
public class Game {

    /** The first teams in the game */
    private final Team team1;
    private final Team team2;
    /** Statistical record for the teams */
    private TeamStats team1BoxScore;
    private TeamStats team2BoxScore;
    /** Victor/Loser */
    private Team winner;
    private TeamStats winnerBoxScore;
    private Team loser;
    private TeamStats loserBoxScore;

    /**
     * Constructs a game with two participating teams.
     * @param team1 First  team
     * @param team2 Second  team
     */
    public Game(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
    }

    /**
     * Returns the first team.
     * @return First team
     */
    public Team getTeam1() {
        return team1;
    }

    /**
     * Returns the second team.
     * @return Second team
     */
    public Team getTeam2() {
        return team2;
    }

    /**
     * Returns the box score for the first team.
     * @return Box score of the first team
     */
    public TeamStats getTeam1BoxScore() {
        return team1BoxScore;
    }

    /**
     * Sets the box score for the first team.
     * @param team1BoxScore Box score for the first team
     */
    public void setTeam1BoxScore(TeamStats team1BoxScore) {
        this.team1BoxScore = team1BoxScore;
    }

    /**
     * Returns the box score for the second team.
     * @return Box score of the second team
     */
    public TeamStats getTeam2BoxScore() {
        return team2BoxScore;
    }

    /**
     * Sets the box score for the second team.
     * @param team2BoxScore Box score for the second team
     */
    public void setTeam2BoxScore(TeamStats team2BoxScore) {
        this.team2BoxScore = team2BoxScore;
    }
    /**
     * Updates the winner of the game.
     * @param winner The winning team
     */
    public void setWinner(Team winner) {
        this.winner = winner;
        winner.incrementWins();
        if(winner.equals(team1)){
            winnerBoxScore = team1BoxScore;
            loserBoxScore = team2BoxScore;
            updateLoser(team2);
        } else{
            winnerBoxScore = team2BoxScore;
            loserBoxScore = team1BoxScore;
            updateLoser(team1);
        }
    }

    public Team getWinner() {
        return loser;
    }
    /**
     * Updates the losing team of the game.
     * @param loser The losing team
     */
    public void updateLoser(Team loser) {
        this.loser = loser;
        loser.incrementLosses();
    }

    /**
     * Compares this game to another object for equality.
     * @param o The object to compare with
     * @return True if the games are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(team1, game.team1) &&
                Objects.equals(team2, game.team2) &&
                Objects.equals(team1BoxScore, game.team1BoxScore) &&
                Objects.equals(team2BoxScore, game.team2BoxScore) &&
                Objects.equals(winner, game.winner) &&
                Objects.equals(loser, game.loser);
    }

    /**
     * Generates a hash code for this game.
     * @return The hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(team1, team2, team1BoxScore, team2BoxScore, winner, loser);
    }

    /**
     * Returns a string representation of the game.
     * @return A string describing the game
     */
    @Override
    public String toString() {
        return String.format("Game{team1=%s, team2=%s, team1Score=%d, team2Score=%d, winner=%s, loser=%s}",
                team1.getName(), team2.getName(), team1BoxScore.getScore(), team2BoxScore.getScore(),
                winner.getName(), loser.getName());
    }

    /**
     * Executes the simulation of the game.
     */
    public void play(){
        GameSimulation simulation = new GameSimulation(this);
        simulation.runSimulation();
    }

    /**
     * Displays basic information about the game.
     */
    public void showHeader(){
        String header = String.format("|\n| Matchup: %s vs %s\n| Winning Team: %s (Score: %d - %d)\n|\n",
                team1.getName(), team2.getName(), winner.getName(),
                winnerBoxScore.getScore(), loserBoxScore.getScore());
        System.out.println(header);
    }

    /**
     * Shows detailed results of the game, including box scores.
     */
    public void showCompleteResults(){
        showHeader();
        System.out.println(winnerBoxScore);
        System.out.println(loserBoxScore);
    }
}

