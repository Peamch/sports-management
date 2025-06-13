package com.sports.sports_management.controller;

import com.sports.sports_management.entity.Match;
import com.sports.sports_management.entity.Participation;
import com.sports.sports_management.entity.Team;
import com.sports.sports_management.entity.Tournament;
import com.sports.sports_management.service.ParticipationService;
import com.sports.sports_management.repository.TournamentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/participations")
public class ParticipationController {

    private final ParticipationService participationService;

    @Autowired
    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @PostMapping
    public ResponseEntity<Participation> addParticipation(@RequestBody Participation participation) {
        Participation createdParticipation = participationService.addParticipation(participation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdParticipation);
    }

    @GetMapping("/tournament/{tournamentId}/teams")
    public List<Team> getTeamsByTournament(@PathVariable Long tournamentId) {
        return participationService.getTeamsByTournament(tournamentId);
    }

    @GetMapping("/team/{teamId}/tournaments")
    public ResponseEntity<List<Tournament>> getTournamentsByTeam(@PathVariable Long teamId) {
        List<Tournament> tournaments = participationService.getTournamentsByTeam(teamId);
        if (tournaments != null && !tournaments.isEmpty()) {
            return ResponseEntity.ok(tournaments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}