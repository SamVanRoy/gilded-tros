package com.gildedtros;

import com.gildedtros.item.InventoryItemFactory;
import com.gildedtros.item.Item;
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
        InventoryItemFactory.getInventoryItem(item).decreaseSellByDayByOne(item);
    }

    private void updateQualityFromItem(Item item) {
        InventoryItemFactory.getInventoryItem(item).updateQuality();
    }
}