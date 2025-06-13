package com.sports.sports_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sports.sports_management.entity.Tournament;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    @Query("SELECT p.tournament FROM Participation p WHERE p.team.id = :teamId")
    List<Tournament> findByTeamId(Long teamId);
}
