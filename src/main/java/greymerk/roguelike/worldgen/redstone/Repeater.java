package greymerk.roguelike.worldgen.redstone;

import java.util.Random;

import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.WorldEditor;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class Repeater {
	
	private static final int MAX_DELAY = 3;
	
	public static void generate(WorldEditor editor, Random rand, Cardinal dir, int delay, Coord pos){
		generate(editor, rand, dir, delay, false, pos);
	}
	
	public static void generate(WorldEditor editor, Random rand, Cardinal dir, int delay, boolean powered, Coord pos){
		
		int meta = 0;
		
		switch(dir){
		case NORTH: break;
		case EAST: meta = 1; break;
		case SOUTH: meta = 2; break;
		case WEST: meta = 3; break;
		default:
		}
		
		if(delay > 0){
			meta += 3 + (delay > MAX_DELAY ? MAX_DELAY : delay);
		}
		
		Block b = powered ? Blocks.powered_repeater : Blocks.unpowered_repeater;
		
		MetaBlock repeater = new MetaBlock(b, meta);
				
		repeater.setBlock(editor, pos);
		editor.blockUpdate(pos, b, 1);
	}
	
}
