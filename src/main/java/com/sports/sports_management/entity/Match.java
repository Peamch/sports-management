package com.sports.sports_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stage;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    @JsonIgnore
    private Tournament tournament;

    @JsonProperty("tournamentId")
    public Long getTournamentId() {
        return tournament != null ? tournament.getId() : null;
    }

    @ManyToOne
    @JoinColumn(name = "team_a_id")
    @JsonIgnore
    private Team teamA;

    @ManyToOne
    @JoinColumn(name = "team_b_id")
    @JsonIgnore
    private Team teamB;

    @JsonProperty("teamAId")
    public Long getTeamAId() {
        return teamA != null ? teamA.getId() : null;
    }

    @JsonProperty("teamBId")
    public Long getTeamBId() {
        return teamB != null ? teamB.getId() : null;
    }

    private LocalDate date;
    private Integer scoreA;
    private Integer scoreB;
}
