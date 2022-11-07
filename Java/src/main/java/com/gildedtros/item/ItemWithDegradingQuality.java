package com.gildedtros.item;

public interface ItemWithDegradingQuality {

    default void degradeQualityFromItemBy(Item item, int amountOfQualityToBeDegraded) {
        item.quality -= amountOfQualityToBeDegraded;
        if (item.quality < 0) {
            item.quality = 0;
        }
    }

    default void degradeQualityFromItemByOne(Item item) {
        degradeQualityFromItemBy(item, 1);
    }
}
