package com.gildedtros.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LegendaryItem implements ItemWithQuality {

    private final Item legendaryItem;

    @Override
    public void updateQuality() {
        legendaryItem.quality = 80;
    }

    @Override
    public void degradeQualityFromItemByOne(Item item) {
        throw new UnsupportedOperationException("Legendary items do not degrade in Quality");
    }
}
