package com.gildedtros;

import com.gildedtros.item.Item;
import com.gildedtros.item.ItemWithQuality;
import com.gildedtros.item.ItemWithQualityFactory;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
class GildedTros {
    Item[] items;

    public void updateInventory() {
        for (Item item : items) {
            if (itemIsNotALegendaryItem(item)) {
                item.sellIn -= 1;
            }

            if (itemIsABackstagePass(item) || itemIsAGoodWine(item)) {
                updateQualityFromItem(item);
                return;
            }

            degradeQualityFromItemByOne(item);

            if (item.sellIn < 0) {
                degradeQualityFromItemByOne(item);
            }
        }
    }

    private void updateQualityFromItem(Item item) {
        Optional.ofNullable(ItemWithQualityFactory.getItemWithQuality(item))
                .ifPresent(ItemWithQuality::updateQuality);
    }

    private boolean itemIsABackstagePass(Item item) {
        return item.name.equals("Backstage passes for Re:Factor") || item.name.equals("Backstage passes for HAXX");
    }

    private boolean itemIsNotALegendaryItem(Item item) {
        return !item.name.equals("B-DAWG Keychain");
    }

    private boolean itemIsAGoodWine(Item item) {
        return item.name.equals("Good Wine");
    }

    private void degradeQualityFromItemByOne(Item item) {
        if (item.quality > 0) {
            if (itemIsNotALegendaryItem(item)) {
                item.quality -= 1;
            }
        }
    }
}