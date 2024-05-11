package com.example.products.repositories;

import com.example.products.domain.entities.Ration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RationRepository extends JpaRepository<Ration, Long> {
    @Query(value = "select * from ration r join product p on p.id = r.product_id" +
            " where person_id = :personId and created >= :startDate and created < :endDate", nativeQuery = true)
    List<Ration> findByPersonIdAndCreated(Long personId, Date startDate, Date endDate);
}
