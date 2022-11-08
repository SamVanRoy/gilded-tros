package com.gildedtros.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NormalItem implements InventoryItem, ItemWithDegradingQuality {

    private final Item standardItem;

    @Override
    public void updateQuality() {
        if (isSellByDatePassed(standardItem))
            degradeQualityFromItemBy(standardItem, 2);
        else
            degradeQualityFromItemByOne(standardItem);
    }
}
