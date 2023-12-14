package Components.Games;

import Components.Player.Player;
import Components.Simulations.PlayerStats;
import Components.Team.Team;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Season {
    private ArrayList<Game> gameList;
    private ArrayList<Team> teamList;
    private HashMap<Player, ArrayList<PlayerStats>> playerStatsMap;
    private SeasonLeader pointsLeader;
    private SeasonLeader reboundsLeader;
    private SeasonLeader assistsLeader;
    private SeasonLeader threeLeader;
    private Playoffs playoffs;
    private final int year;

    /**
     * Initializes a new season with a list of teams and a year.
     * @param teamList List of teams participating in the season.
     * @param year The year of the season.
     */
    public Season(ArrayList<Team> teamList, int year){
        this.gameList = new ArrayList<>();
        this.teamList = teamList;
        this.playerStatsMap = new HashMap<>();
        this.year = year;
    }

    /**
     * Arranges games for all teams in the season.
     */
    public void arrangeGames(){
        for(Team team1 : this.teamList){ // Loop through teams
            for(Team team2 : this.teamList){ // Loop through teams to see who can play who
                if(!team1.equals(team2)){ // Teams can't play each themselves
                    Game game = new Game(team1, team2); // Teams will play each other twice
                    this.gameList.add(game);
                }
            }
        }
    }

    /**
     * Simulates all the games in the season.
     */
    public void playGames(){
        for(Game game : this.gameList){
            game.play();
        }
    }

    /**
     * Parses game statistics and updates player statistics for the season.
     */
    public void parseGameStats(){
        for(Game game : this.gameList){
            for(PlayerStats stats : game.getTeam1BoxScore().getStatsList()){
                updateSeasonStats(stats);
            }
            for(PlayerStats stats : game.getTeam2BoxScore().getStatsList()){
                updateSeasonStats(stats);
            }
        }
    }

    /**
     * Updates the player statistics for the season.
     * @param stats Player statistics to update.
     */
    public void updateSeasonStats(PlayerStats stats){
        if(this.playerStatsMap.containsKey(stats.getPlayer())){ // Player already in the map
            ArrayList<PlayerStats> statsList = this.playerStatsMap.get(stats.getPlayer());
            statsList.add(stats);
            this.playerStatsMap.put(stats.getPlayer(), statsList);
        } else{ // Player not in the map yet
            List<PlayerStats> playerStats = new ArrayList<>();
            playerStats.add(stats);
            this.playerStatsMap.put(stats.getPlayer(), new ArrayList<>(playerStats));
        }
    }

    /**
     * Rounds a double value to two decimal places.
     * @param value The value to round.
     * @return The rounded value.
     */
    public double round(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Parses the season statistics to find leaders in points, rebounds, assists, and three-pointers.
     */
    public void parseSeasonStats(){
        HashMap<Player, Integer> pointsMap = new HashMap<>();
        HashMap<Player, Integer> reboundsMap = new HashMap<>();
        HashMap<Player, Integer> assistsMap = new HashMap<>();
        HashMap<Player, Integer> threeMap = new HashMap<>();
        for(ArrayList<PlayerStats> statsList : this.playerStatsMap.values()){ // Loop through all player stats
            for(PlayerStats stats : statsList){
                pointsMap = updatePlayerStats(pointsMap, stats.getTotalPoints(), stats.getPlayer());
                reboundsMap = updatePlayerStats(reboundsMap, stats.getRebounds(), stats.getPlayer());
                assistsMap = updatePlayerStats(assistsMap, stats.getAssists(), stats.getPlayer());
                threeMap = updatePlayerStats(threeMap, stats.getThreePointers(), stats.getPlayer());
            }
        }

        double highest = 0;
        for(Player player : pointsMap.keySet()){
            double avgPoints = (double) pointsMap.get(player) / (this.teamList.size()*3); // Calculate average points
            if(avgPoints > highest){
                highest = avgPoints;
                this.pointsLeader = new SeasonLeader(player, round(avgPoints), GameEnums.LeaderType.POINTS);
            }
        }

        highest = 0;
        for(Player player : reboundsMap.keySet()){
            double avgRebs = (double) reboundsMap.get(player) / (this.teamList.size()*3); // Calculate average rebounds
            if(avgRebs > highest){
                highest = avgRebs;
                this.reboundsLeader = new SeasonLeader(player, round(avgRebs), GameEnums.LeaderType.REBOUNDS);
            }
        }

        highest = 0;
        for(Player player : assistsMap.keySet()){
            double avgAsts = (double) assistsMap.get(player) / (this.teamList.size()*3); // Calculate average assists
            if(avgAsts > highest){
                highest = avgAsts;
                this.assistsLeader = new SeasonLeader(player, round(avgAsts), GameEnums.LeaderType.ASSISTS);
            }
        }

        highest = 0;
        for(Player player : threeMap.keySet()){
            int threes = threeMap.get(player);
            if(threes > highest){
                highest = threes;
                this.threeLeader = new SeasonLeader(player, threes, GameEnums.LeaderType.THREES);
            }
        }
    }

    /**
     * Updates player statistics in the given map.
     * @param map The map containing player statistics.
     * @param statNum The statistics number to update.
     * @param player The player whose statistics are being updated.
     * @return The updated map.
     */
    public HashMap<Player, Integer> updatePlayerStats(HashMap<Player, Integer> map, Integer statNum, Player player){
        if(map.containsKey(player)){ // Player's key is in the map
            int updateName = map.get(player);
            updateName += statNum;
            map.put(player, updateName);
        } else{ // Player's key is not in the map
            map.put(player, statNum);
        }
        return map;
    }

    /**
     * Gets the list of games in the season.
     * @return List of games.
     */
    public ArrayList<Game> getGameList() {
        return gameList;
    }

    /**
     * Gets the playoffs of the season.
     * @return Playoffs.
     */
    public Playoffs getPlayoffs() {
        return playoffs;
    }

    /**
     * Ends the season and displays the results including records and season leaders.
     */
    public void endSeason() {
        StringBuilder endString = new StringBuilder();
        endString.append(String.format("\n------ %s Season Results ------\n", this.year));
        endString.append("---\n--- Records: ---\n");

        // Iterate over the 8 selected teams and append their records
        for (Team team : teamList) {
            endString.append(String.format("--- [%s] (%d-%d) ---\n",
                    team.getName(), team.getWins(), team.getLosses()));
        }

        endString.append("---\n--- Season Leaders: ---\n---\n");

        // Assuming each leader toString method provides a formatted string
        endString.append("Points Leader: ").append(this.pointsLeader.toString()).append("\n");
        endString.append("Rebounds Leader: ").append(this.reboundsLeader.toString()).append("\n");
        endString.append("Assists Leader: ").append(this.assistsLeader.toString()).append("\n");
        endString.append("Three Point Leader: ").append(this.threeLeader.toString()).append("\n");

        System.out.println(endString.toString());
    }


    /**
     * Simulates the entire season, including arranging games, playing them, parsing stats, and ending the season.
     */
    public void simulateSeason(){
        arrangeGames();
        playGames();
        parseGameStats();
        parseSeasonStats();
        endSeason();
    }

    /**
     * Simulates the playoffs for the season.
     */
    public void simulatePlayoffs(){
        this.playoffs = new Playoffs(this.teamList, this.year);
        playoffs.simulate();
    }
}

