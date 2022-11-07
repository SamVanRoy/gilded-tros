package com.gildedtros.item;

public interface ItemWithQuality {
    void updateQuality();

    default void decreaseSellByDayByOne(Item item) {
        item.sellIn -= 1;
    }
}
