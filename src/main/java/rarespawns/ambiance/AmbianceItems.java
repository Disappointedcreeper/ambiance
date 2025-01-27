package rarespawns.ambiance;
import java.util.function.Function;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public final class AmbianceItems {
    private AmbianceItems() {
    }
   
    public static final Item CUSTOM_ITEM = register("test_item", Item::new, new Item.Settings());
   
    public static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
      final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("ambiance", path));
      return Items.register(registryKey, factory, settings);
    }
   
    public static void initialize() {
    }
  }
