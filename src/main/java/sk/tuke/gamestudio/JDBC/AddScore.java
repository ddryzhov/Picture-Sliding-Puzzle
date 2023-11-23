package sk.tuke.gamestudio.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddScore extends Score {
    public AddScore() throws SQLException {
    }

    //Adds a score for a player to the database.
    public void addScore(String playerName, int score) throws SQLException {
        String sql = "INSERT INTO scores (player_name, score) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerName);
            statement.setInt(2, score);
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new SQLException("Failed to insert score for player " + playerName, e);
        }
    }
}
