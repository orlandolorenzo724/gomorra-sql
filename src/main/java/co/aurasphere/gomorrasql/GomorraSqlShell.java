package co.aurasphere.gomorrasql;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import co.aurasphere.gomorrasql.model.MannaggGiudException;
import co.aurasphere.gomorrasql.model.GomorraSqlQueryResult;
import co.aurasphere.gomorrasql.utils.PropertiesUtils;

/**
 * Shell wrapper on the {@link GomorraSqlInterpreter}.
 * 
 * @author Donato Rimenti
 *
 */
public class GomorraSqlShell {
	static Logger logger = Logger.getLogger(GomorraSqlShell.class.getName());

	public static void main(String[] args) throws Exception {
		boolean isConnectionConfigured = false;
		String connectionUrl = "";
		try {
			connectionUrl = PropertiesUtils.getConnectionString();
			isConnectionConfigured = true;
		} catch (Exception e) {
			logger.info("Could not find a configured database connection inside database.yml");
		}

		Scanner scanner = new Scanner(System.in, StandardCharsets.ISO_8859_1.name());
		if (!isConnectionConfigured) {
			System.out.print("Insert a JDBC string to connect to a database: ");
			connectionUrl = scanner.nextLine();
		}

		Connection connection = DriverManager.getConnection(connectionUrl);
		GomorraSqlInterpreter gomorraSqlParser = new GomorraSqlInterpreter(connection);

		System.out.println("Succesfully connected to DB " + connection.getCatalog());
		while (true) {
			try {
				System.out.print("> ");
				String query = scanner.nextLine();
				System.out.println(query);
				GomorraSqlQueryResult result = gomorraSqlParser.execute(query);
				if (result.getResultSet() != null) {
					printSelectResult(result.getResultSet());
				}
				if (result.getAffectedRows() != null) {
					System.out.println("Affected rows: " + result.getAffectedRows());
				}
				if (result.getResultSet() == null && result.getAffectedRows() == null) {
					System.out.println("OK");
				}
			} catch (MannaggGiudException | SQLException e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}

	private static void printSelectResult(ResultSet result) throws SQLException {
		ResultSetMetaData rsmd = result.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		for (int i = 1; i <= columnsNumber; i++) {
			System.out.print(rsmd.getColumnName(i) + " | ");
		}
		System.out.println();
		while (result.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				System.out.print(result.getString(i) + " | ");
			}
			System.out.println();
		}
	}

}