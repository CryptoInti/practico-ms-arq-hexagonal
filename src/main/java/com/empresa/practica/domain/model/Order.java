
package com.empresa.practica.domain.model;

public class Order {
    private Customer customer;
    private double totalAmount;
    private double discountedAmount;

    public Order(Customer customer, double totalAmount) {
        this.customer = customer;
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(double discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    // Método para aplicar descuento
    public void applyDiscount(double discount) {
        this.discountedAmount = this.totalAmount * (1 - (discount / 100));
    }
}
