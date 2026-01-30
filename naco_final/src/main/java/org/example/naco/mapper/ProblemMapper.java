package org.example.naco.mapper;

import org.example.naco.domain.Problem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProblemMapper {
    List<Problem> findAll(@Param("q") String q,
                          @Param("status") String status,
                          @Param("difficulty") String difficulty,
                          @Param("topic") String topic);

    Problem findById(@Param("id") Long id);

    int insert(Problem p);

    int update(Problem p);

    int delete(@Param("id") Long id);

    int updateStatus(@Param("id") Long id, @Param("status") String status);

    int updateSolvedTime(@Param("id") Long id, @Param("solvedTimeSec") int solvedTimeSec);

    int finishSolve(@Param("id") Long id, @Param("solvedTimeSec") int solvedTimeSec);
}
