
package com.solver.termination;

import java.util.Arrays;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.phase.scope.AbstractPhaseScope;
import org.optaplanner.core.impl.score.ScoreUtils;
import org.optaplanner.core.impl.solver.scope.SolverScope;
import org.optaplanner.core.impl.solver.termination.AbstractTermination;
import org.optaplanner.core.impl.solver.termination.Termination;
import org.optaplanner.core.impl.solver.thread.ChildThreadType;

import com.domain.TravelingTournament;

public class CustomTermination extends AbstractTermination<TravelingTournament> {
    /* BestScoreTermination<TravelingTournament> { */

    private int levelsSize;
    private static Score bestScoreLimit;
    private static Score startingInitializedScore;
    private double[] timeGradientWeightNumbers;

    static {
        bestScoreLimit = HardSoftScore.of(-20, -800000);
    }

    public CustomTermination() {
        levelsSize = bestScoreLimit.toLevelNumbers().length;
        timeGradientWeightNumbers = new double[levelsSize - 1];
        Arrays.fill(timeGradientWeightNumbers, 0.50); // Number pulled out of thin air
    }

    public static void setBestScoreLimit(Score pBestScoreLimit) {
        bestScoreLimit = pBestScoreLimit;
    }

    public static void setStartingInitializedScore(Score pStartingInitializedScore) {
        startingInitializedScore = pStartingInitializedScore;
    }

    // ************************************************************************
    // Terminated methods
    // ************************************************************************

    @Override
    public boolean isSolverTerminated(SolverScope<TravelingTournament> solverScope) {
        return isTerminated(solverScope.isBestSolutionInitialized(), solverScope.getBestScore());
    }

    @Override
    public boolean isPhaseTerminated(AbstractPhaseScope<TravelingTournament> phaseScope) {
        return isTerminated(phaseScope.isBestSolutionInitialized(), (Score) phaseScope.getBestScore());
    }

    protected boolean isTerminated(boolean bestSolutionInitialized, Score bestScore) {
        return bestSolutionInitialized && bestScore.compareTo(bestScoreLimit) >= 0;
    }

    // ************************************************************************
    // Time gradient methods
    // ************************************************************************

    @Override
    public double calculateSolverTimeGradient(SolverScope<TravelingTournament> solverScope) {
        Score bestScore = solverScope.getBestScore();
        return calculateTimeGradient(startingInitializedScore, bestScoreLimit, bestScore);
    }

    @Override
    public double calculatePhaseTimeGradient(AbstractPhaseScope<TravelingTournament> phaseScope) {
        Score bestScore = phaseScope.getBestScore();
        return calculateTimeGradient(startingInitializedScore, bestScoreLimit, bestScore);
    }

    protected double calculateTimeGradient(Score startScore, Score endScore, Score score) {
        Score totalDiff = endScore.subtract(startScore);
        Number[] totalDiffNumbers = totalDiff.toLevelNumbers();
        Score scoreDiff = score.subtract(startScore);
        Number[] scoreDiffNumbers = scoreDiff.toLevelNumbers();
        if (scoreDiffNumbers.length != totalDiffNumbers.length) {
            throw new IllegalStateException("The startScore (" + startScore + "), endScore (" + endScore
                    + ") and score (" + score + ") don't have the same levelsSize.");
        }
        return ScoreUtils.calculateTimeGradient(totalDiffNumbers, scoreDiffNumbers, timeGradientWeightNumbers,
                levelsSize);
    }

    // ************************************************************************
    // Other methods
    // ************************************************************************

    @Override
    public Termination<TravelingTournament> createChildThreadTermination(SolverScope<TravelingTournament> solverScope,
            ChildThreadType childThreadType) {
        // TODO FIXME through some sort of solverlistener and async behaviour...
        throw new UnsupportedOperationException("This terminationClass (" + getClass()
                + ") does not yet support being used in child threads of type (" + childThreadType + ").");
    }

    @Override
    public String toString() {
        return "BestScore(" + bestScoreLimit + ")";
    }

}
