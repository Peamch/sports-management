package com.sports.sports_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.sports.sports_management.entity.Team;
import com.sports.sports_management.entity.Match;
import com.sports.sports_management.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        Team createdTeam = teamService.addTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        Team updatedTeam = teamService.updateTeam(id, team);
        if (updatedTeam != null) {
            return ResponseEntity.ok(updatedTeam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        boolean deleted = teamService.deleteTeam(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/matches")
    public ResponseEntity<List<Match>> getMatchesByTeam(@PathVariable Long id) {
        List<Match> matches = teamService.getMatchesByTeam(id);
        if (matches != null && !matches.isEmpty()) {
            return ResponseEntity.ok(matches);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/average-age")
    public ResponseEntity<Double> getAverageAge(@PathVariable Long id) {
        Double averageAge = teamService.getAverageAge(id);
        if (averageAge != null) {
            return ResponseEntity.ok(averageAge);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}