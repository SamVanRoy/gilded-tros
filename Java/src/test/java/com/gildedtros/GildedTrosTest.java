package com.gildedtros;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedTrosTest {

    @Nested
    @DisplayName("Item sellIn decrements correctly")
    class ItemSellInDecrementsCorrectly {

        @Test
        void givenAnItemWithASellIn_whenUpdateQuality_thenSellInFromItemDecrementedWith1() {
            GildedTros app = newGildedTros("Jupke", 10, 40);
            Item[] expectedItems = createItems("Jupke", 9, 40);

            app.updateQuality();

            assertThat(app.items[0].sellIn).isEqualTo(expectedItems[0].sellIn);
        }
    }

    @Nested
    @DisplayName("Item quality degrades correctly")
    class ItemQualityDegradesCorrectly {

        @Test
        void givenAnItemWithAQuality_whenUpdateQuality_thenQualityFromItemDegradedWith1() {
            GildedTros app = newGildedTros("Jupke", 10, 40);
            Item[] expectedItems = createItems("Jupke", 10, 39);

            app.updateQuality();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }

        @Test
        void givenTheSellByDateHasPassed_whenUpdateQuality_thenQualityDegradesTwiceAsFast() {
            GildedTros app = newGildedTros("Jupke", -1, 40);
            Item[] expectedItems = createItems("Jupke", -1, 38);

            app.updateQuality();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }
    }

    private GildedTros newGildedTros(String itemName, int itemSellIn, int itemQuality) {
        Item[] item = createItems(itemName, itemSellIn, itemQuality);
        return new GildedTros(item);
    }

    private Item[] createItems(String name, int sellIn, int quality) {
        return new Item[]{new Item(name, sellIn, quality)};
    }
}
