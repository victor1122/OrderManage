/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.tbl_product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import project.entity.TblProduct;

/**
 *
 * @author anhnh
 */
@Stateless
public class tbl_productSessionBean implements tbl_productSessionBeanLocal, tbl_productSessionBeanRemote {
    @PersistenceContext(unitName = "Assignment2SE1068-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public int getStock(String id) {
        TblProduct product = em.find(TblProduct.class, id);
        if(product!=null){
            return product.getQuantity();
        }
        return 0;
    }

    @Override
    public boolean setStock(String id, int quantity) {
        TblProduct product = em.find(TblProduct.class, id);
        if(product!=null){
            product.setQuantity(quantity);
            em.merge(product);
            return true;
        }
        return false;
    }
    
    

    
    
    
}
