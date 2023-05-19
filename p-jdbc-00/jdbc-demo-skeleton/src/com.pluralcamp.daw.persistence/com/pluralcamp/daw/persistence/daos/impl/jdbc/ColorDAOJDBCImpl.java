package com.pluralcamp.daw.persistence.daos.impl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pluralcamp.daw.entities.core.Color;
import com.pluralcamp.daw.persistence.daos.contracts.ColorDAO;
import com.pluralcamp.daw.persistence.exceptions.DAOException;

public class ColorDAOJDBCImpl implements ColorDAO {
    @Override

    public Color getColorById(long id) throws DAOException {
        // Objectes que calen:
        Color color = null;
        // 1er objecte - Connexio, via DriverManager de JDBC
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/calendar-restore?serverTimezone=Europe/Paris", "root", "");
                PreparedStatement sentSQL = connection
                        .prepareStatement("SELECT id, name, red, green, blue FROM colors WHERE id = ?");) {

            sentSQL.setLong(1, id);
            try (ResultSet reader = sentSQL.executeQuery()) {
                if (reader.next()) {
                    color = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"),
                            reader.getInt("blue"));
                    color.setId(reader.getLong("id"));
                }
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }

        return color;
        // 2n objecte - Obrir un canal de Consulta - PraparedStatement
        // 2.1 - Enviar la consulta SQL
        // 3er objecte - Obrir un canal de Lectura, un cursor - ResultSet
    }

    @Override
    public List<Color> getColors() throws DAOException {

        List<Color> colors = new ArrayList<>();
        // 1er objecte - Connexio, via DriverManager de JDBC

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/calendar-restore?serverTimezone=Europe/Paris", "root", "");
                PreparedStatement sentSQL = connection
                        .prepareStatement("SELECT id, name, red, green, blue FROM colors");
                ResultSet reader = sentSQL.executeQuery()) {

            while (reader.next()) {
                var color = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"),
                        reader.getInt("blue"));
                color.setId(reader.getLong("id"));
                colors.add(color);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
        return colors;
    }

    @Override
    public List<Color> getColors(int offset, int count) throws DAOException {
        List<Color> colors = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/calendar-restore?serverTimezone=Europe/Paris", "root", "");
                PreparedStatement sentSQL = connection
                        .prepareStatement("SELECT id, name, red, green, blue FROM colors LIMIT ?, ?");) {

            sentSQL.setInt(1, offset);
            sentSQL.setInt(2, count);

            try (ResultSet reader = sentSQL.executeQuery()) {
                while (reader.next()) {
                    var color = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"),
                            reader.getInt("blue"));
                    color.setId(reader.getLong("id"));
                    colors.add(color);
                }
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }

        return colors;
    }

    @Override
    public List<Color> getColors(String searchTerm) throws DAOException {
        List<Color> colors = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/calendar-restore?serverTimezone=Europe/Paris", "root", "");
                PreparedStatement sentSQL = connection
                        .prepareStatement("SELECT id, name, red, green, blue FROM colors WHERE name LIKE ?");) {

            sentSQL.setString(1, "%" + searchTerm + "%");

            try (ResultSet reader = sentSQL.executeQuery()) {
                while (reader.next()) {
                    var color = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"),
                            reader.getInt("blue"));
                    color.setId(reader.getLong("id"));
                    colors.add(color);
                }
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }

        return colors;
    }

    @Override
    public List<Color> getColors(String searchTerm, int offset, int count) throws DAOException {
        List<Color> colors = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/calendar-restore?serverTimezone=Europe/Paris", "root", "");
                PreparedStatement sentSQL = connection.prepareStatement(
                        "SELECT id, name, red, green, blue FROM colors WHERE name LIKE ? LIMIT ?, ?");) {

            sentSQL.setString(1, "%" + searchTerm + "%");
            sentSQL.setInt(2, offset);
            sentSQL.setInt(3, count);

            try (ResultSet reader = sentSQL.executeQuery()) {
                while (reader.next()) {
                    var color = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"),
                            reader.getInt("blue"));
                    color.setId(reader.getLong("id"));
                    colors.add(color);
                }
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }

        return colors;
    }
}
