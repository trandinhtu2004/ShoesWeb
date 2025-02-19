/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Order {

    private int oid;
    private double totalprice;
    private String note, address;
    private String date;
    private User user;

    public Order() {
    }

    public Order(int oid, double totalprice, String note, String address, String date, User user) {
        this.oid = oid;
        this.totalprice = totalprice;
        this.note = note;
        this.address = address;
        this.date = date;
        this.user = user;
    }

    public Order(double totalprice, String note, String address, String date, User user) {
        this.totalprice = totalprice;
        this.note = note;
        this.address = address;
        this.date = date;
        this.user = user;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
