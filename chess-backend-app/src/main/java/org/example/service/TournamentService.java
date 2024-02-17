package org.example.service;


import org.example.entity.ChessTournament;
import org.example.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TournamentService {

    @Autowired
    TournamentRepository tournamentRepository;

    public Map<String, List<ChessTournament>> getTournamentsGroupedByCluster(String region, String month) {
        // Retrieve tournaments based on region and month
        List<ChessTournament> filteredTournaments = tournamentRepository.findByRegionAndMonth(region, month);

        // Group tournaments by the entire cluster label
        Map<String, List<ChessTournament>> clusterMap = new HashMap<>();
        for (ChessTournament tournament : filteredTournaments) {
            String clusterLabel = tournament.getClusterName();
            clusterMap.computeIfAbsent(clusterLabel, k -> new ArrayList<>()).add(tournament);
        }

        return clusterMap;
    }





}
