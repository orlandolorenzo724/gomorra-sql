package co.aurasphere.gomorrasql.states.where;

import co.aurasphere.gomorrasql.constants.Keywords;
import co.aurasphere.gomorrasql.model.MannaggGiudException;
import co.aurasphere.gomorrasql.model.QueryInfo;
import co.aurasphere.gomorrasql.model.WhereCondition;
import co.aurasphere.gomorrasql.states.AbstractState;
import co.aurasphere.gomorrasql.states.GreedyMatchKeywordState;
import static co.aurasphere.gomorrasql.constants.Operators.*;

/**
 * State for continuing the last WHERE subclause in the format "field operator
 * value" by inserting the operator.
 * 
 * @author Donato Rimenti
 *
 */
public class WhereOperatorState extends AbstractState {

	private final WhereCondition condition;

	public WhereOperatorState(QueryInfo queryInfo, WhereCondition condition) {
		super(queryInfo);
		this.condition = condition;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws MannaggGiudException {
		if (Keywords.WHERE_OPERATORS.contains(token) || token.equalsIgnoreCase(Keywords.IS_KEYWORD)) {
			if (token.equalsIgnoreCase(Keywords.IS_NOT_KEYWORDS[0])) {
				condition.setOperator(OPERATOR_IS_NOT);
				return createGreedyMatchState();
			}

			condition.setOperator(token.equalsIgnoreCase(Keywords.IS_KEYWORD) ? OPERATOR_IS : token);
			return new WhereValueState(queryInfo, condition);
		}

		throw new MannaggGiudException(Keywords.WHERE_OPERATORS, token);
	}

	private AbstractState createGreedyMatchState() {
		return new GreedyMatchKeywordState(
				queryInfo,
				Keywords.IS_NOT_KEYWORDS,
				q -> new WhereValueState(q, condition)
		);
	}
}