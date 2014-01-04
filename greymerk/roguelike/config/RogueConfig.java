package greymerk.roguelike.config;

import java.io.File;
import java.io.IOException;

import net.minecraft.src.Tuple;

public enum RogueConfig {

	DONATURALSPAWN, LEVELRANGE, LEVELMAXROOMS, LEVELSCATTER, SPAWNFREQUENCY;
	
	public static final String configDirName = "config";
	public static final String configFileName = "roguelike.cfg";
	
	private static ConfigFile instance = null;
	
	public static String getName(RogueConfig option){
		switch(option){
		case DONATURALSPAWN: return "doNaturalSpawn";
		case LEVELRANGE: return "levelRange";
		case LEVELMAXROOMS: return "levelMaxRooms";
		case LEVELSCATTER: return "levelScatter";
		case SPAWNFREQUENCY: return "spawnFrequency";
		default: return null;
		}
	}
	
	public static Tuple getDefault(RogueConfig option){
		switch(option){
		case DONATURALSPAWN: return new Tuple(getName(option), true);
		case LEVELRANGE: return new Tuple(getName(option), 80);
		case LEVELMAXROOMS: return new Tuple(getName(option), 40);
		case LEVELSCATTER: return new Tuple(getName(option), 12);
		case SPAWNFREQUENCY: return new Tuple(getName(option), 10);
		default: return null;
		}
	}
	
	private static void setDefaults(){
		if(!instance.ContainsKey(getName(DONATURALSPAWN))) setBoolean(DONATURALSPAWN, (Boolean)getDefault(DONATURALSPAWN).getSecond());
		if(!instance.ContainsKey(getName(LEVELRANGE)))setInt(LEVELRANGE, (Integer)getDefault(LEVELRANGE).getSecond());
		if(!instance.ContainsKey(getName(LEVELMAXROOMS)))setInt(LEVELMAXROOMS, (Integer)getDefault(LEVELMAXROOMS).getSecond());
		if(!instance.ContainsKey(getName(LEVELSCATTER)))setInt(LEVELSCATTER, (Integer)getDefault(LEVELSCATTER).getSecond());
		if(!instance.ContainsKey(getName(SPAWNFREQUENCY)))setInt(SPAWNFREQUENCY, (Integer)getDefault(SPAWNFREQUENCY).getSecond());
	}
	
	public static boolean getBoolean(RogueConfig option){
		reload(false);
		Tuple def = getDefault(option);
		return instance.GetBoolean(getName(option), (Boolean)def.getSecond());
	}
	
	public static void setBoolean(RogueConfig option, Boolean value){
		reload(false);
		Tuple def = getDefault(option);
		instance.Set(getName(option), value);
	}
	
	public static int getInt(RogueConfig option){
		reload(false);
		Tuple def = getDefault(option);
		return instance.GetInteger((String)def.getFirst(), (Integer)def.getSecond());
	}
	
	public static void setInt(RogueConfig option, int value){
		reload(false);
		Tuple def = getDefault(option);
		instance.Set((String)def.getFirst(), value);
	}
	
	private static void init(){
		
		// make sure file exists
		File configDir = new File(configDirName);
		if(!configDir.exists()){
			configDir.mkdir();
		}
		
		File cfile = new File(configDirName + "/" + configFileName);
		
		if(!cfile.exists()){
			try {
				cfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// read in configs
		try {
			instance = new ConfigFile(configDirName + "/" + configFileName, new INIParser());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaults();
		
		try {
			instance.Write();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void reload(boolean force){
		if(instance == null || force){
			init();
		}
	}

	
}