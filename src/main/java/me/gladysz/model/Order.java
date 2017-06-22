package me.gladysz.model;

import me.gladysz.enums.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "ORDER_HEADER")
public class Order extends AbstractDomainClass {

    @ManyToOne
    private Customer customer;

    @Embedded
    private Address shippingAddress;

    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

    private Date dateShipped;

    public Order() {
        this.orderDetails = new ArrayList<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetails;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetails = orderDetail;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    public void addToOrderDetails(OrderDetail orderDetail) {
        this.orderDetails.add(orderDetail);
    }

    public void removeOrderDetail(OrderDetail orderDetail) {
        this.orderDetails.remove(orderDetail);
    }
}