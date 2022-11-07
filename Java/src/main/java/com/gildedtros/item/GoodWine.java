package com.gildedtros.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GoodWine implements ItemWithQuality, ItemWithIncreasingQuality {

    private final Item goodWine;

    @Override
    public void updateQuality() {
        increaseQualityFromItemByOne(goodWine);

        if (goodWine.sellIn < 0) {
            increaseQualityFromItemByOne(goodWine);
        }
    }
}
