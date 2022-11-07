package com.gildedtros.item;

public interface ItemWithDegradingQuality {

    default void degradeQualityFromItemByOne(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }
    }
}
