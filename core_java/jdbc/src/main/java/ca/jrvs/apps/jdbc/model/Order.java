package ca.jrvs.apps.jdbc.model;

import ca.jrvs.apps.jdbc.utils.DataTransferObject;

import java.sql.Timestamp;
import java.util.List;

public class Order implements DataTransferObject {
    private long id;
    private Timestamp creationDate;
    private Float totalDue;
    private String status;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private long salePersonId;
    private String salePersonFirstName;
    private List<OrderedItem> orderedItems;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Float getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(Float totalDue) {
        this.totalDue = totalDue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public long getSalePersonId() {
        return salePersonId;
    }

    public void setSalePersonId(long salePersonId) {
        this.salePersonId = salePersonId;
    }

    public String getSalePersonFirstName() {
        return salePersonFirstName;
    }

    public void setSalePersonFirstName(String salePersonFirstName) {
        this.salePersonFirstName = salePersonFirstName;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", totalDue=" + totalDue +
                ", status='" + status + '\'' +
                ", customerFirstName='" + customerFirstName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", salePersonId=" + salePersonId +
                ", salePersonFirstName='" + salePersonFirstName + '\'' +
                ", orderedItems=" + orderedItems +
                '}';
    }
}
