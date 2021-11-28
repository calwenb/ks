package wen.pojo;

import java.util.Date;
import java.util.List;

/**
 * order pojo层
 */
public class Order {
    private int id;
    private String login_name;
    private String linkman;
    private String address;
    private String phonenumber;
    private double amount;
    private Date time;
    private String remark;
    private int status;
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(int id, String login_name, String linkman, String address, String phonenumber, double amount, Date time, String remark, int status) {
        this.id = id;
        this.login_name = login_name;
        this.linkman = linkman;
        this.address = address;
        this.phonenumber = phonenumber;
        this.amount = amount;
        this.time = time;
        this.remark = remark;
        this.status = status;
    }

    public Order(int id, String login_name, String linkman, String address, String phonenumber, double amount, Date time, String remark, int status, List<OrderItem> orderItems) {
        this.id = id;
        this.login_name = login_name;
        this.linkman = linkman;
        this.address = address;
        this.phonenumber = phonenumber;
        this.amount = amount;
        this.time = time;
        this.remark = remark;
        this.status = status;
        this.orderItems = orderItems;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", login_name='" + login_name + '\'' +
                ", linkman='" + linkman + '\'' +
                ", address='" + address + '\'' +
                ", phonenumber=" + phonenumber +
                ", amount='" + amount + '\'' +
                ", time=" + time +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", orderItems=" + orderItems +
                '}';
    }
}
