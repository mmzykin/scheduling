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

import com.app.MatchDayDiffCount;
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
        solutionFileIO.write(solution, new File(outputFile));    
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
    while (!rescheduleMatches.isEmpty()){
        MatchDayDiffCount besMatchDayDiffCount = new MatchDayDiffCount();
        HardSoftScore startScore = rater.calculateScore(solution);
        for (Match match : rescheduleMatches){
            MatchDayDiffCount currentMatchDayDiffCount = new MatchDayDiffCount( match, null, -Integer.MIN_VALUE, 0);
            for (Day day : solution.getDayList()) {
                if (Modificator.checkDate(solution, match, day)) {
                    Modificator.setDate(solution, match, day);
                    HardSoftScore newScore = rater.calculateScore(solution);
                    Integer diff = (Integer) newScore.getHardScore() - (Integer) startScore.getHardScore();
                    if (diff > currentMatchDayDiffCount.getDayDiff()) {
                        currentMatchDayDiffCount.setDayDiff(diff);
                        currentMatchDayDiffCount.setDay(day);
                    }
                }
                currentMatchDayDiffCount.countVars++;
                Modificator.setDate(solution, match, null);
            }
            if (currentMatchDayDiffCount.getDayDiff() > besMatchDayDiffCount.getDayDiff()) {
                besMatchDayDiffCount = currentMatchDayDiffCount;
            }
            else if (currentMatchDayDiffCount.getDayDiff() == besMatchDayDiffCount.getDayDiff()) {
                if (currentMatchDayDiffCount.getCountVars() < besMatchDayDiffCount.getCountVars()) {
                    besMatchDayDiffCount = currentMatchDayDiffCount;
                }
            }
        }
        Modificator.setDate(solution, besMatchDayDiffCount.getMatch(), besMatchDayDiffCount.getDay());
        // delete match from besMatchDayDiffCount.getMatch() from rescheduleMatches
        rescheduleMatches.remove(besMatchDayDiffCount.getMatch());
        System.out.println("Match " + besMatchDayDiffCount.getMatch().getId() + " is rescheduled to " + besMatchDayDiffCount.getDay().getId());
        System.out.println(rescheduleMatches.size());
        }
    }     
}
