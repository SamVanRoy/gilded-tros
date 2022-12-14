package com.gildedtros.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LegendaryItem implements InventoryItem {

    private final Item legendaryItem;

    @Override
    public void updateQuality() {
        legendaryItem.quality = 80;
    }
}
