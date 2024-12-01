package co.aurasphere.gomorrasql.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a statement in a where condition of a GomorraSQL query.
 * 
 * @author Donato Rimenti
 *
 */
@Getter
public class WhereCondition {

	private final String field;
	@Setter
	private String value;
	@Setter
	private String operator;

	public WhereCondition(String field) {
		this.field = field;
	}

}
