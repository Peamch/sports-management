package com.sports.sports_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sports.sports_management.entity.Tournament;
import com.sports.sports_management.entity.Match;
import com.sports.sports_management.entity.Team;
import com.sports.sports_management.repository.TournamentRepository;
import com.sports.sports_management.repository.MatchRepository;
import com.sports.sports_management.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;
    @Autowired
    public TeamService(TeamRepository teamRepository,
                       MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }
    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    public Team updateTeam(Long id, Team updatedTeam) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            team.setName(updatedTeam.getName());
            team.setCity(updatedTeam.getCity());
            return teamRepository.save(team);
        } else {
            throw new RuntimeException("Команда не знайдена: " + id);
        }
    }
    public boolean deleteTeam(Long id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public List<Match> getMatchesByTeam(Long teamId) {
        return matchRepository.findByTeamId(teamId);
    }
    public Double getAverageAge(Long teamId) {
        return teamRepository.findAveragePlayerAgeByTeamId(teamId);
    }
}
