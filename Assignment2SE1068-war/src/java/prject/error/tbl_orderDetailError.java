/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prject.error;

import java.io.Serializable;

/**
 *
 * @author anhnh
 */
public class tbl_orderDetailError implements Serializable {
    private String wrongQuantity;

    public tbl_orderDetailError() {
    }

    /**
     * @return the wrongQuantity
     */
    public String getWrongQuantity() {
        return wrongQuantity;
    }

    /**
     * @param wrongQuantity the wrongQuantity to set
     */
    public void setWrongQuantity(String wrongQuantity) {
        this.wrongQuantity = wrongQuantity;
    }
    
    
    
}
