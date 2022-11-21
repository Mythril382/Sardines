package sardines.content;

import arc.graphics.*;
import mindustry.entities.bullet.*;
import sardines.entities.bullet.*;

public class SardBullets{
    public static BulletType wallBowling;
    
    public static void load(){
        wallBowling = new BowlingBulletType(1f, 150f){{
            width = height = 6f;
            shrinkX = shrinkY = 0f;
            lifetime = 60f * 2.5f;
            spin = 3f;
            layer = 99f;
            frontColor = trailColor = Color.valueOf("ffffff");
            trailLength = 20;
            trailWidth = 3f;
        }};
    }
}
