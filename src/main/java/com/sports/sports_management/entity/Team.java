package com.sports.sports_management.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @OneToMany(mappedBy = "team")
    private List<Participation> participations;

    @OneToMany(mappedBy = "teamA")
    private List<Match> matchesAsTeamA;

    @OneToMany(mappedBy = "teamB")
    private List<Match> matchesAsTeamB;
}