package com.sports.sports_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sports.sports_management.entity.Participation;
import com.sports.sports_management.entity.Team;
import com.sports.sports_management.entity.Tournament;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    @Query("SELECT p.team FROM Participation p WHERE p.tournament.id = :tournamentId")
    List<Team> findTeamsByTournamentId(Long tournamentId);

    @Query("SELECT p.tournament FROM Participation p WHERE p.team.id = :teamId")
    List<Tournament> findTournamentsByTeamId(Long teamId);
}
