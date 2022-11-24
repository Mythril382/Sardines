package sardines.content;

import arc.graphics.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import sardines.entities.bullet.*;

public class SardBullets{
    public static BulletType
    copperWallBowling;
    
    public static void load(){
        copperWallBowling = new BowlingBulletType(1f, 200f){{
            width = height = 6f;
            pierceCap = 4;
            shrinkX = shrinkY = 0f;
            lifetime = 60f * 2.5f;
            spin = 3f;
            layer = 99f;
            frontColor = Color.valueOf("ffffff");
            trailColor = Color.valueOf("d99d73");
            trailLength = 35;
            trailWidth = 3f;
            hitSound = Sounds.bang;
        }};
    }
}
