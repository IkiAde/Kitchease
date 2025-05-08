package com.example.KitchEase.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Panier {
    private List<PanierItem> items = new ArrayList<>();

    public List<PanierItem> getItems() {
        return new ArrayList<>(items); // Retourne une copie pour éviter les modifications externes
    }

    public void addItem(PanierItem newItem) {
        Optional<PanierItem> existingItem = items.stream()
                .filter(item -> item.getNom().equals(newItem.getNom()))
                .findFirst();

        if (existingItem.isPresent()) {
            PanierItem item = existingItem.get();
            item.setQuantite(item.getQuantite() + newItem.getQuantite());
        } else {
            items.add(newItem);
        }
    }

    public double getTotalPanier() {
        return items.stream().mapToDouble(PanierItem::getTotal).sum();
    }

    public void clear() {
        items.clear();
    }

    public void setItems(List<PanierItem> newItems) {
        this.items.clear();
        this.items.addAll(newItems);
    }
}