package co.aurasphere.gomorrasql.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;

/**
 * Wrapper around JDBC query results, used by GomorraSQL.
 * 
 * @author Donato Rimenti
 *
 */
@Setter
@Getter
public class GomorraSqlQueryResult {
	
	private Integer affectedRows;
	
	private ResultSet resultSet;

}