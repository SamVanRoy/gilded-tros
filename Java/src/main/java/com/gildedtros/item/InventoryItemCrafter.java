package com.gildedtros.item;

@FunctionalInterface
public interface InventoryItemCrafter {
    InventoryItem craft(Item item);
}
