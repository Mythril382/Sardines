package sardines.content;

import arc.graphics.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import sardines.entities.bullet.*;

public class SardBullets{
    public static BulletType copperWallBowling, titaniumWallBowling, scrapWallBowling, leadWallBowling;
    
    public static void load(){
        copperWallBowling = new BowlingBulletType(3f, 200f){{
            width = height = 6f;
            shrinkX = shrinkY = 0f;
            lifetime = 60f * 180f;
            spin = 3f;
            frontColor = Color.valueOf("ffffff");
            trailColor = Color.valueOf("d99d73");
            trailLength = 35;
            trailWidth = 3f;
            hitSound = Sounds.bang;
            sprite = "copper-wall";
        }};
        
        titaniumWallBowling = new BowlingBulletType(3f, 600f){{
            width = height = 8f;
            shrinkX = shrinkY = 0f;
            lifetime = 60f * 180f;
            spin = 5f;
            frontColor = Color.valueOf("ffffff");
            trailColor = Color.valueOf("8da1e3");
            trailLength = 45;
            trailWidth = 4f;
            hitSound = Sounds.bang;
            sprite = "titanium-wall";
        }};
        
        scrapWallBowling = new BowlingBulletType(3f, 50f){{
            width = height = 4f;
            shrinkX = shrinkY = 0f;
            pierceCap = 3;
            lifetime = 60f * 180f;
            spin = 8f;
            frontColor = Color.valueOf("ffffff");
            trailColor = Color.valueOf("b2c6d2");
            trailLength = 25;
            trailWidth = 2f;
            hitSound = Sounds.pew;
            sprite = "scrap-wall1";
            rotChange = 45f / 2f;
        }};
        
        leadWallBowling = new BowlingBulletType(3.5f, 150f){{
            width = height = 7f;
            shrinkX = shrinkY = 0f;
            lifetime = 60f * 180f;
            spin = 3.5f;
            frontColor = Color.valueOf("ffffff");
            trailColor = Color.valueOf("ab99d3");
            trailLength = 35;
            trailWidth = 3f;
            hitSound = Sounds.bang;
            sprite = "sardines-lead-wall-bowling";
            bounceBack = true;
        }};
    }
}
