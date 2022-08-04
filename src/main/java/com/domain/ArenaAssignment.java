package com.domain;

import java.util.TreeMap;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.solution.cloner.DeepPlanningClone;
import org.optaplanner.core.api.domain.variable.CustomShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableReference;

import com.common.domain.AbstractPersistable;
import com.solver.TeamAssignmentUpdatingVariableListener;

@PlanningEntity()
public class ArenaAssignment extends AbstractPersistable {
    @CustomShadowVariable(variableListenerClass = TeamAssignmentUpdatingVariableListener.class,
            sources = { @PlanningVariableReference(entityClass = Match.class, variableName = "day") })
    @DeepPlanningClone

    TreeMap<String, Match> matchMap;

    public ArenaAssignment() {
        matchMap = new TreeMap<>();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMatchMap(TreeMap<String, Match> matchMap) {
        this.matchMap = matchMap;
    }

    public TreeMap<String, Match> getMatchMap() {
        return matchMap;
    }

    private String getKey(Match match) {

        String key = String.format("%05d", match.getDay().getIndex()) + " " + match.getId();
        return key;
    }

    public void setMatch(Match match) {
        matchMap.put(getKey(match), match);
    }

    public void removeMatch(Match match) {
        String key = getKey(match);
        if (matchMap.containsKey(key)) {
            matchMap.remove(key);
        }
    }

}
