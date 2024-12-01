package co.aurasphere.gomorrasql;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.aurasphere.gomorrasql.model.MannaggGiudException;

public class TestWrongSyntax {
	
	private Connection connection;

	@Before
	public void setup() throws SQLException {
		this.connection = Config.getDbConnection();
	}

	@After
	public void teardown() throws SQLException {
		this.connection.close();
	}

	@Test(expected = MannaggGiudException.class)
	public void testWrongInitialKeyword() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		gsi.execute("uella");
	}
	
	@Test(expected = MannaggGiudException.class)
	public void testSelectDoubleComma() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		gsi.execute("ripigliammo a , ,");
	}
	
	@Test(expected = MannaggGiudException.class)
	public void testSelectWrongFrom() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		gsi.execute("ripigliammo a chist");
	}
	
	@Test(expected = MannaggGiudException.class)
	public void testQueryEndExpected() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		gsi.execute("sfaccimm wrong");
	}
	
	@Test(expected = MannaggGiudException.class)
	public void testWrongLongOperator() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		gsi.execute("ripigliammo tutto proprio tutto");
	}
	
	@Test(expected = MannaggGiudException.class)
	public void testIncompleteQuery() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		gsi.execute("ripigliammo");
	}
	
	@Test(expected = MannaggGiudException.class)
	public void testWhereInvalidOperator() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		gsi.execute("ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a city ar� city_id !! 4");
	}
	
	@Test(expected = MannaggGiudException.class)
	public void testWrongSelectWhereJoinOperator() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		gsi.execute("ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a city ar� city_id <= 4 ma 5 > 1");
	}
	
	@Test(expected = MannaggGiudException.class)
	public void testWrongSelectWhereClause() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		gsi.execute("ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a city quando city_id <= 4 e 5 > 1");
	}
	
	@Test(expected = MannaggGiudException.class)
	public void testWrongUpdateSetClause() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		gsi.execute("rifacimm city cambia city_id = 1");
	}
	
	@Test(expected = MannaggGiudException.class)
	public void testWrongUpdateWhereClause() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		gsi.execute("rifacimm city accunza city_id accuss� 1 e basta");
	}
}