package com.app;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;



import com.domain.Day;
import com.domain.LogisticGroup;

public class idMatchCollection{
    Map<Long, Map<Day, Long>> idMatchCollection;
    public idMatchCollection(){
        idMatchCollection = new HashMap<>();
    }
    public void add(Long id, Day day, Long matchId){
        if(!idMatchCollection.containsKey(id)){
            idMatchCollection.put(id, new HashMap<>());
        }
        idMatchCollection.get(id).put(day, matchId);
    }
    public Long get(Long id, Day day){
        if(!idMatchCollection.containsKey(id)){
            return null;
        }
        return idMatchCollection.get(id).get(day);
    }
    public void remove(Long id, Day day){
        if(!idMatchCollection.containsKey(id)){
            return;
        }
        idMatchCollection.get(id).remove(day);
    }
    public boolean contains(Long id){
        return idMatchCollection.containsKey(id);
    }
    public boolean contains(Long id, Day day){
        if(!idMatchCollection.containsKey(id)){
            return false;
        }
        return idMatchCollection.get(id).containsKey(day);
    }
    public void clear(){
        idMatchCollection.clear();
    }
    public void clear(Long id){
        idMatchCollection.remove(id);
    }
    public void clear(Long id, Day day){
        if(!idMatchCollection.containsKey(id)){
            return;
        }
        idMatchCollection.get(id).remove(day);
    }
    public void clear(Long id, List<Day> days){
        if(!idMatchCollection.containsKey(id)){
            return;
        }
        for(Day day: days){
            idMatchCollection.get(id).remove(day);
        }
    }
    public void clear(List<Long> ids){
        for(Long id: ids){
            idMatchCollection.remove(id);
        }
    }
    public void clear(List<Long> ids, List<Day> days){
        for(Long id: ids){
            for(Day day: days){
                idMatchCollection.get(id).remove(day);
            }
        }
    }
    // get day by id
    public List<Day> getDays(Long id){
        if(!idMatchCollection.containsKey(id)){
            return null;
        }
        List<Day> days = new ArrayList<>();
        for(Day day : idMatchCollection.get(id).keySet()){
            if(idMatchCollection.get(id).containsKey(day)){
                days.add(day);
            }
        }
        return days;
    }
    // get id by day
    public List<Long> getIds(Day day){
        List<Long> ids = new ArrayList<>();
        for(Long id : idMatchCollection.keySet()){
            if(idMatchCollection.get(id).containsKey(day)){
                ids.add(id);
            }
        }
        return ids;
    }
    



}