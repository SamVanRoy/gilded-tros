package com.gildedtros;

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

            updateQualityFromItem(item);

            if (itemIsNotAGoodWine(item) && itemIsNotABackstagePass(item)) {
                degradeQualityFromItemByOne(item);
            } else {
                if (itemIsNotABackstagePass(item)) {
                    increaseQualityFromItemByOne(item);
                }
            }

            if (item.sellIn < 0) {
                if (itemIsNotAGoodWine(item)) {
                    if (itemIsNotABackstagePass(item)) {
                        degradeQualityFromItemByOne(item);
                    } else {
                        item.quality = 0;
                    }
                } else {
                    increaseQualityFromItemByOne(item);
                }
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

    private boolean itemIsNotABackstagePass(Item item) {
        return !itemIsABackstagePass(item);
    }

    private boolean itemIsNotALegendaryItem(Item item) {
        return !item.name.equals("B-DAWG Keychain");
    }

    private boolean itemIsNotAGoodWine(Item item) {
        return !item.name.equals("Good Wine");
    }

    private void increaseQualityFromItemByOne(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    private void degradeQualityFromItemByOne(Item item) {
        if (item.quality > 0) {
            if (itemIsNotALegendaryItem(item)) {
                item.quality -= 1;
            }
        }
    }
}