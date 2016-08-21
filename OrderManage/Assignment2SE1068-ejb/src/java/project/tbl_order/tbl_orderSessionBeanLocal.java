/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.tbl_order;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author anhnh
 */
@Local
public interface tbl_orderSessionBeanLocal {

    List searchOrder(String user, String dateFrom, String dateTo);

    void setTotal(float total, String orderID);

    void deleteOrder(String orderID);

    double getTotal(String orderID);
    
}
