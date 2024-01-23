package com.bfu.email.infra;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bfu.email.domain.Email;

public interface EmailJpaRepository extends JpaRepository<Email, UUID> {

}
