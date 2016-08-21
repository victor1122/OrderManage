/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.tbl_order;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author anhnh
 */
@Remote
public interface tbl_orderSessionBeanRemote {

    List searchOrder(String user, String dateFrom, String dateTo);

    void setTotal(float total, String orderID);

    void deleteOrder(String orderID);

    double getTotal(String orderID);
    
}
