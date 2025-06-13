package com.sports.sports_management.controller;

import com.sports.sports_management.entity.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.sports.sports_management.entity.Tournament;
import com.sports.sports_management.service.TournamentService;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping
    public ResponseEntity<Tournament> addTournament(@RequestBody Tournament tournament) {
        Tournament createdTournament = tournamentService.addTournament(tournament);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTournament);
    }

    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        return ResponseEntity.ok(tournaments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tournament> updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        Tournament updatedTournament = tournamentService.updateTournament(id, tournament);
        if (updatedTournament != null) {
            return ResponseEntity.ok(updatedTournament);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        boolean deleted = tournamentService.deleteTournament(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/matches")
    public ResponseEntity<List<Match>> getMatchesByTournament(@PathVariable Long id) {
        List<Match> matches = tournamentService.getMatchesByTournament(id);
        if (matches != null && !matches.isEmpty()) {
            return ResponseEntity.ok(matches);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/schedule")
    public ResponseEntity<Object> getSchedule(@PathVariable Long id) {
        Object schedule = tournamentService.getSchedule(id);
        if (schedule != null) {
            return ResponseEntity.ok(schedule);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}