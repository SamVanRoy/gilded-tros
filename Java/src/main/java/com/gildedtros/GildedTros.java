package com.gildedtros;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class GildedTros {
    Item[] items;

    public void updateInventory() {
        for (Item item : items) {
            if (!item.name.equals("Good Wine") && itemIsNotABackstagePass(item)) {
                if (item.quality > 0) {
                    if (itemIsNotALegendaryItem(item)) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (itemIsABackstagePass(item)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            }

            if (itemIsNotALegendaryItem(item)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Good Wine")) {
                    if (itemIsNotABackstagePass(item)) {
                        if (item.quality > 0) {
                            if (itemIsNotALegendaryItem(item)) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
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
}