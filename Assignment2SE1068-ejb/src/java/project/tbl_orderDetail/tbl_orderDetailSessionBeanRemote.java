/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.tbl_orderDetail;

import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import project.entity.TblorderDetail;

/**
 *
 * @author anhnh
 */
@Remote
public interface tbl_orderDetailSessionBeanRemote {

    Map<String, TblorderDetail> showDetail(String orderID);

    boolean updateQuantity(int quantity, double unitPrice, int id);

    int getQuantity(int id);

    void deleteProduct(int id);
    
}
