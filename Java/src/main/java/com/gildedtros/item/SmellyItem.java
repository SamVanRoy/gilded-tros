package com.gildedtros.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SmellyItem implements InventoryItem, ItemWithDegradingQuality {

    private final Item smellyItem;

    @Override
    // TODO SAM: think about a way to reuse NormalItem.updateQuality * 2
    public void updateQuality() {
        if (isSellByDatePassed(smellyItem))
            degradeQualityFromItemBy(smellyItem, 4);
        else
            degradeQualityFromItemBy(smellyItem, 2);
    }
}
