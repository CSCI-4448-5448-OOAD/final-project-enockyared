package Components.Games;

import Components.Player.Player;
import Components.Team.Team;

import java.util.*;

public class League_NBA {

    private ArrayList<Team> teamList; // Stores the list of teams participating in the league
    private ArrayList<Season> seasonList; // Maintains a record of all the seasons played in the league
    private int year; // Tracks the current year of the league

    // Constructor: Initializes the league with a list of teams and sets the current year
    public League_NBA(ArrayList<Team> teamList){
        this.teamList = teamList;
        this.seasonList = new ArrayList<>();
        this.year = Calendar.getInstance().get(Calendar.YEAR);
    }

    // Simulates a new season, adds it to the season list, and updates league year
    public void simulateNewSeason(){
        Season season = new Season(this.teamList, this.year);
        season.simulateSeason();
        season.simulatePlayoffs();
        this.seasonList.add(season);
    }

    // Generates a map of games played by a specific user team in the current season
    public HashMap<Integer, Game> buildMap(Team userTeam){
        int count = 0;
        HashMap<Integer, Game> gameMap = new HashMap<>();
        Season season = seasonList.get(0); // Assumes the current season is the first in the list
        // Adds regular season games involving the user's team to the map
        for(Game game : season.getGameList()){
            if(game.getTeam1().getName().equals(userTeam.getName()) ||
                    game.getTeam2().getName().equals(userTeam.getName())){
                count += 1;
                gameMap.put(count, game);
                System.out.printf("Game %s (%s Season)%n", count, this.year);
                game.showHeader();
            }
        }
        // Adds playoff games involving the user's team to the map
        for(Game game : season.getPlayoffs().getGameList()){
            if(game.getTeam1().getName().equals(userTeam.getName()) ||
                    game.getTeam2().getName().equals(userTeam.getName())){
                count += 1;
                gameMap.put(count, game);
                System.out.printf("Game %s (%s Playoffs)%n", count, this.year);
                game.showHeader();
            }
        }
        return gameMap;
    }

    // Increments the league year and ages of all players in the league
    public void addYear(){
        this.year += 1;
        for(Team team : this.teamList){
            for(Player player : team.getRoster()){
                player.increaseAge();
            }
        }
    }
}

