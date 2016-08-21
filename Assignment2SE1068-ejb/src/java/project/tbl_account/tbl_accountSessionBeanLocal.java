/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.tbl_account;

import javax.ejb.Local;
import project.entity.TblAccount;

/**
 *
 * @author anhnh
 */
@Local
public interface tbl_accountSessionBeanLocal {

    TblAccount checklogin(String Username, String Password);

    String insertAccount(TblAccount account);

    String searchAccount(String UserID);
    
}
