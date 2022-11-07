package com.gildedtros.item;

public interface ItemWithQuality {
    void updateQuality();

    default void increaseQualityFromItemByOne(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    default void degradeQualityFromItemByOne(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }
    }

    default void decreaseSellByDayByOne(Item item) {
        item.sellIn -= 1;
    }
}
