package WildCodeSchoolProject.GiftMeFive.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import WildCodeSchoolProject.GiftMeFive.util.JdbcUtils;
import WildCodeSchoolProject.GiftMeFive.entity.*;

public class WishRepository {

	private final static String DB_URL = "jdbc:mysql://localhost:3306/gm5?serverTimezone=GMT";
	private final static String DB_USER = "gm5admin5";
	private final static String DB_PASSWORD = "WildCodeGm_5!";

	public List<Artikel> showWishlist(Long eingabe) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement("SELECT * FROM Artikel WHERE wunschliste_id = ?;");
			statement.setLong(1, eingabe);
			resultSet = statement.executeQuery();

			List<Artikel> artikel = new ArrayList<>();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String name = resultSet.getString("name");
				String beschreibung = resultSet.getString("beschreibung");
				String datum = resultSet.getString("datum");
				String bildlink = resultSet.getString("bildlink");
				String produktlink = resultSet.getString("produktlink");
				String preis = resultSet.getString("preis");
				artikel.add(new Artikel(id, name, beschreibung, datum, bildlink, produktlink, preis));
			}
			return artikel;
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JdbcUtils.closeResultSet(resultSet);
			JdbcUtils.closeStatement(statement);
			JdbcUtils.closeConnection(connection);
		}
		return null;
	}
	
	public Artikel addWish(String Name, String Beschreibung, String Datum,
            String Bildlink, String Produktlink, String Preis, Long wunschliste_id, String titelname) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement(
					"INSERT INTO Artikel (Name, Beschreibung,Bildlink, Produktlink, Preis, wunschliste_id) VALUES (?, ?, ?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);
						
			    statement.setString(1, Name);
				statement.setString(2, Beschreibung);
				statement.setString(3, Bildlink);
				statement.setString(4, Produktlink);
				statement.setString(5, Preis);
				statement.setLong(6, wunschliste_id);
			
				if (statement.executeUpdate() != 1) {
	                throw new SQLException("failed to insert data");
	            }
				generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
	                Long id = generatedKeys.getLong(1);
	                System.out.println("Repo: "+ id + Name + Beschreibung + Datum + Bildlink + Produktlink + Preis);
	                return new Artikel(id, Name, Beschreibung, Datum, Bildlink, Produktlink, Preis);
				} else {
	                throw new SQLException("failed to get inserted id");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            JdbcUtils.closeResultSet(generatedKeys);
	            JdbcUtils.closeStatement(statement);
	            JdbcUtils.closeConnection(connection);
	        }
	               
		return null;
	}
	
	public void removeWish(Long id) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement(
					"DELETE FROM Artikel WHERE id = ?;", 
					Statement.RETURN_GENERATED_KEYS);
						
				statement.setLong(1, id);
			
				if (statement.executeUpdate() != 1) {
	                throw new SQLException("failed to remove data");
	            }
				System.out.println("Repo: "+ id + "gelöscht.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            JdbcUtils.closeResultSet(generatedKeys);
	            JdbcUtils.closeStatement(statement);
	            JdbcUtils.closeConnection(connection);
	        }
	}

	public Long erstellen(String name, String datum) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet generatedSet = null;
		Long id = 0l;
		
		try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "INSERT INTO Wunschliste (name, enddatum) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, name);
            statement.setString(2, datum);


            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to insert data");
            }

            generatedSet = statement.getGeneratedKeys();

            if (generatedSet.next()) {
                id = generatedSet.getLong(1);
                //return new Wunschliste(id, name, datum);
            } else {
                throw new SQLException("failed to get inserted id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResultSet(generatedSet);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }		
        return id;
	}
	
}
