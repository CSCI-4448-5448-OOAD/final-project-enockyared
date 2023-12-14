package Components.NCA;

import Components.Player.Player;
import Components.Player.Position;
import Components.Player.Role;
import Components.Player.Type;

import java.util.HashSet;
import java.util.Random;

public class PlayerPop {

    HashSet<String> nameSet;

    public PlayerPop(){
        nameSet = new HashSet<>();
    }
    public String generateName() {
        String name = "";
        int listLength = 50; // Use the length of the nameList in PlayerNames
        Random random = new Random();

        while (name.equals("")) {
            int index = random.nextInt(listLength);
            name = PlayerNames.names[index];
            if (nameSet.contains(name)) {
                name = "";
            }
        }
        nameSet.add(name);
        return name;
    }



        public Type generateType(Position position, Role role) {
            int random = generateRandomForRole(role, position);
            return mapType(position, random);
        }

        private int generateRandomForRole(Role role, Position position) {
            int lowerBound, upperBound;

            if (role.equals(Role.Superstar)) {
                switch (position) {
                    case Point_guard:
                        lowerBound = 5; upperBound = 9;
                        break;
                    case Shooting_guard:
                        lowerBound = 6; upperBound = 9;
                        break;
                    case Small_forward:
                        lowerBound = 7; upperBound = 10;
                        break;
                    case Power_forward:
                        lowerBound = 8; upperBound = 10;
                        break;
                    case Center:
                        return 4; // Special case for Center with Superstar role
                    default:
                        throw new IllegalArgumentException("Unknown position: " + position);
                }
            } else {
                switch (position) {
                    case Point_guard:
                    case Shooting_guard:
                        upperBound = 5;
                        break;
                    case Small_forward:
                        upperBound = 6;
                        break;
                    case Power_forward:
                        upperBound = 7;
                        break;
                    case Center:
                        upperBound = 3;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown position: " + position);
                }
                lowerBound = 0;
            }

            return (int) (Math.random() * (upperBound - lowerBound + 1)) + lowerBound;
        }

        private Type mapType(Position position, int random) {
            switch (position) {
                case Point_guard:
                    switch (random) {
                        case 0:
                            return Type.Playmaker;
                        case 1:
                            return Type.All_around;
                        case 2:
                            return Type.Offensive_playmaker;
                        case 3:
                            return Type.Defensive_playmaker;
                        case 4:
                            return Type.Perimeter_defender;
                        case 5:
                            return Type.Offensive_superstar;
                        case 6:
                            return Type.Point_god;
                        case 7:
                            return Type.Balanced_superstar;
                        case 8:
                            return Type.Lockdown_defender;
                        case 9:
                            return Type.Two_way_superstar;
                        default:
                            throw new IllegalStateException("Unexpected value for Point Guard: " + random);
                    }
                case Shooting_guard:
                    switch (random) {
                        case 0:
                            return Type.Three_and_D;
                        case 1:
                            return Type.All_around;
                        case 2:
                            return Type.Three_level_scorer;
                        case 3:
                            return Type.Perimeter_defender;
                        case 4:
                            return Type.In_out_defender;
                        case 5:
                            return Type.Pure_shooter;
                        case 6:
                            return Type.Offensive_superstar;
                        case 7:
                            return Type.Balanced_superstar;
                        case 8:
                            return Type.Lockdown_defender;
                        case 9:
                            return Type.Two_way_superstar;
                        default:
                            throw new IllegalStateException("Unexpected value for Shooting Guard: " + random);
                    }
                case Small_forward:
                    switch (random) {
                        case 0:
                            return Type.Three_and_D;
                        case 1:
                            return Type.All_around;
                        case 2:
                            return Type.Three_level_scorer;
                        case 3:
                            return Type.Perimeter_defender;
                        case 4:
                            return Type.In_out_defender;
                        case 5:
                            return Type.Inside_defender;
                        case 6:
                            return Type.Pure_shooter;
                        case 7:
                            return Type.Balanced_superstar;
                        case 8:
                            return Type.Lockdown_defender;
                        case 9:
                            return Type.Two_way_superstar;
                        case 10:
                            return Type.Offensive_superstar;
                        default:
                            throw new IllegalStateException("Unexpected value for Small Forward: " + random);
                    }
                case Power_forward:
                    switch (random) {
                        case 0:
                            return Type.Energetic_rebounder;
                        case 1:
                            return Type.Post_player;
                        case 2:
                            return Type.In_out_defender;
                        case 3:
                            return Type.Perimeter_defender;
                        case 4:
                            return Type.Inside_defender;
                        case 5:
                            return Type.Pure_shooter;
                        case 6:
                            return Type.Stretch_big;
                        case 7:
                            return Type.Three_level_scorer;
                        case 8:
                            return Type.Paint_beast;
                        case 9:
                            return Type.Lockdown_defender;
                        case 10:
                            return Type.Two_way_superstar;
                        default:
                            throw new IllegalStateException("Unexpected value for Power Forward: " + random);
                    }
                case Center:
                    switch (random) {
                        case 0:
                            return Type.Energetic_rebounder;
                        case 1:
                            return Type.Post_player;
                        case 2:
                            return Type.Inside_defender;
                        case 3:
                            return Type.Stretch_big;
                        case 4:
                            return Type.Paint_beast;
                        default:
                            throw new IllegalStateException("Unexpected value for Center: " + random);
                    }
                default:
                    throw new IllegalArgumentException("Unknown position: " + position);
            }
        }


    public Position generatePosition(){
        int numberOfPositions = 4; //starting at 0
        int random = new Random().nextInt(numberOfPositions);
        switch (random) {
            case 0:
                return Position.Point_guard;
            case 1:
                return Position.Shooting_guard;
            case 2:
                return Position.Small_forward;
            case 3:
                return Position.Power_forward;
            case 4:
                return Position.Center;
            default:
                throw new IllegalStateException("Unexpected value: " + random);
        }
    }
    public Role generate2kRating(){
        int random = new Random().nextInt(100);
        if(random >= 90){   //rare type
            return Role.Superstar;
        } else if(random  >= 80){ 
            return Role.Star;
        } else if(random >= 70){ 
            return Role.Star;
        } else if(random <= 60){ 
            return Role.Sixth_man;
        } else{ //181-240
            return Role.Bench;
        }
    }
    public int generateAge(){
        return (int) (Math.random() * (25 - 19 + 1) + 19);
    }

    public Player createPlayer(){
        Position position = generatePosition();
        Role role = generate2kRating();
        Type type = generateType(position, role);
        return new Player(generateName(), generateAge(), type, role, position);
    }

    public static void main(String[] args) {
        PlayerPop playerPop = new PlayerPop();
        
        Player player = playerPop.createPlayer();
        System.out.println("\nGenerated Players:");
        for (int i = 0; i < 11; i++) {
            Player newPlayer = playerPop.createPlayer();
            System.out.println(newPlayer);
        }
    }
}
