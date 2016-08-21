/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.tbl_product;

import javax.ejb.Local;

/**
 *
 * @author anhnh
 */
@Local
public interface tbl_productSessionBeanLocal {

    int getStock(String id);

    boolean setStock(String id, int quantity);
    
}
