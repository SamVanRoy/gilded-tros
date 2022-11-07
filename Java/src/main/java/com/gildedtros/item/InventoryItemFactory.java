package com.gildedtros.item;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class InventoryItemFactory {

    private static final Map<String, Function<Item, InventoryItem>> INVENTORY_ITEM_MAP = Map.ofEntries(
            new SimpleEntry<>("Backstage passes for Re:Factor", BackstagePass::new),
            new SimpleEntry<>("Backstage passes for HAXX", BackstagePass::new),
            new SimpleEntry<>("Good Wine", GoodWine::new),
            new SimpleEntry<>("B-DAWG Keychain", LegendaryItem::new),
            new SimpleEntry<>("Duplicate Code", SmellyItem::new),
            new SimpleEntry<>("Long Methods", SmellyItem::new),
            new SimpleEntry<>("Ugly Variable Names", SmellyItem::new)
    );

    public static InventoryItem getInventoryItem(Item item) {
        return getItemFromInventoryItemMap(item)
                .orElseGet(() -> new NormalItem(item));
    }

    private static Optional<InventoryItem> getItemFromInventoryItemMap(Item item) {
        return Optional.ofNullable(INVENTORY_ITEM_MAP.get(item.name))
                .map(inventoryItem -> inventoryItem.apply(item));
    }
}
