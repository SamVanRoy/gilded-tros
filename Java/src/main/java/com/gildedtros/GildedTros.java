package com.gildedtros;

import com.gildedtros.item.Item;
import com.gildedtros.item.ItemWithQualityFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class GildedTros {
    Item[] items;

    public void updateInventory() {
        for (Item item : items) {
            if (itemIsNotALegendaryItem(item)) {
                item.sellIn -= 1;
            }

            updateQualityFromItem(item);
        }
    }

    private void updateQualityFromItem(Item item) {
        ItemWithQualityFactory.getItemWithQuality(item).updateQuality();
    }

    private boolean itemIsNotALegendaryItem(Item item) {
        return !item.name.equals("B-DAWG Keychain");
    }
}