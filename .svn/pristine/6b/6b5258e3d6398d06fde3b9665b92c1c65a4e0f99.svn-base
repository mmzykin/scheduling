package com.domain;

import java.util.TreeMap;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.solution.cloner.DeepPlanningClone;
import org.optaplanner.core.api.domain.variable.CustomShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableReference;

import com.common.domain.AbstractPersistable;
import com.solver.TeamAssignmentUpdatingVariableListener;

@PlanningEntity()
public class TeamAssignment extends AbstractPersistable {
    @CustomShadowVariable(variableListenerClass = TeamAssignmentUpdatingVariableListener.class,
            sources = { @PlanningVariableReference(entityClass = Match.class, variableName = "day") })
    @DeepPlanningClone

    TreeMap<Integer, Match> matchMap;

    public TeamAssignment() {
        matchMap = new TreeMap<>();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMatchMap(TreeMap<Integer, Match> matchMap) {
        this.matchMap = matchMap;
    }

    public TreeMap<Integer, Match> getMatchMap() {
        return matchMap;
    }

    public void setMatch(Match match) {
        matchMap.put(match.getDay().getIndex(), match);
    }

}
