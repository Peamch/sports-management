package com.sports.sports_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.sports.sports_management.entity.Player;
import com.sports.sports_management.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player createdPlayer = playerService.addPlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Player>> getPlayersByTeam(@PathVariable Long teamId) {
        List<Player> players = playerService.getPlayersByTeam(teamId);
        if (players != null && !players.isEmpty()) {
            return ResponseEntity.ok(players);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        Player updatedPlayer = playerService.updatePlayer(id, player);
        if (updatedPlayer != null) {
            return ResponseEntity.ok(updatedPlayer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        boolean deleted = playerService.deletePlayer(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/statistics")
    public ResponseEntity<Object> getPlayersStatistics() {
        Object statistics = playerService.getPlayersStatistics();
        return ResponseEntity.ok(statistics);
    }
}