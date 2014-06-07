package greymerk.roguelike.catacomb.dungeon.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import greymerk.roguelike.catacomb.dungeon.IDungeon;
import greymerk.roguelike.catacomb.theme.ITheme;
import greymerk.roguelike.treasure.ITreasureChest;
import greymerk.roguelike.treasure.TreasureChestEmpty;
import greymerk.roguelike.treasure.loot.provider.ItemNovelty;
import greymerk.roguelike.worldgen.BlockWeightedRandom;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.WorldGenPrimitive;

public class DungeonBaj implements IDungeon {

	@Override
	public boolean generate(World world, Random rand, ITheme theme, int x, int y, int z) {

		WorldGenPrimitive.fillRectHollow(world, rand, x - 5, y - 2, z - 5, x + 5, y + 4, z + 5, new MetaBlock(Block.stone.blockID), true, true);
		WorldGenPrimitive.setBlock(world, x - 5, y + 1, z, 0);
		WorldGenPrimitive.setBlock(world, x - 5, y, z, Block.dirt.blockID);
		WorldGenPrimitive.setBlock(world, x + 5, y + 1, z, 0);
		WorldGenPrimitive.setBlock(world, x + 5, y, z, Block.dirt.blockID);
		WorldGenPrimitive.setBlock(world, x, y + 1, z - 5, 0);
		WorldGenPrimitive.setBlock(world, x, y, z - 5, Block.dirt.blockID);
		WorldGenPrimitive.setBlock(world, x, y + 1, z + 5, 0);
		WorldGenPrimitive.setBlock(world, x, y, z + 5, Block.dirt.blockID);
		
		WorldGenPrimitive.fillRectSolid(world, rand, x - 3, y - 1, z - 3, x + 3, y - 1, z + 3, Block.waterMoving.blockID);
		
		BlockWeightedRandom walls = new BlockWeightedRandom();
		walls.addBlock(new MetaBlock(Block.stone.blockID), 100);
		walls.addBlock(new MetaBlock(Block.dirt.blockID), 20);
		walls.addBlock(new MetaBlock(Block.gravel.blockID), 40);
		
		WorldGenPrimitive.fillRectSolid(world, rand, x - 4, y - 1, z - 4, x + 4, y - 1, z - 4, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x - 4, y - 1, z - 4, x - 4, y - 1, z + 4, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x - 4, y - 1, z + 4, x + 4, y - 1, z + 4, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x + 4, y - 1, z - 4, x + 4, y - 1, z + 4, walls);
		
		WorldGenPrimitive.fillRectSolid(world, rand, x - 4, y, z - 4, x - 4, y + 3, z - 4, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x - 3, y, z - 4, x - 3, y + 3, z - 4, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x - 4, y, z - 3, x - 4, y + 3, z - 3, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x - 3, y - 1, z - 3, x - 3, y, z - 3, walls);
		WorldGenPrimitive.setBlock(world, x - 3, y + 1, z - 3, Block.torchWood.blockID);
		walls.setBlock(world, rand, x - 3, y - 1, z - 2);
		walls.setBlock(world, rand, x - 2, y - 1, z - 3);

		WorldGenPrimitive.fillRectSolid(world, rand, x - 4, y, z + 4, x - 4, y + 3, z + 4, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x - 3, y, z + 4, x - 3, y + 3, z + 4, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x - 4, y, z + 3, x - 4, y + 3, z + 3, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x - 3, y - 1, z + 3, x - 3, y, z + 3, walls);
		WorldGenPrimitive.setBlock(world, x - 3, y + 1, z + 3, Block.torchWood.blockID);
		walls.setBlock(world, rand, x - 3, y - 1, z + 2);
		walls.setBlock(world, rand, x - 2, y - 1, z + 3);
		
		WorldGenPrimitive.fillRectSolid(world, rand, x + 4, y, z - 4, x + 4, y + 3, z - 4, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x + 3, y, z - 4, x + 3, y + 3, z - 4, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x + 4, y, z - 3, x + 4, y + 3, z - 3, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x + 3, y - 1, z - 3, x + 3, y, z - 3, walls);
		WorldGenPrimitive.setBlock(world, x + 3, y + 1, z - 3, Block.torchWood.blockID);
		walls.setBlock(world, rand, x + 3, y - 1, z - 2);
		walls.setBlock(world, rand, x + 2, y - 1, z - 3);
		
		WorldGenPrimitive.fillRectSolid(world, rand, x + 4, y, z + 4, x + 4, y + 3, z + 4, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x + 3, y, z + 4, x + 3, y + 3, z + 4, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x + 4, y, z + 3, x + 4, y + 3, z + 3, walls);
		WorldGenPrimitive.fillRectSolid(world, rand, x + 3, y - 1, z + 3, x + 3, y, z + 3, walls);
		WorldGenPrimitive.setBlock(world, x + 3, y + 1, z + 3, Block.torchWood.blockID);
		walls.setBlock(world, rand, x + 3, y - 1, z + 2);
		walls.setBlock(world, rand, x + 2, y - 1, z + 3);
		
		BlockWeightedRandom roof = new BlockWeightedRandom();
		roof.addBlock(new MetaBlock(Block.stone.blockID), 100);
		roof.addBlock(new MetaBlock(0), 50);
		roof.addBlock(new MetaBlock(Block.dirt.blockID), 20);
		
		WorldGenPrimitive.fillRectSolid(world, rand, x - 4, y + 3, z - 4, x + 4, y + 3, z + 4, roof, true, false);
		
		crops(world, rand, x - 1, y - 1, z - 3);
		crops(world, rand, x - 2, y - 1, z - 2);
		crops(world, rand, x - 1, y - 1, z - 2);
		crops(world, rand, x + 1, y - 1, z - 2);
		crops(world, rand, x + 2, y - 1, z - 2);
		crops(world, rand, x + 3, y - 1, z - 1);
		crops(world, rand, x - 3, y - 1, z);
		crops(world, rand, x - 2, y - 1, z);
		crops(world, rand, x + 1, y - 1, z);
		crops(world, rand, x - 2, y - 1, z + 1);
		crops(world, rand, x, y - 1, z + 1);
		crops(world, rand, x + 2, y - 1, z + 2);
		crops(world, rand, x - 1, y - 1, z + 3);
		crops(world, rand, x, y - 1, z + 3);
		crops(world, rand, x + 1, y - 1, z + 3);
		
		WorldGenPrimitive.setBlock(world, x, y - 1, z, Block.dirt.blockID);
		ITreasureChest chest = new TreasureChestEmpty().generate(world, rand, x, y, z);
		chest.setInventorySlot(ItemNovelty.getItem(ItemNovelty.BAJ), chest.getInventorySize() / 2);
		
		
		return false;
	}

	@Override
	public int getSize() {
		return 8;
	}

	private void crops(World world, Random rand, int x, int y, int z){
		
		if(rand.nextInt(10) == 0){
			WorldGenPrimitive.setBlock(world, x, y, z, Block.gravel.blockID);
			return;
		}
		
		if(rand.nextInt(5) == 0){
			WorldGenPrimitive.setBlock(world, x, y, z, Block.dirt.blockID);
			WorldGenPrimitive.setBlock(world, x, y + 1, z, Block.reed.blockID);
			return;
		}
		
		WorldGenPrimitive.setBlock(world, x, y, z, Block.tilledField.blockID);
		WorldGenPrimitive.setBlock(world, x, y + 1, z, Block.crops.blockID);
		
	}
	
}