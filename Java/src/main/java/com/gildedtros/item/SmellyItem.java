package com.gildedtros.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SmellyItem implements InventoryItem, ItemWithDegradingQuality {

    private final Item smellyItem;

    @Override
    // TODO SAM: think about a way to reuse NormalItem.updateQuality * 2
    public void updateQuality() {
        degradeQualityFromItemBy(smellyItem, 2);

        if (smellyItem.sellIn < 0) {
            degradeQualityFromItemBy(smellyItem, 2);
        }
    }
}
