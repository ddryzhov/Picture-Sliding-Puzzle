package sk.tuke.gamestudio.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Score {
    Connection connection;

    //Constructor that creates a connection to the PostgreSQL database.
    public Score() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/gamestudio";
        String user = "postgres";
        String password = "Danil2704";
        try {
            connection = DriverManager.getConnection(url, user, password);
        }catch (SQLException e) {
            throw new SQLException("Unable to establish a connection to the database", e);
        }
    }

    //Retrieves the top 10 players with the highest scores from the database.
    public List<String> getTopPlayers() throws SQLException {
        List<String> topPlayers = new ArrayList<>();
        String sql = "SELECT player_name, score FROM scores ORDER BY score DESC LIMIT 10";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String player = resultSet.getString("player_name") + ": " + resultSet.getInt("score");
            topPlayers.add(player);
        }
        return topPlayers;
    }

    //Closes the connection to the database.
    public void close() throws SQLException {
        connection.close();
    }
}