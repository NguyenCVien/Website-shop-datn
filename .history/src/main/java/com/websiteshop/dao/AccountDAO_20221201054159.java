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

    public void changePassword(String username, String oldPassword, String newPassword)
        throws Exception {
        EntityManager em = JpaUtils.getTransaction();

        EntityTransaction trans = em.getTransaction();

        String jpql = "select u from Accounts where u.username = :username and u.password = :password" ;

        try {
            trans.begin();
            TypedQuery<Account> query = em.createQuery(jpql, Account.class);
            query.setParameter("username", username);
            query.setParameter("password", oldPassword);

            Account account = query.getSingleResult();

            if(account == null) {
                throw new exception("Mật khẩu hoặc tài khoản không đúng!");
            }
            account.setPassword(newPassword);

            em.merge(account);

            trans.commit();

        } catch (Exception e) {
            trans.rollback();

            throw e;
        }finally{
            em.close();
        }
    }
}
