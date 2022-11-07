package com.gildedtros.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BackstagePass implements ItemWithQuality, ItemWithIncreasingQuality {

    private final Item backstagePass;

    @Override
    public void updateQuality() {
        increaseQualityFromItemByOne(backstagePass);

        if (backstagePass.sellIn < 10) {
            increaseQualityFromItemByOne(backstagePass);
        }

        if (backstagePass.sellIn < 5) {
            increaseQualityFromItemByOne(backstagePass);
        }

        if (backstagePass.sellIn < 0) {
            dropQualityToZero();
        }
    }

    private void dropQualityToZero() {
        backstagePass.quality = 0;
    }
}
