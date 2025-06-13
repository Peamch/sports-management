package com.sports.sports_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sports.sports_management.entity.Match;
import com.sports.sports_management.repository.MatchRepository;

import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match addMatch(Match match) {
        return matchRepository.save(match);
    }

    public Match updateMatch(Long id, Match updatedMatch) {
        Optional<Match> optionalMatch = matchRepository.findById(id);
        if (optionalMatch.isPresent()) {
            Match match = optionalMatch.get();
            match.setDate(updatedMatch.getDate());
            match.setTeamA(updatedMatch.getTeamA());
            match.setTeamB(updatedMatch.getTeamB());
            match.setScoreA(updatedMatch.getScoreA());
            match.setScoreB(updatedMatch.getScoreB());
            return matchRepository.save(match);
        } else {
            throw new RuntimeException("Матч не знайдено: " + id);
        }
    }

    public boolean deleteMatch(Long id) {
        if (matchRepository.existsById(id)) {
            matchRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}