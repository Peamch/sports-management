package com.sports.sports_management.repository;

import com.sports.sports_management.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeam_Id(Long teamId);
}