package co.aurasphere.gomorrasql.states.where;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.aurasphere.gomorrasql.Keywords;
import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;
import co.aurasphere.gomorrasql.model.QueryInfo.QueryType;
import co.aurasphere.gomorrasql.states.AbstractState;
import co.aurasphere.gomorrasql.states.GreedyMatchKeywordState;
import co.aurasphere.gomorrasql.states.select.SelectJoinTableNameState;

public class OptionalWhereState extends AbstractState {

	public OptionalWhereState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		List<String> expectedKeywords = new ArrayList<>(Arrays.asList(Keywords.WHERE_KEYWORD, Keywords.END_QUERY_KEYWORD));
		if (token.equalsIgnoreCase(Keywords.WHERE_KEYWORD)) {
			return new WhereFieldState(queryInfo);
		}
		if (token.equalsIgnoreCase(Keywords.END_QUERY_KEYWORD)) {
			return this;
		}
		if (queryInfo.getType().equals(QueryType.SELECT)) {
			// TODO: skip and limit
			expectedKeywords.add(Keywords.JOIN_KEYWORDS[0]);
			if (token.equalsIgnoreCase(Keywords.JOIN_KEYWORDS[0])) {
				return new GreedyMatchKeywordState(queryInfo, Keywords.JOIN_KEYWORDS, SelectJoinTableNameState::new);
			}
		}
		throw new CaggiaFaException(expectedKeywords, token);
	}
	
	@Override
	public boolean isFinalState() {
		return true;
	}

}