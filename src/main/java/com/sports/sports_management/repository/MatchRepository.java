package com.sports.sports_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sports.sports_management.entity.Match;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByTournament_Id(Long tournamentId);
    List<Match> findByTeamA_IdOrTeamB_Id(Long teamAId, Long teamBId);
    default List<Match> findByTeamId(Long teamId) {
        return findByTeamA_IdOrTeamB_Id(teamId, teamId);
    }
}
