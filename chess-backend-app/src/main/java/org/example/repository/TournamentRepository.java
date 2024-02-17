package org.example.repository;

import org.example.entity.ChessTournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<ChessTournament,Integer> {

    @Query("SELECT t FROM ChessTournament t WHERE t.clusterName LIKE CONCAT(:region, '|', :month, '%')")
    List<ChessTournament> findByRegionAndMonth(@Param("region") String region, @Param("month") String month);


}
