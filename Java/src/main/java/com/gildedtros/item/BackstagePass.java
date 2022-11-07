package com.gildedtros.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BackstagePass implements InventoryItem, ItemWithIncreasingQuality {

    private final Item backstagePass;

    @Override
    public void updateQuality() {
        if (isSellByDateUnder(0)) {
            dropQualityToZero();
        } else if (isSellByDateUnder(5)) {
            increaseQualityFromItemBy(backstagePass, 3);
        } else if (isSellByDateUnder(10)) {
            increaseQualityFromItemBy(backstagePass, 2);
        } else {
            increaseQualityFromItemByOne(backstagePass);
        }
    }

    private boolean isSellByDateUnder(int amountOfDaysLeft) {
        return backstagePass.sellIn < amountOfDaysLeft;
    }

    private void dropQualityToZero() {
        backstagePass.quality = 0;
    }
}
