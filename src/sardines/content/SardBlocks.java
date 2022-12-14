package sardines.content;

import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import sardines.world.blocks.defense.*;

import static mindustry.type.ItemStack.*;

public class SardBlocks{
    public static Block copperWallBowling, titaniumWallBowling;
    
    public static void load(){
        copperWallBowling = new BowlingBlock("copper-wall-bowling"){{
            requirements(Category.turret, with(Items.copper, 6));
            bullet = SardBullets.copperWallBowling;
        }};
        
        titaniumWallBowling = new BowlingBlock("titanium-wall-bowling"){{
            requirements(Category.turret, with(Items.titanium, 6));
            bullet = SardBullets.titaniumWallBowling;
        }};
    }
}
