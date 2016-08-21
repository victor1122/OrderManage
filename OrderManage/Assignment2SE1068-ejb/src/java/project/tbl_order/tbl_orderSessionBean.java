/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.tbl_order;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import project.entity.TblOrder;

/**
 *
 * @author anhnh
 */
@Stateless
public class tbl_orderSessionBean implements tbl_orderSessionBeanLocal, tbl_orderSessionBeanRemote {
    @PersistenceContext(unitName = "Assignment2SE1068-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List searchOrder(String user, String dateFrom, String dateTo) {
        String sql = "SELECT t FROM TblOrder t WHERE t.customerID = :customerID and t.orderDate between '"
                + dateFrom
                + "' and '"
                + dateTo
                + " 23:59:59'";
        Query query = em.createQuery(sql);
        query.setParameter("customerID", user);
        List list = query.getResultList();
        return list;
    }

    @Override
    public void setTotal(float total, String orderID) {
        TblOrder order = em.find(TblOrder.class, orderID);
        if(order!=null){
            order.setTotal(total);
            em.merge(order);
        }
    }
    
    @Override
    public void deleteOrder(String orderID) {
        TblOrder order = em.find(TblOrder.class, orderID);
        if(order!=null){
            em.remove(order);
            em.flush();
        }
    }

    @Override
    public double getTotal(String orderID) {
        TblOrder order = em.find(TblOrder.class, orderID);
        if(order!=null){
            return order.getTotal();
        }
        return 0;
    }
    
    
    
    
    
    
}
