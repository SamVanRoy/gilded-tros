package com.gildedtros.item;

public interface ItemWithIncreasingQuality {

    default void increaseQualityFromItemBy(Item item, int amountOfQualityToBeIncreased) {
        item.quality += amountOfQualityToBeIncreased;
        if (item.quality > 50) {
            item.quality = 50;
        }
    }

    default void increaseQualityFromItemByOne(Item item) {
        increaseQualityFromItemBy(item, 1);
    }
}
