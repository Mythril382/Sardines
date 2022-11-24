package sardines.content;

import arc.graphics.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import sardines.entities.bullet.*;

public class SardBullets{
    public static BulletType
    
    // wall bowling
    copperWallBowling,
    titaniumWallBowling;
    
    public static void load(){
        copperWallBowling = new BowlingBulletType(3f, 200f){{
            width = height = 6f;
            // pierceCap = 4;
            shrinkX = shrinkY = 0f;
            lifetime = 60f * 3f;
            spin = 3f;
            frontColor = Color.valueOf("ffffff");
            trailColor = Color.valueOf("d99d73");
            trailLength = 35;
            trailWidth = 3f;
            hitSound = Sounds.bang;
        }};
        
        titaniumWallBowling = new BowlingBulletType(3f, 600f){{
            width = height = 8f;
            // pierceCap = 6;
            shrinkX = shrinkY = 0f;
            lifetime = 60f * 3f;
            spin = 5f;
            frontColor = Color.valueOf("ffffff");
            trailColor = Color.valueOf("8da1e3");
            trailLength = 45;
            trailWidth = 4f;
            hitSound = Sounds.bang;
        }};
    }
}
