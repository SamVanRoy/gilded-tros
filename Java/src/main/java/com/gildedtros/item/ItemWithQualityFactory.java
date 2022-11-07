package com.gildedtros.item;

public class ItemWithQualityFactory {

    public static ItemWithQuality getItemWithQuality(Item item) {
        if (isABackstagePass(item.name))
            return new BackstagePass(item);

        if (isAGoodWine(item.name))
            return new GoodWine(item);

        return null;
    }

    private static boolean isABackstagePass(String name) {
        return name.equals("Backstage passes for Re:Factor") || name.equals("Backstage passes for HAXX");
    }

    private static boolean isAGoodWine(String name) {
        return name.equals("Good Wine");
    }
}
