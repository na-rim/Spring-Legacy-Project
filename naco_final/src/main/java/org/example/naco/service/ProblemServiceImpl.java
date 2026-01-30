package org.example.naco.service;

import org.example.naco.domain.Problem;
import org.example.naco.mapper.ProblemMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {
    private final ProblemMapper mapper;

    public ProblemServiceImpl(ProblemMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Problem> list(String q, String status, String difficulty, String topic) {
        return mapper.findAll(q, status, difficulty, topic);
    }

    @Override
    public Problem get(Long id) {
        return mapper.findById(id);
    }

    @Override
    public void create(Problem p) {
        if (p.getStatus() == null || p.getStatus().trim().isEmpty()) {
            p.setStatus("TODO");
        }
        mapper.insert(p);
    }

    @Override
    public void update(Problem p) {
        mapper.update(p);
    }

    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }

    @Override
    public void updateStatus(Long id, String status) {
        mapper.updateStatus(id, status);
    }

    @Override
    public void updateSolvedTime(Long id, int solvedTimeSec) {
        mapper.updateSolvedTime(id, solvedTimeSec);
    }

    @Override
    public void finishSolve(Long id, int solvedTimeSec) {
        mapper.finishSolve(id, solvedTimeSec);
    }
}
