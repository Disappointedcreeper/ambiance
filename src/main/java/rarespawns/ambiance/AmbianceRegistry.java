package rarespawns.ambiance;

import com.mojang.datafixers.util.Pair;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


// shamelessly stealing this from here https://gist.github.com/Raik176/9187a4960869749a6d50456115eab6e1
public class AmbianceRegistry
{
    public static final Item TEST_ITEM = registerItem("test_item", (properties) -> new Item(properties.food(new FoodComponent.Builder().build())));
    public static final Pair<Block,BlockItem> TEST_BLOCK = registerBlockWithItem("test_block", Block::new);
    public static final Pair<Block,BlockItem> MOSSY_OAK_PLANKS = registerBlockWithItem("mossy_oak_planks", Block::new);
    public static final Pair<Block,BlockItem> MOSSY_OAK_STAIRS = registerBlockWithItem("mossy_oak_stairs", Block::new);
    public static final Pair<Block,BlockItem> MOSSY_OAK_SLAB = registerBlockWithItem("mossy_oak_slab", Block::new);
    public static final Pair<Block,BlockItem> MOSSY_OAK_FENCE = registerBlockWithItem("mossy_oak_fence", Block::new);
    public static final BlockFamily MOSSY_OAK = new BlockFamily.Builder(AmbianceRegistry.MOSSY_OAK_PLANKS.getFirst())
        .stairs(AmbianceRegistry.MOSSY_OAK_STAIRS.getFirst())
		.slab(AmbianceRegistry.MOSSY_OAK_SLAB.getFirst())
		.fence(AmbianceRegistry.MOSSY_OAK_FENCE.getFirst())
		.build();
    public static final Pair<Block,BlockItem> MOSSY_OAK_DOOR = registerBlockWithItem("mossy_oak_door", Block::new);
    public static final Pair<Block,BlockItem> MOSSY_OAK_TRAPDOOR = registerBlockWithItem("mossy_oak_trapdoor", Block::new);
    

    public static Item registerItem(String name) {
        return registerItem(name, Item::new);
    }
    public static <T extends Item> T registerItem(String name, ItemFactory<T> factory) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("ambiance",name));
        return net.minecraft.registry.Registry.register(
                Registries.ITEM,
                key,
                factory.create(new Item.Settings().registryKey(key))
        );
    }

    //the functions that register the items and blocks
    //I am assuming that it's just a bunch of different cases (so if you feed in no settings it uses default settings)
    public static Block registerBlock(String name, AbstractBlock.Settings base) {
        return registerBlock(name, Block::new, base);
    }
    public static Block registerBlock(String name) {
        return registerBlock(name, Block::new);
    }
    public static <T extends Block> T registerBlock(String name, BlockFactory<T> factory) {
        return registerBlock(name, factory, AbstractBlock.Settings.create());
    }
    public static <T extends Block> T registerBlock(String name, BlockFactory<T> factory, AbstractBlock.Settings base) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of("ambiance",name));
        return net.minecraft.registry.Registry.register(
                Registries.BLOCK,
                key,
                factory.create(base.registryKey(key))
        );
    }
    public static Pair<Block, BlockItem> registerBlockWithItem(String name) {
        return registerBlockWithItem(name, AbstractBlock.Settings.create());
    }
    public static Pair<Block, BlockItem> registerBlockWithItem(String name, AbstractBlock.Settings base) {
        return registerBlockWithItem(name, Block::new, base);
    }
    public static <T extends Block> Pair<T, BlockItem> registerBlockWithItem(String name, BlockFactory<T> factory) {
        return registerBlockWithItem(name, factory, AbstractBlock.Settings.create());
    }
    public static <T extends Block> Pair<T, BlockItem> registerBlockWithItem(String name, BlockFactory<T> factory, AbstractBlock.Settings base) {
        return registerBlockWithItem(name, factory, base, (settings, block) -> new BlockItem(block,settings));
    }
    public static <T extends Block, U extends BlockItem> Pair<T, U> registerBlockWithItem(String name, BlockFactory<T> blockFactory, BlockItemFactory<T,U> itemFactory) {
        return registerBlockWithItem(name, blockFactory, AbstractBlock.Settings.create(), itemFactory);
    }
    public static <T extends Block, U extends BlockItem> Pair<T, U> registerBlockWithItem(String name, BlockFactory<T> blockFactory, AbstractBlock.Settings base, BlockItemFactory<T,U> itemFactory) {
        T block = registerBlock(name, blockFactory, base);
        return new Pair<>(
                block,
                registerItem(name, (settings) -> itemFactory.create(settings,block))
        );
    }

    @FunctionalInterface
    public interface ItemFactory<T extends Item> {
        T create(Item.Settings settings);
    }

    @FunctionalInterface
    public interface BlockFactory<T extends Block> {
        T create(AbstractBlock.Settings settings);
    }

    @FunctionalInterface
    public interface BlockItemFactory<T extends Block, U extends BlockItem> {
        U create(Item.Settings settings, T block);
    }
    public static void initialize() {
    }
}
