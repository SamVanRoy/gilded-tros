package com.gildedtros;

import com.gildedtros.item.Item;
import com.gildedtros.item.ItemWithQualityFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class GildedTros {
    Item[] items;

    public void updateInventory() {
        for (Item item : items) {
            decreaseSellByDayFromItem(item);
            updateQualityFromItem(item);
        }
    }

    private void decreaseSellByDayFromItem(Item item) {
        ItemWithQualityFactory.getItemWithQuality(item).decreaseSellByDayByOne(item);
    }

    private void updateQualityFromItem(Item item) {
        ItemWithQualityFactory.getItemWithQuality(item).updateQuality();
    }
}