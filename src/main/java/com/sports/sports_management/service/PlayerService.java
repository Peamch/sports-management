package com.sports.sports_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sports.sports_management.entity.Player;
import com.sports.sports_management.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> getPlayersByTeam(Long teamId) {
        return playerRepository.findByTeam_Id(teamId);
    }

    public Player updatePlayer(Long id, Player updatedPlayer) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            player.setName(updatedPlayer.getName());
            player.setBirthDate(updatedPlayer.getBirthDate());
            player.setPosition(updatedPlayer.getPosition());
            return playerRepository.save(player);
        } else {
            throw new RuntimeException("Ігрока не знайдено: " + id);
        }
    }

    public boolean deletePlayer(Long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<String> getPlayersStatistics() {
        return playerRepository.findAll().stream()
                .map(player -> {
                    if ("Goalkeeper".equalsIgnoreCase(player.getPosition())) {
                        return player.getName() + ": Відбито сейвів: " + (player.getStats() != null ? player.getStats() : 0);
                    } else {
                        return player.getName() + ": Забито голів: " + (player.getStats() != null ? player.getStats() : 0);
                    }
                })
                .collect(Collectors.toList());
    }
}