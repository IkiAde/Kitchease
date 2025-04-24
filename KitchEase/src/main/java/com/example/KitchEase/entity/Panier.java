// models/Panier.java
package com.example.KitchEase.entity;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private List<PanierItem> items = new ArrayList<>();

    public List<PanierItem> getItems() {
        return items;
    }

    public void addItem(PanierItem newItem) {
        for (PanierItem item : items) {
            if (item.getNom().equals(newItem.getNom())) {
                item.setQuantite(item.getQuantite() + newItem.getQuantite());
                return;
            }
        }
        items.add(newItem);
    }

    public double getTotalPanier() {
        return items.stream().mapToDouble(PanierItem::getTotal).sum();
    }

    public void clear() {
        items.clear();
    }
}
