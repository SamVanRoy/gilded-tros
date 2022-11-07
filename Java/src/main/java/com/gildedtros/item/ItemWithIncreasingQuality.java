package com.gildedtros.item;

public interface ItemWithIncreasingQuality {

    default void increaseQualityFromItemByOne(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }
}
