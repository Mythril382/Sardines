package sardines.content;

import arc.graphics.*;
import mindustry.entities.bullet.*;
import sardines.entities.bullet.*;

public class SardBullets{
    public static BulletType
    copperWallBowling;
    
    public static void load(){
        copperWallBowling = new BowlingBulletType(1f, 150f){{
            width = height = 6f;
            shrinkX = shrinkY = 0f;
            lifetime = 60f * 2.5f;
            spin = 3f;
            layer = 99f;
            frontColor = Color.valueOf("ffffff");
            trailColor = Color.valueOf("d99d73");
            trailLength = 35;
            trailWidth = 3f;
        }};
    }
}
