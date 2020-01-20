package WildCodeSchoolProject.GiftMeFive.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				String name = resultSet.getString("Name");
				String beschreibung = resultSet.getString("Beschreibung");
				String datum = resultSet.getString("Datum");
				String bildlink = resultSet.getString("Bildlink");
				String produktlink = resultSet.getString("Produktlink");
				String preis = resultSet.getString("Preis");
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
}
