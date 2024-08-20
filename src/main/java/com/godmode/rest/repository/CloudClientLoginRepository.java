package com.godmode.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.godmode.rest.model.CloudClientUser;

@Repository
public interface  CloudClientLoginRepository extends JpaRepository<CloudClientUser, String>{
}
