package sardines.content;

import mindustry.entities.bullet.*;
import sardines.entities.bullet.*;

public class SardBullets{
    public static BulletType wallBowling;
    
    public static void load(){
        wallBowling = new BowlingBulletType(1f, 150f){{
            width = height = 4f * 32f;
        }};
    }
}
