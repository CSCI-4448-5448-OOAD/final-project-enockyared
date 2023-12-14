package Create;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class GameInputs {

    // Nested static class for player related inputs
    public static class Player {
        public static final Set<String> POSITION_SET = new HashSet<>(Arrays.asList(
                "Point_guard", "Shooting_guard", "Small_forward", "Power_forward", "Center"));

        public static final Set<String> ROLE_SET = new HashSet<>(Arrays.asList(
                "Superstar", "Star", "Starter", "Sixth_man", "Bench"));

        public static final Set<String> TYPE_SET = new HashSet<>(Arrays.asList(
                "Three_and_D", "All_around", "Offensive_playmaker", "Defensive_playmaker", "Playmaker",
                "Energetic_rebounder", "Three_level_scorer", "Post_player", "In_out_defender", "Pure_shooter",
                "Stretch_big", "Inside_defender", "Perimeter_defender", "Paint_beast", "Offensive_superstar",
                "Point_god", "Balanced_superstar", "Lockdown_defender", "Two_way_superstar", "God"));

    }

    // Nested static class for team related inputs
    public static class Team {
        // Existing sets
        public static final Set<String> LAKERS_SET = new HashSet<>(Arrays.asList("lakers", "Lakers", "LAKERS"));
        public static final Set<String> WARRIORS_SET = new HashSet<>(Arrays.asList("warriors", "Warriors", "WARRIORS"));
        public static final Set<String> SIXERS_SET = new HashSet<>(Arrays.asList("sixers", "Sixers", "76ers", "SIXERS"));
        public static final Set<String> CELTICS_SET = new HashSet<>(Arrays.asList("celtics", "Celtics", "CELTICS"));

        // New sets for additional teams
        public static final Set<String> NUGGETS_SET = new HashSet<>(Arrays.asList("nuggets", "Nuggets", "NUGGETS"));
        public static final Set<String> SUNS_SET = new HashSet<>(Arrays.asList("suns", "Suns", "SUNS"));
        public static final Set<String> CLIPPERS_SET = new HashSet<>(Arrays.asList("clippers", "Clippers", "CLIPPERS"));
        public static final Set<String> TIMBERWOLVES_SET = new HashSet<>(Arrays.asList("timberwolves", "Timberwolves", "TIMBERWOLVES"));
        public static final Set<String> HEAT_SET = new HashSet<>(Arrays.asList("heat", "Heat", "HEAT"));
        public static final Set<String> MAVERICKS_SET = new HashSet<>(Arrays.asList("mavericks", "Mavericks", "MAVERICKS"));
        public static final Set<String> BUCKS_SET = new HashSet<>(Arrays.asList("bucks", "Bucks", "BUCKS"));

        public static boolean isValidTeamName(String teamName) {
            return LAKERS_SET.contains(teamName) || WARRIORS_SET.contains(teamName) ||
                    SIXERS_SET.contains(teamName) || CELTICS_SET.contains(teamName) ||
                    NUGGETS_SET.contains(teamName) || SUNS_SET.contains(teamName) ||
                    CLIPPERS_SET.contains(teamName) || TIMBERWOLVES_SET.contains(teamName) ||
                    HEAT_SET.contains(teamName) || MAVERICKS_SET.contains(teamName) ||
                    BUCKS_SET.contains(teamName);
        }
    }
}