package com.gildedtros.item;

public interface ItemWithQuality {
    void updateQuality();

    default void increaseQualityFromItemByOne(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }
}
