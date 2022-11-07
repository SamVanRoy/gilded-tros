package com.gildedtros.item;

public interface InventoryItem {
    void updateQuality();

    default void decreaseSellByDayByOne(Item item) {
        item.sellIn -= 1;
    }
}
