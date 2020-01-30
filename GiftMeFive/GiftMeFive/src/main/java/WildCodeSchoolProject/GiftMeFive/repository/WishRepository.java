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
	
	public List<Article> showWishlistForm(Long wishlistId) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement(
					"SELECT * FROM article WHERE wishlistId = ?;");
			statement.setLong(1, wishlistId);
			resultSet = statement.executeQuery();

			List<Article> article = new ArrayList<>();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				String creationdate = resultSet.getString("creationdate");
				String imagelink = resultSet.getString("imagelink");
				String productlink = resultSet.getString("productlink");

				article.add(new Article(id, name, description, creationdate, imagelink, productlink, wishlistId));
			}
			return article;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JdbcUtils.closeResultSet(resultSet);
			JdbcUtils.closeStatement(statement);
			JdbcUtils.closeConnection(connection);
		}
		return null;
	}

	public Article addWish(String articlename, String description, String userimage, String productlink,
			Long wishlistId) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement(
					"INSERT INTO article (name, description, imagelink, productlink, wishlistId) VALUES (?, ?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, articlename);
			statement.setString(2, description);
			statement.setString(3, userimage);
			statement.setString(4, productlink);
			statement.setLong(5, wishlistId);

			if (statement.executeUpdate() != 1) {
				throw new SQLException("failed to insert data");
			}
			generatedKeys = statement.getGeneratedKeys();
			if (!generatedKeys.next()) {
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

	public void removeWish(Long articleId) {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement("DELETE FROM article WHERE id = ?;");
			statement.setLong(1, articleId);

			if (statement.executeUpdate() != 1) {
				throw new SQLException("failed to remove data");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeStatement(statement);
			JdbcUtils.closeConnection(connection);
		}
	}

	public Long createWishlist(String titlename, String enddate) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet generatedSet = null;
		Long wishlistId = null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement("INSERT INTO wishlist (name, enddate) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, titlename);
			statement.setString(2, enddate);

			if (statement.executeUpdate() != 1) {
				throw new SQLException("failed to insert data");
			}
			generatedSet = statement.getGeneratedKeys();

			if (generatedSet.next()) {
				wishlistId = generatedSet.getLong(1);
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
		return wishlistId;
	}

	public Reservierung reserveWish(Long articleId, String reservationname) {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement("UPDATE reservation SET name=?, reserved=true WHERE id = ?;");
			statement.setString(1, reservationname);
			statement.setLong(2, articleId);

			if (statement.executeUpdate() != 1) {
				throw new SQLException("failed to insert data");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			JdbcUtils.closeStatement(statement);
			JdbcUtils.closeConnection(connection);
		}
		return null;
	}
	
	public List<Article> showWishlist(Long wishlistId) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement(
					"SELECT *, wishlist.name AS wishlistname, reservation.reserved, reservation.name AS reservationname FROM article JOIN wishlist ON wishlistId = wishlist.id JOIN reservation ON article.id = reservation.id WHERE wishlistId = ? ;");
			statement.setLong(1, wishlistId);
			resultSet = statement.executeQuery();

			List<Article> Article = new ArrayList<>();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				String creationdate = resultSet.getString("creationdate");
				String imagelink = resultSet.getString("imagelink");
				String productlink = resultSet.getString("productlink");
				String wishlistname = resultSet.getString("wishlistname");
				Boolean reserved = resultSet.getBoolean("reserved");
				String reservationname = resultSet.getString("reservationname");

				Article.add(new Article(id, name, description, creationdate, imagelink, productlink, wishlistId,
						wishlistname, reserved, reservationname));
			}
			return Article;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JdbcUtils.closeResultSet(resultSet);
			JdbcUtils.closeStatement(statement);
			JdbcUtils.closeConnection(connection);
		}
		return null;
	}
	
	public String getWishlistname(Long wishlistId) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement(
					"SELECT name FROM wishlist WHERE id = ? ;");
			
			statement.setLong(1, wishlistId);
			resultSet = statement.executeQuery();
			
			String wishlistname = "";
			while (resultSet.next()) {
				wishlistname = resultSet.getString("name");;	
			}
			return wishlistname;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JdbcUtils.closeResultSet(resultSet);
			JdbcUtils.closeStatement(statement);
			JdbcUtils.closeConnection(connection);
		}
		return null;
	}
}
