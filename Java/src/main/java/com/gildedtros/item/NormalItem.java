package com.gildedtros.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NormalItem implements InventoryItem, ItemWithDegradingQuality {

    private final Item standardItem;

    @Override
    public void updateQuality() {
        if (standardItem.sellIn < 0)
            degradeQualityFromItemBy(standardItem, 2);
        else
            degradeQualityFromItemByOne(standardItem);
    }
}
