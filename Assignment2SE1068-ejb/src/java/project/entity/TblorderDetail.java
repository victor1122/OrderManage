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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anhnh
 */
@Entity
@Table(name = "tbl_orderDetail", catalog = "ManageOrder", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblorderDetail.findAll", query = "SELECT t FROM TblorderDetail t"),
    @NamedQuery(name = "TblorderDetail.findById", query = "SELECT t FROM TblorderDetail t WHERE t.id = :id"),
    @NamedQuery(name = "TblorderDetail.findByProductID", query = "SELECT t FROM TblorderDetail t WHERE t.productID = :productID"),
    @NamedQuery(name = "TblorderDetail.findByQuantity", query = "SELECT t FROM TblorderDetail t WHERE t.quantity = :quantity"),
    @NamedQuery(name = "TblorderDetail.findByUnitPrice", query = "SELECT t FROM TblorderDetail t WHERE t.unitPrice = :unitPrice"),
    @NamedQuery(name = "TblorderDetail.findByUnitItem", query = "SELECT t FROM TblorderDetail t WHERE t.unitItem = :unitItem"),
    @NamedQuery(name = "TblorderDetail.findByTotal", query = "SELECT t FROM TblorderDetail t WHERE t.total = :total"),
    @NamedQuery(name = "TblorderDetail.findByOrderID", query = "SELECT t FROM TblorderDetail t WHERE t.orderID = :orderID")})
public class TblorderDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "productID", nullable = false, length = 10)
    private String productID;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "unitPrice", nullable = false)
    private double unitPrice;
    @Basic(optional = false)
    @Column(name = "unitItem", nullable = false, length = 10)
    private String unitItem;
    @Basic(optional = false)
    @Column(name = "total", nullable = false)
    private double total;
    @Basic(optional = false)
    @Column(name = "orderID", nullable = false, length = 10)
    private String orderID;
    
    public TblorderDetail() {
    }

    public TblorderDetail(Integer id) {
        this.id = id;
    }

    public TblorderDetail(Integer id, String productID, int quantity, double unitPrice, String unitItem, double total, String orderID) {
        this.id = id;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.unitItem = unitItem;
        this.total = total;
        this.orderID = orderID;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitItem() {
        return unitItem;
    }

    public void setUnitItem(String unitItem) {
        this.unitItem = unitItem;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblorderDetail)) {
            return false;
        }
        TblorderDetail other = (TblorderDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.entity.TblorderDetail[ id=" + id + " ]";
    }
    
}
