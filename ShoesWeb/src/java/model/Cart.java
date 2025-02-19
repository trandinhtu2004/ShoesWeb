/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Cart extends Product {

    private int quantity;

    public Cart() {
    }

    public Cart(int quantity) {
        this.quantity = quantity;
    }

    public Cart(int id, String name, String description, String category, double price, int stock, String image, int quantity) {
        super(id, name, description, category, price, stock, image);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
