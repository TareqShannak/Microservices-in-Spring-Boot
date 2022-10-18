package com.example.apis.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    private long id;

    private String name;

    private String owner;

    private int capacity;

    private double amount;

    private double total;

    private double profit;

    private String reference;

    private String exportTo;

    private double percentage;

    public Item() {
    }

    public Item(long id, String name, String owner, int capacity, double amount, double total, double profit, String reference, String exportTo, double percentage) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.capacity = capacity;
        this.amount = amount;
        this.total = total;
        this.profit = profit;
        this.reference = reference;
        this.exportTo = exportTo;
        this.percentage = percentage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getExportTo() {
        return exportTo;
    }

    public void setExportTo(String exportTo) {
        this.exportTo = exportTo;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", capacity=" + capacity +
                ", amount=" + amount +
                ", total=" + total +
                ", profit=" + profit +
                ", reference='" + reference + '\'' +
                ", exportTo='" + exportTo + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
