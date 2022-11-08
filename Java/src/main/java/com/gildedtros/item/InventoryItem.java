package com.gildedtros.item;

public interface InventoryItem {
    void updateQuality();

    default void decreaseSellByDayByOne(Item item) {
        item.sellIn -= 1;
    }

    default boolean isSellByDatePassed(Item item) {
        return item.sellIn < 0;
    }
}
