/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anhnh
 */
@Entity
@Table(name = "tbl_account", catalog = "ManageOrder", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAccount.findAll", query = "SELECT t FROM TblAccount t"),
    @NamedQuery(name = "TblAccount.findByAccountID", query = "SELECT t FROM TblAccount t WHERE t.accountID = :accountID"),
    @NamedQuery(name = "TblAccount.findByCustomerName", query = "SELECT t FROM TblAccount t WHERE t.customerName = :customerName"),
    @NamedQuery(name = "TblAccount.findByPassword", query = "SELECT t FROM TblAccount t WHERE t.password = :password"),
    @NamedQuery(name = "TblAccount.findByEmail", query = "SELECT t FROM TblAccount t WHERE t.email = :email")})
public class TblAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "accountID", nullable = false, length = 10)
    private String accountID;
    @Basic(optional = false)
    @Column(name = "customerName", nullable = false, length = 50)
    private String customerName;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    public TblAccount() {
    }

    public TblAccount(String accountID) {
        this.accountID = accountID;
    }

    public TblAccount(String accountID, String customerName, String password, String email) {
        this.accountID = accountID;
        this.customerName = customerName;
        this.password = password;
        this.email = email;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountID != null ? accountID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAccount)) {
            return false;
        }
        TblAccount other = (TblAccount) object;
        if ((this.accountID == null && other.accountID != null) || (this.accountID != null && !this.accountID.equals(other.accountID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.entity.TblAccount[ accountID=" + accountID + " ]";
    }
    
}
