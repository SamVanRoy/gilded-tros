package com.gildedtros;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class GildedTros {
    Item[] items;

    public void updateInventory() {
        for (Item item : items) {
            if (itemIsNotAGoodWine(item) && itemIsNotABackstagePass(item)) {
                if (item.quality > 0) {
                    if (itemIsNotALegendaryItem(item)) {
                        item.quality -= 1;
                    }
                }
            } else {
                increaseQualityFromItemByOne(item);

                if (itemIsABackstagePass(item)) {
                    if (item.sellIn < 11) {
                        increaseQualityFromItemByOne(item);
                    }

                    if (item.sellIn < 6) {
                        increaseQualityFromItemByOne(item);
                    }
                }
            }

            if (itemIsNotALegendaryItem(item)) {
                item.sellIn -= 1;
            }

            if (item.sellIn < 0) {
                if (itemIsNotAGoodWine(item)) {
                    if (itemIsNotABackstagePass(item)) {
                        if (item.quality > 0) {
                            if (itemIsNotALegendaryItem(item)) {
                                item.quality -= 1;
                            }
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    increaseQualityFromItemByOne(item);
                }
            }
        }
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
}