package com.gildedtros;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

        @Test
        void givenALegendaryItem_whenUpdateQuality_thenQualityFromItemNotDegraded() {
            GildedTros app = newGildedTros("B-DAWG Keychain", 10, 80);
            Item[] expectedItems = createItems("B-DAWG Keychain", 10, 80);

            app.updateQuality();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }

        @Test
        void givenALegendaryItemWhereTheSellByDateHasPassed_whenUpdateQuality_thenQualityFromItemNotDegraded() {
            GildedTros app = newGildedTros("B-DAWG Keychain", -1, 80);
            Item[] expectedItems = createItems("B-DAWG Keychain", -1, 80);

            app.updateQuality();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }
    }

    @Nested
    @DisplayName("Item quality increases correctly")
    class ItemQualityIncreasesCorrectly {

        @ParameterizedTest
        @MethodSource("provideSellInWithAddedQualityForGoodWine")
        void givenTheItemGoodWineWithGivenSellIn_whenUpdateQuality_thenQualityFromItemIncreasedWithGivenValue(int sellIn, int addedQuality) {
            int initialQuality = 40;
            GildedTros app = newGildedTros("Good Wine", sellIn, initialQuality);
            Item[] expectedItems = createItems("Good Wine", sellIn, initialQuality + addedQuality);

            app.updateQuality();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }

        private static Stream<Arguments> provideSellInWithAddedQualityForGoodWine() {
            return Stream.of(
                    // Arguments.of(sellIn, addedQuality)
                    Arguments.of(10, 1),
                    Arguments.of(1, 1),
                    Arguments.of(0, 2),
                    Arguments.of(-1, 2),
                    Arguments.of(-10, 2)
            );
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
