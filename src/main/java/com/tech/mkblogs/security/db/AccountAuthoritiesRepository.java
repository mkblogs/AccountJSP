package com.tech.mkblogs.security.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.mkblogs.security.db.model.Authorities;

@Repository
public interface AccountAuthoritiesRepository extends JpaRepository<Authorities, Integer> {

}
