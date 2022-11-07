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

            if (itemIsABackstagePass(item) || itemIsAGoodWine(item) || itemIsALegendaryItem(item) || itemIsAStandardItem(item)) {
                updateQualityFromItem(item);
                return;
            }
        }
    }

    private void updateQualityFromItem(Item item) {
        ItemWithQualityFactory.getItemWithQuality(item).updateQuality();
    }

    private boolean itemIsABackstagePass(Item item) {
        return item.name.equals("Backstage passes for Re:Factor") || item.name.equals("Backstage passes for HAXX");
    }

    private boolean itemIsNotALegendaryItem(Item item) {
        return !item.name.equals("B-DAWG Keychain");
    }

    private boolean itemIsALegendaryItem(Item item) {
        return item.name.equals("B-DAWG Keychain");
    }

    private boolean itemIsAGoodWine(Item item) {
        return item.name.equals("Good Wine");
    }

    private boolean itemIsAStandardItem(Item item) {
        return !itemIsABackstagePass(item) && itemIsNotALegendaryItem(item) && !itemIsAGoodWine(item);
    }
}