package com.app;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import com.domain.Day;
import com.domain.Match;
import com.domain.TravelingTournament;
import com.persistence.XStreamTravelingTournamentFileIO;
import com.scheduleRepairMisc.Modificator;
import com.scheduleRepairMisc.Rater;
import com.app.idMatchCollection;
/*import org.optaplanner.persistence.common.api.domain.solution.SolutionFileIO;

import com.common.app.CommonApp;
import com.domain.TravelingTournament;
import com.persistence.XStreamTravelingTournamentFileIO;
import com.swingui.TravelingTournamentPanel;
*/

public class ScheduleRepairApp {

    public static void main(String[] args) {
        String inputFile = "data/tournament_260.xml"; 
        String outputFile = "data/tournament_260_out.xml";
        
        XStreamTravelingTournamentFileIO solutionFileIO = new XStreamTravelingTournamentFileIO();
        
        //Парсинг входных данных и сохранение их в домен (TravelingTournament)
        TravelingTournament solution =  solutionFileIO.read(new File(inputFile));

        //Функция на базе жадного алгоритма, которую нужно реализовать
        doRepair(solution);
              
        //Сохранение результата работы функции doRepair в xml файл
        //Временно закомментил вывод в файл, пока doRepair не реализованна
        //solutionFileIO.write(solution, new File(outputFile));    
    }
    
    private static void doRepair(TravelingTournament solution) {
        //Создание объекта для оценки качества расписания.
        Rater rater = new Rater(solution.getLogisticGroupList());
        
        //Список  для хранения матчей, которые нужно будет переназначить
        List<Match> rescheduleMatches = new ArrayList<>();
        
        //Обрабатываем в цикле каждый матч
        for(Match match : solution.getMatchList()) {
            
            //Если матч не зафиксирован пользователем
            if(!match.getFixingState()) {
                //Сбросить дату у матча
                Modificator.setDate(solution, match, null);
                //Добавить матч в список матчей, которые подлежат переназначению
                rescheduleMatches.add(match);                
            }
        }
        
        //Получить стартовую оценку расписани (матчи которые подлежат переназначению - не учитываются)
        HardSoftScore startScore = rater.calculateScore(solution);
        
    //     //Здесь пример того, как для матча можно проверить в какой день чемпионата его можно поместить
    //     List<List<Integer>> diffsByMatchList = new ArrayList<>();
    //     for(Day day : solution.getDayList()) {
    //         for (Match match : rescheduleMatches) {
                
    //             boolean checkStatus =  Modificator.checkDate(solution, match, day);
    //             if (checkStatus) {
    //                 Modificator.setDate(solution, match, day);
    //                 HardSoftScore newScore = rater.calculateScore(solution);
    //                 int diff = newScore.getHardScore() - startScore.getHardScore();
    //                 diffsByMatchList.add(new ArrayList<Integer>());
    //                 diffsByMatchList.s  
    //         }
    //     }
    // }
    // create entity of idMatchCollection class and fill it with data
    idMatchCollection _idMatchCollection = new idMatchCollection();
    for (Match match : rescheduleMatches){
        for (Day day : solution.getDayList()) {
            if (Modificator.checkDate(solution, match, day)) {
                Modificator.setDate(solution, match, day);
                HardSoftScore newScore = rater.calculateScore(solution);
                long diff = (long) newScore.getHardScore() - (long) startScore.getHardScore();
                // add map of Day to diff to idMatchCollection with key = id of match
                _idMatchCollection.add(match.getId(), day, diff);
            }
        }
    }
    // make another entity of idMatchCollection 
    idMatchCollection _idMatchCollectionMins = new idMatchCollection();
    // make function to search for the smallest diff in idMatchCollection
    for (Match match : rescheduleMatches){
        long min = Long.MAX_VALUE;
        Day day = null;
        for (Day day_ : solution.getDayList()) {
            if (_idMatchCollection.get(match.getId(), day_) < min) {
                min = _idMatchCollection.get(match.getId(), day_);
                day = day_;
            }
        }
        // add map of Day to diff to idMatchCollectionMins with key = id of match
        _idMatchCollectionMins.add(match.getId(), day, min);
        }
    }
     
}
