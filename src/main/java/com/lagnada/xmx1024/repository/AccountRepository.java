package com.lagnada.xmx1024.repository;

import com.lagnada.xmx1024.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.repository.annotation.RestResource;

import java.util.List;

@RestResource(path = "people")
public interface AccountRepository extends JpaRepository<Account, Long> {

    @RestResource(path = "first-name", rel = "find-by-first-name")
    @Query("SELECT a FROM Account a WHERE LOWER(a.firstName) LIKE :firstName")
    List<Account> findByFirstName(@Param("firstName") String firstName);

    @RestResource(path = "last-name", rel = "find-by-last-name")
    List<Account> findByLastName(@Param("last-name") String lastName);

}
