package com.cjx913.mybatis.pojo;

public class Order {
    private int order_id;
    private double total;
    private String remark;

    private User user;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", total=" + total +
                ", remark='" + remark + '\'' +
                '}';
    }
}
