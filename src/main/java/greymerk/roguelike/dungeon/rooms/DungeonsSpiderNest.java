package greymerk.roguelike.dungeon.rooms;

import java.util.Random;

import greymerk.roguelike.dungeon.Dungeon;
import greymerk.roguelike.dungeon.base.DungeonBase;
import greymerk.roguelike.dungeon.settings.LevelSettings;
import greymerk.roguelike.treasure.Treasure;
import greymerk.roguelike.worldgen.BlockWeightedRandom;
import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.Spawner;
import greymerk.roguelike.worldgen.WorldEditor;
import greymerk.roguelike.worldgen.blocks.BlockType;

public class DungeonsSpiderNest extends DungeonBase {
	WorldEditor editor;
	Random rand;
	int originX;
	int originY;
	int originZ;
	byte dungeonHeight;
	int dungeonLength;
	int dungeonWidth;
	
	public DungeonsSpiderNest() {
		super();
		dungeonHeight = 2;
		dungeonLength = 3;
		dungeonWidth = 3;
		
	}

	public boolean generate(WorldEditor editor, Random inRandom, LevelSettings settings, Cardinal[] entrances, Coord origin) {

		this.editor = editor;
		rand = inRandom;
		originX = origin.getX();
		originY = origin.getY();
		originZ = origin.getZ();

		BlockWeightedRandom webs = new BlockWeightedRandom();
		webs.addBlock(BlockType.get(BlockType.WEB), 3);
		webs.addBlock(BlockType.get(BlockType.AIR), 1);
		
		for (int blockX = originX - dungeonLength - 1; blockX <= originX + dungeonLength + 1; blockX++) {
			for (int blockZ = originZ - dungeonWidth - 1; blockZ <= originZ + dungeonWidth + 1; blockZ++) {
				for (int blockY = originY + dungeonHeight; blockY >= originY - dungeonHeight; blockY--) {
					
					int x = Math.abs(blockX - originX);
					int z = Math.abs(blockZ - originZ);
					
					int clearHeight = x > z ? x : z;
					
					if(blockY == originY) webs.setBlock(editor, inRandom, new Coord(blockX, blockY, blockZ));
					if(clearHeight < 1) clearHeight = 1;
					if(Math.abs(blockY - originY) > clearHeight) continue;
						
					if(rand.nextInt(clearHeight)  == 0){
						webs.setBlock(editor, inRandom, new Coord(blockX, blockY, blockZ));
					} else if(rand.nextInt(5) == 0){
						editor.setBlock(blockX, blockY, blockZ, BlockType.get(BlockType.GRAVEL));
					}
					
				}
			}
		}
		
		Spawner.generate(editor, rand, settings, new Coord(originX, originY, originZ), Spawner.CAVESPIDER);
		
		Treasure.createChests(editor, rand, 1 + rand.nextInt(3), WorldEditor.getRectSolid(
				originX - dungeonLength, originY - 1, originZ - dungeonWidth,
				originX + dungeonLength, originY + 1, originZ + dungeonWidth), Dungeon.getLevel(originY));

		return true;
	}
	
	public int getSize(){
		return 4;
	}
}
