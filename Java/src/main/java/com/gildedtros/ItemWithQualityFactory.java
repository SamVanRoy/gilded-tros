package com.gildedtros;

public class ItemWithQualityFactory {

    public static ItemWithQuality getItemWithQuality(Item item) {
        if (isABackstagePass(item.name))
            return new BackstagePass(item);

        return null;
    }

    private static boolean isABackstagePass(String name) {
        return name.equals("Backstage passes for Re:Factor") || name.equals("Backstage passes for HAXX");
    }
}
