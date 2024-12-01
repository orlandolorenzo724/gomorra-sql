package co.aurasphere.gomorrasql.states.where;

import co.aurasphere.gomorrasql.constants.Keywords;
import co.aurasphere.gomorrasql.model.MannaggGiudException;
import co.aurasphere.gomorrasql.model.QueryInfo;
import co.aurasphere.gomorrasql.model.WhereCondition;
import co.aurasphere.gomorrasql.states.AbstractState;

import static co.aurasphere.gomorrasql.constants.Values.VALUE_NULL;

/**
 * State for completing the last WHERE subclause in the format "field operator
 * value" by inserting the value.
 * 
 * @author Donato Rimenti
 *
 */
public class WhereValueState extends AbstractState {

	private final WhereCondition condition;

	public WhereValueState(QueryInfo queryInfo, WhereCondition condition) {
		super(queryInfo);
		this.condition = condition;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws MannaggGiudException {
		if(token.equalsIgnoreCase(Keywords.NULL_KEYWORD)) {
			condition.setValue(VALUE_NULL);
		} else {
			condition.setValue(token);
		}
		queryInfo.addWhereCondition(condition);
		return new WhereJoinState(queryInfo);
	}
	
}