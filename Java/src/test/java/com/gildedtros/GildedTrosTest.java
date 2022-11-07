package com.gildedtros;

import com.gildedtros.item.Item;
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
        void givenAnItemWithASellIn_whenUpdateInventory_thenSellInFromItemDecrementedWith1() {
            GildedTros app = newGildedTros("Jupke", 10, 40);
            Item[] expectedItems = createItems("Jupke", 9, 40);

            app.updateInventory();

            assertThat(app.items[0].sellIn).isEqualTo(expectedItems[0].sellIn);
        }
    }

    @Nested
    @DisplayName("Item quality degrades correctly")
    class ItemQualityDegradesCorrectly {

        @Test
        void givenAnItemWithAQuality_whenUpdateInventory_thenQualityFromItemDegradedWith1() {
            GildedTros app = newGildedTros("Jupke", 10, 40);
            Item[] expectedItems = createItems("Jupke", 10, 39);

            app.updateInventory();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }

        @Test
        void givenTheSellByDateHasPassed_whenUpdateInventory_thenQualityDegradesTwiceAsFast() {
            GildedTros app = newGildedTros("Jupke", -1, 40);
            Item[] expectedItems = createItems("Jupke", -1, 38);

            app.updateInventory();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }

        @Test
        void givenAnItemWithAlmostLowestQuality_whenUpdateInventoryDegradingQualityWithMoreThan1_thenQualityFromItemIsNeverUnderLowestQuality() {
            GildedTros app = newGildedTros("Duplicate Code", -1, 1);
            Item[] expectedItems = createItems("Duplicate Code", -1, 0);

            app.updateInventory();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }

        @Test
        void givenAnItemWithQualityZero_whenUpdateInventory_thenQualityFromItemStaysZero() {
            GildedTros app = newGildedTros("Jupke", 10, 0);
            Item[] expectedItems = createItems("Jupke", 10, 0);

            app.updateInventory();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }

        @Test
        void givenALegendaryItem_whenUpdateInventory_thenQualityFromItemNotDegraded() {
            GildedTros app = newGildedTros("B-DAWG Keychain", 10, 80);
            Item[] expectedItems = createItems("B-DAWG Keychain", 10, 80);

            app.updateInventory();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }

        @Test
        void givenALegendaryItemWhereTheSellByDateHasPassed_whenUpdateInventory_thenQualityFromItemNotDegraded() {
            GildedTros app = newGildedTros("B-DAWG Keychain", -1, 80);
            Item[] expectedItems = createItems("B-DAWG Keychain", -1, 80);

            app.updateInventory();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }
    }

    @Nested
    @DisplayName("Item quality increases correctly")
    class ItemQualityIncreasesCorrectly {

        @ParameterizedTest
        @MethodSource("provideItemNamesFromIncreasingQualityItems")
        void givenAnIncreasingQualityItemWithMaxQuality_whenUpdateInventory_thenQualityFromItemStaysMaxQuality(String itemName) {
            GildedTros app = newGildedTros(itemName, 10, 50);
            Item[] expectedItems = createItems(itemName, 10, 50);

            app.updateInventory();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }

        private static Stream<Arguments> provideItemNamesFromIncreasingQualityItems() {
            return Stream.of(
                    // Arguments.of(itemName)
                    Arguments.of("Good Wine"),
                    Arguments.of("Backstage passes for Re:Factor"),
                    Arguments.of("Backstage passes for HAXX")
            );
        }

        @ParameterizedTest
        @MethodSource("provideSellInWithAddedQualityForGoodWine")
        void givenTheItemGoodWineWithGivenSellIn_whenUpdateInventory_thenQualityFromItemIncreasedWithGivenValue(int sellIn, int addedQuality) {
            int initialQuality = 40;
            GildedTros app = newGildedTros("Good Wine", sellIn, initialQuality);
            Item[] expectedItems = createItems("Good Wine", sellIn, initialQuality + addedQuality);

            app.updateInventory();

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

        @ParameterizedTest
        @MethodSource("provideSellInWithAddedQualityForBackstagePassItems")
        void givenABackstagePassItemWithGivenSellIn_whenUpdateInventory_thenQualityFromItemIncreasedWithGivenValue(int sellIn, int addedQuality) {
            int initialQuality = 40;
            GildedTros app = newGildedTros("Backstage passes for Re:Factor", sellIn, initialQuality);
            Item[] expectedItems = createItems("Backstage passes for Re:Factor", sellIn, initialQuality + addedQuality);

            app.updateInventory();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }

        private static Stream<Arguments> provideSellInWithAddedQualityForBackstagePassItems() {
            return Stream.of(
                    // Arguments.of(sellIn, addedQuality)
                    Arguments.of(11, 1),
                    Arguments.of(10, 2),
                    Arguments.of(9, 2),
                    Arguments.of(6, 2),
                    Arguments.of(5, 3),
                    Arguments.of(4, 3),
                    Arguments.of(1, 3)
            );
        }

        @ParameterizedTest
        @MethodSource("provideSellInForBackstagePassItems")
        void givenABackstagePassItemWithGivenSellIn_whenUpdateInventory_thenQualityFromItemDroppedTo0(int sellIn) {
            GildedTros app = newGildedTros("Backstage passes for Re:Factor", sellIn, 40);
            Item[] expectedItems = createItems("Backstage passes for Re:Factor", sellIn, 0);

            app.updateInventory();

            assertThat(app.items[0].quality).isEqualTo(expectedItems[0].quality);
        }

        private static Stream<Arguments> provideSellInForBackstagePassItems() {
            return Stream.of(
                    // Arguments.of(sellIn)
                    Arguments.of(0),
                    Arguments.of(-1),
                    Arguments.of(-10)
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
