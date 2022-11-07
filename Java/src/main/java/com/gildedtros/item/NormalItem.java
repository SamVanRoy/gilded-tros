package com.gildedtros.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NormalItem implements ItemWithQuality, ItemWithDegradingQuality {

    private final Item standardItem;

    @Override
    public void updateQuality() {
        degradeQualityFromItemByOne(standardItem);

        if (standardItem.sellIn < 0) {
            degradeQualityFromItemByOne(standardItem);
        }
    }
}
