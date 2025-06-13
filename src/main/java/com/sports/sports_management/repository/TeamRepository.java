package com.sports.sports_management.repository;

import com.sports.sports_management.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query(value = """
        SELECT AVG(EXTRACT(YEAR FROM age(CURRENT_DATE, birth_date)))
        FROM player
        WHERE team_id = :teamId
    """, nativeQuery = true)
    Double findAveragePlayerAgeByTeamId(@Param("teamId") Long teamId);

    @Query("SELECT p.team FROM Participation p WHERE p.tournament.id = :tournamentId")
    List<Team> findByTournamentId(@Param("tournamentId") Long tournamentId);
}