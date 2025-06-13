package com.sports.sports_management.service;

import com.sports.sports_management.entity.Match;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sports.sports_management.entity.Tournament;
import com.sports.sports_management.entity.Team;
import com.sports.sports_management.entity.Participation;
import com.sports.sports_management.repository.ParticipationRepository;
import com.sports.sports_management.repository.TournamentRepository;
import com.sports.sports_management.repository.MatchRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public ParticipationService(ParticipationRepository participationRepository,
                                TournamentRepository tournamentRepository,
                                MatchRepository matchRepository) {
        this.participationRepository = participationRepository;
        this.tournamentRepository = tournamentRepository;
        this.matchRepository = matchRepository;
    }

    public Participation addParticipation(Participation participation) {
        return participationRepository.save(participation);
    }

    public List<Team> getTeamsByTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("Турнір не знайдено"));

        Set<Team> teams = new HashSet<>();

        for (Match match : tournament.getMatches()) {
            if (match.getTeamA() != null) {
                teams.add(match.getTeamA());
            }
            if (match.getTeamB() != null) {
                teams.add(match.getTeamB());
            }
        }

        return new ArrayList<>(teams);
    }

    public List<Tournament> getTournamentsByTeam(Long teamId) {
        List<Match> matches = matchRepository.findByTeamA_IdOrTeamB_Id(teamId, teamId);

        Set<Tournament> tournaments = new HashSet<>();
        for (Match match : matches) {
            if (match.getTournament() != null) {
                tournaments.add(match.getTournament());
            }
        }
        return new ArrayList<>(tournaments);
    }
}