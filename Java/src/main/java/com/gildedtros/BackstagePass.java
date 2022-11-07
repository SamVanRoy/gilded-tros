package com.gildedtros;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BackstagePass implements ItemWithQuality {

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
    }
}
