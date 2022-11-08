package com.gildedtros.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GoodWine implements InventoryItem, ItemWithIncreasingQuality {

    private final Item goodWine;

    @Override
    public void updateQuality() {
        if (goodWine.sellIn < 0)
            increaseQualityFromItemBy(goodWine, 2);
        else
            increaseQualityFromItemByOne(goodWine);
    }
}
