package com.app;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;



import com.domain.Day;
import com.domain.LogisticGroup;

public class idMatchMapClass {
    List<Map<Day, Integer>> idMatchMap;
    public idMatchMapClass(List<Map<Day, Integer>> idMatchMap) {
        this.idMatchMap = idMatchMap;
    }

    public void add (int _match, Day _day, Integer _delta){
        if (idMatchMap.contains(_match))
        {
            idMatchMap.get(_match).put(_day, _delta);
        }
        else {
            Map<Day, Integer> _temporary = new HashMap<>();
            _temporary.put(_day, _delta);
            idMatchMap.add(_temporary);
        }
        
    } 
    public idMatchMapClass() {
        idMatchMap = new ArrayList<Map<Day,Integer>>(); 
    }
    Integer id;    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public List<Map<Day, Integer>> getIdMatchMap() {
        return idMatchMap;
    }
    
    public void setIdMatchMap(List<Map<Day, Integer>> idMatchMap) {
        this.idMatchMap = idMatchMap;
    }
    
    public idMatchMapClass(Integer id) {
        this.id = id;
    }
    public Integer getMinDelta(int _match) {
        Integer _minDelta = 1000000000;
        for (Map<Day, Integer> _dayDelta : idMatchMap) {
            if (_dayDelta.containsKey(_match)) {
                if (_dayDelta.get(_match) < _minDelta) {
                    _minDelta = _dayDelta.get(_match);
                }
            }
        }
        return _minDelta;
    }
    public void deleteOtherDeltas(int _match) {
        Integer _minDelta = getMinDelta(_match);
        for (Map<Day, Integer> _dayDelta : idMatchMap) {
            if (_dayDelta.containsKey(_match)) {
                if (_dayDelta.get(_match) != _minDelta) {
                    _dayDelta.remove(_match);
                }
            }
        }
    }
    public class idMatchMapClassElement {

        public int getMatchId() {
            return 0;
        }

        public Integer getDayId() {
            return null;
        }
// make getDiff method to return delta for that match and day

        public Integer getDiff() {
            return null;
        }
    }
    public idMatchMapClassElement[] getList() {
        return null;
    }
    
}
