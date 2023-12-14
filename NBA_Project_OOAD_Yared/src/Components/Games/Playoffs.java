package Components.Games;

import Components.Team.Team;

import java.util.ArrayList;
import java.util.Collections;

public class Playoffs {

    private final int year;
    private final ArrayList<Team> selectedTeams;
    private final ArrayList<Game> gameList;
    // Holds the teams participating in the playoffs


    // Constructor: Initializes the playoffs with the participating teams and the year
    public Playoffs(ArrayList<Team> allTeams, int year) {
        this.year = year;
        this.gameList = new ArrayList<>();
        this.selectedTeams = new ArrayList<>();

        // Randomly select 8 teams from the provided list
        Collections.shuffle(allTeams);
        for (int i = 0; i < 8; i++) {
            this.selectedTeams.add(allTeams.get(i));
        }

    }

    private ArrayList<Team> simulateRound(ArrayList<Team> selectedTeams, GameEnums.RoundType roundType) {
        ArrayList<Team> winners = new ArrayList<>();

        for (int i = 0; i < selectedTeams.size(); i += 2) {
            Team team1 = selectedTeams.get(i);
            Team team2 = selectedTeams.get(i + 1);

            PlayoffComp round = simulateMatch(team1, team2, roundType);
            winners.add(round.getWinner());
        }

        return winners;
    }

    // Simulates a playoff round
    private PlayoffComp simulateMatch(Team team1, Team team2, GameEnums.RoundType roundType) {
        int team1Wins = 0, team2Wins = 0;

        // Simulate games until one of the teams wins 4 games
        while (team1Wins < 4 && team2Wins < 4) {
            Game game = new Game(team1, team2);
            game.play(); // Simulate a single game
            this.gameList.add(game);

            // Determine the winner of each game and increment their win count
            if (game.getWinner().getName().equals(team1.getName())) {
                team1Wins++;
            } else {
                team2Wins++;
            }
        }

        // Create a PlayoffComp object to record the match result
        Team winner = team1Wins > team2Wins ? team1 : team2;
        Team loser = team1Wins > team2Wins ? team2 : team1;
        int winnerWins = Math.max(team1Wins, team2Wins);
        int loserWins = Math.min(team1Wins, team2Wins);

        return new PlayoffComp(winner, winnerWins, loser, loserWins, roundType);
    }


    // Simulates the entire playoffs and determines the champion
    public void simulate() {
        // Quarterfinals
        ArrayList<Team> quarterFinalWinners = simulateRound(selectedTeams, GameEnums.RoundType.QUARTER_FINAL);

        // Semifinals
        ArrayList<Team> semiFinalWinners = simulateRound(quarterFinalWinners, GameEnums.RoundType.SEMI_FINAL);

        // Finals
        Team finalist1 = semiFinalWinners.get(0);
        Team finalist2 = semiFinalWinners.get(1);
        PlayoffComp championshipRound = simulateMatch(finalist1, finalist2, GameEnums.RoundType.CHAMPIONSHIP);

        // Determine the champion
        Team champion = championshipRound.getWinner();
        System.out.printf("--- The %s have won the %s championship! ---%n", champion.getName(), year);
    }

    // Returns the list of games played during the playoffs
    public ArrayList<Game> getGameList() {
        return gameList;
    }
}
