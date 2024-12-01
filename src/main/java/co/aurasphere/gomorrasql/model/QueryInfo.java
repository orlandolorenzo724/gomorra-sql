package co.aurasphere.gomorrasql.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Context wrapper for the parsed query.
 * 
 * @author Donato Rimenti
 *
 */
@Getter
public class QueryInfo {

	public enum QueryType {
		SELECT, UPDATE, DELETE, INSERT, COMMIT, BEGIN_TRANSACTION, ROLLBACK;
	}

	@Setter
	private QueryType type;

	@Setter
	private String tableName;

	private final List<String> columnNames = new ArrayList<>();

	private final List<String> values = new ArrayList<>();

	private final List<WhereCondition> whereConditions = new ArrayList<>();

	private final List<String> joinedTables = new ArrayList<>();

	private final List<String> whereConditionsJoinOperators = new ArrayList<>();

	public void addColumnName(String columnName) {
		this.columnNames.add(columnName);
	}

	public void addValue(String value) {
		this.values.add(value);
	}

	public void addWhereCondition(WhereCondition whereCondition) {
		this.whereConditions.add(whereCondition);
	}

	public void addJoinedTable(String joinedTable) {
		this.joinedTables.add(joinedTable);
	}

	public void addWhereConditionsJoinOperator(String whereConditionsJoinOperator) {
		this.whereConditionsJoinOperators.add(whereConditionsJoinOperator);
	}
}