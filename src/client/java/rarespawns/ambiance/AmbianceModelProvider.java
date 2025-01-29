package rarespawns.ambiance;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;

public class AmbianceModelProvider extends FabricModelProvider {
	
	public AmbianceModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) 
	{
		blockStateModelGenerator.registerSimpleCubeAll(AmbianceRegistry.MOSSY_OAK_PLANKS.getFirst());
		blockStateModelGenerator.registerDoor(AmbianceRegistry.MOSSY_OAK_DOOR.getFirst());
		blockStateModelGenerator.registerOrientableTrapdoor(AmbianceRegistry.MOSSY_OAK_TRAPDOOR.getFirst());
	}


	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
	}

	@Override
	public String getName() {
		return "Ambiance Model Provider";
	}
}