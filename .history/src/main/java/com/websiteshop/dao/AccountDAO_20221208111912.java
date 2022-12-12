package com.websiteshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Jpa21Utils;

import com.websiteshop.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String> {

    @Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.roleId IN ('DIRE', 'STAF')")
    List<Account> getAdministratiors();

    @Query(value = "select * from Accounts where Username like '?1", nativeQuery = true)
    public Account findByUsernameId(String username);

    public Account resetPasswordToken(String token);

}
