package Components.Player;

import Components.Team.Team;

import java.util.Objects;

public class Player {

    // Player's name
    private String name;

    // Player's age
    private int age;

    // Player's rating
    private Rating rating;

    // Type of player (e.g., attacker, defender)
    private Type type;

    // Team to which the player belongs
    private Team team;

    // Role of the player (e.g., captain, vice-captain)
    private Role role;

    // Position of the player on the field
    private Position position;

    // Constructor for creating a player when team information is not known
    public Player(String name, int age, Type type, Role role, Position position) {
        this.name = name;
        this.age = age;
        this.rating = new Rating(type);
        this.type = type;
        this.role = role;
        this.position = position;
    }

    // Constructor for creating a player when team information is known
    public Player(String name, int age, Type type, Role role, Position position, Team team) {
        this.name = name;
        this.age = age;
        this.rating = new Rating(type);
        this.type = type;
        this.role = role;
        this.team = team;
        this.position = position;
    }

    // Getter for player's name
    public String getName() {
        return name;
    }

    // Method to increase the player's age
    public void increaseAge() {
        age = age + 1;
    }

    // Getter for player's rating
    public Rating getRating() {
        return rating;
    }

    // Getter for player's type
    public Type getType() {
        return type;
    }

    // Equals method to compare two players based on their attributes
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return age == player.age &&
                Objects.equals(name, player.name) &&
                Objects.equals(rating, player.rating) &&
                Objects.equals(role, player.role) &&
                Objects.equals(position, player.position) &&
                Objects.equals(team.getName(), player.team.getName()) &&
                type == player.type;
    }

    // Hash code method to generate a hash code based on player's attributes
    @Override
    public int hashCode() {
        return Objects.hash(name, age, rating, type, role, position, team.getName());
    }

    // String representation of the player
    @Override
    public String toString() {
        String teamName = (this.team == null) ? "None" : this.team.getName();
        return "Player{" +
                "--- name='" + name + '\'' +
                "--- age=" + age +
                "--- rating=" + rating +
                "--- type=" + type +
                "--- role=" + role +
                "--- position=" + position +
                "--- team=" + teamName +
                '}';
    }

    // Getter for the player's team
    public Team getTeam() {
        return team;
    }

    // Setter for the player's team
    public void setTeam(Team team) {
        this.team = team;
    }

    // Getter for the player's role
    public Role getRole() {
        return role;
    }

    // Getter for the player's position
    public Position getPosition() {
        return position;
    }

}

