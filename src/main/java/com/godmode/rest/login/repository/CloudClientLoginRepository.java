package com.godmode.rest.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.godmode.rest.login.CloudClientUser;

@Repository
public interface  CloudClientLoginRepository extends JpaRepository<CloudClientUser, String>{
}
