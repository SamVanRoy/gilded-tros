package com.gildedtros.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GoodWine implements InventoryItem, ItemWithIncreasingQuality {

    private final Item goodWine;

    @Override
    public void updateQuality() {
        if (isSellByDatePassed(goodWine))
            increaseQualityFromItemBy(goodWine, 2);
        else
            increaseQualityFromItemByOne(goodWine);
    }
}
