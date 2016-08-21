/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.tbl_account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import project.entity.TblAccount;

/**
 *
 * @author anhnh
 */
@Stateless
public class tbl_accountSessionBean implements tbl_accountSessionBeanLocal, tbl_accountSessionBeanRemote {

    @PersistenceContext(unitName = "Assignment2SE1068-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public TblAccount checklogin(String Username, String Password) {
        String sql = "SELECT t FROM TblAccount t WHERE t.accountID = :accountID and t.password = :password";
        Query query = em.createQuery(sql);
        query.setParameter("accountID", Username);
        query.setParameter("password", Password);
        try {
            TblAccount acc = (TblAccount) query.getSingleResult();
            return acc;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public String insertAccount(TblAccount account) {
        String userID = account.getAccountID();
        String email = account.getEmail();
        Query query = em.createNamedQuery("TblAccount.findByEmail");
        query.setParameter("email", email);
        try {
            query.getSingleResult();
            return "UNIQUE";
        } catch (NoResultException e) {
        }
        TblAccount acc = em.find(TblAccount.class, userID);
        if (acc != null) {
            return "PRIMARY";
        } else {
            em.persist(account);
            em.flush();
            return "SUCCESS";
        }
    }

    @Override
    public String searchAccount(String UserID) {
        Query query = em.createNamedQuery("TblAccount.findByAccountID");
        try {
            TblAccount acc = (TblAccount) query.getSingleResult();
            return acc.getAccountID();
        } catch (NoResultException e) {
            return null;
        }
    }

}
