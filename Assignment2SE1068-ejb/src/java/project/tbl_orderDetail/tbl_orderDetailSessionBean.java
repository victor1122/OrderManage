/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.tbl_orderDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import project.entity.TblOrder;
import project.entity.TblProduct;
import project.entity.TblorderDetail;

/**
 *
 * @author anhnh
 */
@Stateless
public class tbl_orderDetailSessionBean implements tbl_orderDetailSessionBeanLocal, tbl_orderDetailSessionBeanRemote {

    @PersistenceContext(unitName = "Assignment2SE1068-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Map<String, TblorderDetail> showDetail(String orderID) {
        Query query = em.createNamedQuery("TblorderDetail.findByOrderID");
        query.setParameter("orderID", orderID);
        List<TblorderDetail> list1 = query.getResultList();
        float orderTotal = 0;
        Map<String, TblorderDetail> details = null;
        for (TblorderDetail detail : list1) {
            orderTotal += detail.getTotal();
            TblProduct product = em.find(TblProduct.class, detail.getProductID());
            String proName = product.getProductName();
            if(details==null){
                details = new HashMap<String, TblorderDetail>();
            }
            details.put(proName, detail);

        }
        TblOrder order = em.find(TblOrder.class, orderID);
        if (order != null) {
            order.setTotal(orderTotal);
            em.merge(order);
        }
        return details;
    }

    @Override
    public boolean updateQuantity(int quantity, double unitPrice, int id) {
        TblorderDetail detail = em.find(TblorderDetail.class, id);
        if (detail != null) {
            double total = quantity * unitPrice;
            detail.setQuantity(quantity);
            detail.setTotal(total);
            em.merge(detail);
            return true;
        }
        return false;
    }

    @Override
    public int getQuantity(int id) {
        TblorderDetail detail = em.find(TblorderDetail.class, id);
        if (detail != null) {
            return detail.getQuantity();
        }
        return 0;
    }

    @Override
    public void deleteProduct(int id) {
        TblorderDetail detail = em.find(TblorderDetail.class, id);
        if (detail != null) {
            em.merge(detail);
            em.remove(detail);
        }
    }

}
