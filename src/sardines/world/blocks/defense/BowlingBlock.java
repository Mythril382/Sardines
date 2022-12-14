package sardines.world.blocks.defense;

import arc.math.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.world.*;

import static mindustry.Vars.*;

public class BowlingBlock extends Block{
    public BulletType bullet;
    
    public BowlingBlock(String name){
        super(name);
        
        solid = false;
        update = true;
    }
    
    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return mapBowling() ? tile.floor() == Blocks.sandWater : super.canPlaceOn(tile, team, rotation);
    }
    
    public static boolean mapBowling(){
        return state.map.description().contains("WALL-BOWL");
    }
    
    public class BowlingBuild extends Building{
        @Override
        public void updateTile(){
            float rotation = mapBowling() ? 0f : Mathf.random(0f, 360f);
            bullet.create(this, team, x, y, rotation);
            
            tile.setAir();
        }
    }
}
