package com.gildedtros.item;

public class InventoryItemFactory {

    public static InventoryItem getInventoryItem(Item item) {
        if (isABackstagePass(item.name))
            return new BackstagePass(item);

        if (isAGoodWine(item.name))
            return new GoodWine(item);

        if (isALegendaryItem(item.name))
            return new LegendaryItem(item);

        if (isASmellyItem(item.name))
            return new SmellyItem(item);

        return new NormalItem(item);
    }

    private static boolean isABackstagePass(String name) {
        return name.equals("Backstage passes for Re:Factor") || name.equals("Backstage passes for HAXX");
    }

    private static boolean isAGoodWine(String name) {
        return name.equals("Good Wine");
    }

    private static boolean isALegendaryItem(String name) {
        return name.equals("B-DAWG Keychain");
    }

    private static boolean isASmellyItem(String name) {
        return name.equals("Duplicate Code") || name.equals("Long Methods") || name.equals("Ugly Variable Names");
    }
}
