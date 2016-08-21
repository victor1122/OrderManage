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
public class tbl_accountError implements Serializable {

    private String dupUsername;
    private String dupEmail;
    private String wrongConfirm;

    public tbl_accountError() {
    }

    /**
     * @return the dupUsername
     */
    public String getDupUsername() {
        return dupUsername;
    }

    /**
     * @param dupUsername the dupUsername to set
     */
    public void setDupUsername(String dupUsername) {
        this.dupUsername = dupUsername;
    }

    /**
     * @return the dupEmail
     */
    public String getDupEmail() {
        return dupEmail;
    }

    /**
     * @param dupEmail the dupEmail to set
     */
    public void setDupEmail(String dupEmail) {
        this.dupEmail = dupEmail;
    }

    /**
     * @return the wrongConfirm
     */
    public String getWrongConfirm() {
        return wrongConfirm;
    }

    /**
     * @param wrongconfirm the wrongConfirm to set
     */
    public void setWrongConfirm(String wrongconfirm) {
        this.wrongConfirm = wrongconfirm;
    }
}
