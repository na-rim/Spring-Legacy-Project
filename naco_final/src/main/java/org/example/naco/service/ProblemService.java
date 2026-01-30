package org.example.naco.service;

import org.example.naco.domain.Problem;

import java.util.List;

public interface ProblemService {
    List<Problem> list(String q, String status, String difficulty, String topic);
    Problem get(Long id);
    void create(Problem p);
    void update(Problem p);
    void delete(Long id);
    void updateStatus(Long id, String status);
    void updateSolvedTime(Long id, int solvedTimeSec);
    void finishSolve(Long id, int solvedTimeSec);
}
