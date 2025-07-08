package com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByClientId(String clientId);
}
