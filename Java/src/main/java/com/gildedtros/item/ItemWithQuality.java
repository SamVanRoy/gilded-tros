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
            if (itemIsNotALegendaryItem(item)) {
                item.quality -= 1;
            }
        }
    }

    private boolean itemIsNotALegendaryItem(Item item) {
        return !item.name.equals("B-DAWG Keychain");
    }
}
