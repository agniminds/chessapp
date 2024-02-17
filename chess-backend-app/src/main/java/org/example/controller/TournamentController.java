package org.example.controller;

import org.example.entity.ChessTournament;
import org.example.repository.TournamentRepository;
import org.example.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TournamentController {
    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    TournamentService tournamentService;

    @GetMapping("tournaments")
    public List<ChessTournament> getTournaments() {
        return tournamentRepository.findAll();
    }

    @GetMapping("tournaments/{region}/{month}")
    public Map<String, List<ChessTournament>> getTournamentsByClusters(@PathVariable String region, @PathVariable String month) {
        return tournamentService.getTournamentsGroupedByCluster(region, month);
    }



}
