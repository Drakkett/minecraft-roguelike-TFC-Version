package greymerk.roguelike.dungeon.segment.part;

import java.util.Random;

import greymerk.roguelike.dungeon.IDungeonLevel;
import greymerk.roguelike.dungeon.segment.IAlcove;
import greymerk.roguelike.dungeon.segment.alcove.SilverfishNest;
import greymerk.roguelike.theme.ITheme;
import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.IStair;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.WorldEditor;
import greymerk.roguelike.worldgen.blocks.BlockType;

public class SegmentSilverfish extends SegmentBase {
	
	@Override
	protected void genWall(WorldEditor editor, Random rand, IDungeonLevel level, Cardinal dir, ITheme theme, int x, int y, int z) {
		
		MetaBlock air = BlockType.get(BlockType.AIR);
		IStair stair = theme.getSecondaryStair();
		
		Coord cursor = new Coord(x, y, z);
		Coord start;
		Coord end;
		
		Cardinal[] orth = Cardinal.getOrthogonal(dir);
		
		cursor.add(dir, 2);
		start = new Coord(cursor);
		start.add(orth[0], 1);
		end = new Coord(cursor);
		end.add(orth[1], 1);
		end.add(Cardinal.UP, 2);
		editor.fillRectSolid(rand, start, end, air, true, true);
		
		// front wall
		start.add(dir, 1);
		end.add(dir, 1);
		editor.fillRectSolid(rand, start, end, theme.getPrimaryWall(), false, true);

		// stairs
		cursor.add(Cardinal.UP, 2);
		for(Cardinal d : orth){
			Coord c = new Coord(cursor);
			c.add(d, 1);
			stair.setOrientation(Cardinal.reverse(d), true);
			editor.setBlock(rand, c, stair, true, true);
		}
		
		stair = theme.getPrimaryStair();
		
		cursor = new Coord(x, y, z);
		cursor.add(dir, 3);
		stair.setOrientation(Cardinal.reverse(dir), false);
		stair.setBlock(editor, cursor);
		cursor.add(Cardinal.UP);
		air.setBlock(editor, cursor);
		cursor.add(Cardinal.UP);
		stair.setOrientation(Cardinal.reverse(dir), true);
		stair.setBlock(editor, cursor);
		
		IAlcove nest = new SilverfishNest();
		if(nest.isValidLocation(editor, x, y, z, dir)){
			nest.generate(editor, rand, level.getSettings(), x, y, z, dir);
			return;
		}
	}	
}
