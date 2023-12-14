package Components.Simulations;

import Components.Games.Game;
import Components.Player.Player;
import Components.Player.Role;

public class GameSimulation {

    // Fields
    private final Game game; // The basketball game to be simulated
    private TeamTotals team1Stats; // Total statistics of team 1
    private TeamTotals team2Stats; // Total statistics of team 2
    private double ROLE_FACTOR = 0.5; // Affects player's production based on their role
    private final double OUTSIDE_FACTOR = 20.0; // Affects how many three-pointers are made (higher means less)
    private final double INSIDE_FACTOR = 10; // Affects how many two-pointers are made (higher means less)
    private final double REBOUND_FACTOR = 10; // Affects how many rebounds are grabbed (higher means less)
    private final double ASSIST_FACTOR = 10; // Affects how many assists are made (higher means less)

    // Constructor
    public GameSimulation(Game game) {
        this.game = game;
        this.team1Stats = new TeamTotals(game.getTeam1());
        this.team2Stats = new TeamTotals(game.getTeam2());
    }

    // Method to set ROLE_FACTOR based on player's role
    public void setRole(Role role) {
        switch (role) {
            case Superstar:
                ROLE_FACTOR = 1.25;
                break;
            case Star:
                ROLE_FACTOR = 1;
                break;
            case Starter:
                ROLE_FACTOR = 0.75;
                break;
            case Sixth_man:
                ROLE_FACTOR = 0.75;
                break;
            case Bench:
                ROLE_FACTOR = 0.45;
                break;
        }
    }

    // Generate player statistics for team 1
    public PlayerStats team1PlayerStatGenerator(Player player) {
        setRole(player.getRole());
        // Calculate player statistics based on ratings, roles, and factors
        int outsideTotal = (int) (((player.getRating().getOutsideShotRating() + team1Stats.getPassingAverage() -
                team2Stats.getOutsideDefenseAverage()) / OUTSIDE_FACTOR) * ROLE_FACTOR);
        int insideTotal = (int) (((player.getRating().getInsideShotRating() + team1Stats.getPassingAverage() -
                team2Stats.getInsideDefenseAverage()) / INSIDE_FACTOR) * ROLE_FACTOR);
        int rebounds = (int) (((player.getRating().getReboundRating() + team1Stats.getReboundingAverage() -
                (team2Stats.getInsideDefenseAverage() - player.getRating().getReboundRating())) /
                REBOUND_FACTOR) * ROLE_FACTOR);
        int assists = (int) (((player.getRating().getPassingRating() + team1Stats.getInsideScoringAverage() +
                team1Stats.getOutsideScoringAverage() - team2Stats.getOutsideDefenseAverage() -
                team2Stats.getInsideDefenseAverage()) / ASSIST_FACTOR) * ROLE_FACTOR);
        return new PlayerStats(player, outsideTotal, insideTotal, rebounds, assists);
    }

    // Generate player statistics for team 2
    public PlayerStats team2PlayerStatGenerator(Player player) {
        setRole(player.getRole());
        // Calculate player statistics based on ratings, roles, and factors
        int outsideTotal = (int) (((player.getRating().getOutsideShotRating() + team2Stats.getPassingAverage() -
                team1Stats.getOutsideDefenseAverage()) / OUTSIDE_FACTOR) * ROLE_FACTOR);
        int insideTotal = (int) (((player.getRating().getInsideShotRating() + team2Stats.getPassingAverage() -
                team1Stats.getInsideDefenseAverage()) / INSIDE_FACTOR) * ROLE_FACTOR);
        int rebounds = (int) (((player.getRating().getReboundRating() + team2Stats.getReboundingAverage() -
                (team1Stats.getInsideDefenseAverage() - player.getRating().getReboundRating())) /
                REBOUND_FACTOR) * ROLE_FACTOR);
        int assists = (int) (((player.getRating().getPassingRating() + team2Stats.getInsideScoringAverage() +
                team2Stats.getOutsideScoringAverage() - team1Stats.getOutsideDefenseAverage() -
                team1Stats.getInsideDefenseAverage()) / ASSIST_FACTOR) * ROLE_FACTOR);
        return new PlayerStats(player, outsideTotal, insideTotal, rebounds, assists);
    }

    // Update a player's stats to determine the winner in overtime
    public TeamStats overTime(TeamStats teamStats) {
        PlayerStats stats = teamStats.getStatsList().get(0);
        teamStats.removeStat(stats);
        stats.updatePoints(1);
        teamStats.updateStats(stats);
        return teamStats;
    }

    // Run the game simulation
    public void runSimulation() {
        TeamStats team1Stats = new TeamStats(game.getTeam1());
        TeamStats team2Stats = new TeamStats(game.getTeam2());

        // Generate player statistics for team 1
        for (Player player : game.getTeam1().getRoster()) {
            PlayerStats stats = team1PlayerStatGenerator(player);
            team1Stats.updateStats(stats);
        }

        // Generate player statistics for team 2
        for (Player player : game.getTeam2().getRoster()) {
            PlayerStats stats = team2PlayerStatGenerator(player);
            team2Stats.updateStats(stats);
        }

        // Handle overtime if there's a tie
        if (team1Stats.getScore() == team2Stats.getScore()) {
            int winner = (int) (Math.random() * (2) + 1); // Randomly pick overtime winner (team 1 or team 2)
            if (winner == 2) {
                team2Stats = overTime(team2Stats);
            } else {
                team1Stats = overTime(team1Stats);
            }
        }

        // Set the box scores and determine the game winner
        game.setTeam1BoxScore(team1Stats);
        game.setTeam2BoxScore(team2Stats);

        if (team1Stats.getScore() > team2Stats.getScore()) {
            game.setWinner(game.getTeam1());
        } else if (team1Stats.getScore() < team2Stats.getScore()) {
            game.setWinner(game.getTeam2());
        }
    }
}

