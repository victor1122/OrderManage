/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.tbl_orderDetail;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import project.entity.TblorderDetail;

/**
 *
 * @author anhnh
 */
@Local
public interface tbl_orderDetailSessionBeanLocal {

    Map<String, TblorderDetail> showDetail(String orderID);

    boolean updateQuantity(int quantity, double unitPrice, int id);

    int getQuantity(int id);

    void deleteProduct(int id);
    
}
