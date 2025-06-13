package com.sports.sports_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sports.sports_management.entity.Tournament;
import com.sports.sports_management.entity.Match;
import com.sports.sports_management.repository.TournamentRepository;
import com.sports.sports_management.repository.MatchRepository;
import com.sports.sports_management.repository.TeamRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository,
                             MatchRepository matchRepository,
                             TeamRepository teamRepository) {
        this.tournamentRepository = tournamentRepository;
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    public Tournament addTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament updateTournament(Long id, Tournament updatedTournament) {
        Optional<Tournament> optionalTournament = tournamentRepository.findById(id);
        if (optionalTournament.isPresent()) {
            Tournament existing = optionalTournament.get();
            existing.setName(updatedTournament.getName());
            existing.setStartDate(updatedTournament.getStartDate());
            existing.setEndDate(updatedTournament.getEndDate());
            existing.setLocation(updatedTournament.getLocation());
            return tournamentRepository.save(existing);
        } else {
            throw new RuntimeException("Турнір не знайдено: " + id);
        }
    }

    public boolean deleteTournament(Long id) {
        if (tournamentRepository.existsById(id)) {
            tournamentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Match> getMatchesByTournament(Long tournamentId) {
        return matchRepository.findByTournament_Id(tournamentId);
    }

    public List<Map<String, Object>> getSchedule(Long tournamentId) {
        List<Match> matches = getMatchesByTournament(tournamentId);

        return matches.stream()
                .map(match -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("teamA", match.getTeamA().getName());
                    map.put("teamB", match.getTeamB().getName());
                    map.put("date", match.getDate());
                    return map;
                })
                .collect(Collectors.toList());
    }
}
