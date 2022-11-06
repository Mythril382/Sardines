package sardines.leveling;

import arc.func.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.type.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;

import static arc.Core.*;

public class LevelingSystem{
    protected static Seq<Levelable> levelables = new Seq<>();
    
    public static void load(){
        add(Blocks.duo, Seq.with(
            c -> {
                ItemTurret b = (ItemTurret)c;
                b.health = 250;
                b.requirements = ItemStack.with(Items.copper, 35);
                b.reload = 20f;
                b.shoot.shots = 1;
                b.shoot.shotDelay = 0f;
                b.ammoTypes.get(Items.copper).damage = 9f;
                b.ammoTypes.get(Items.graphite).damage = 18f;
                b.ammoTypes.get(Items.silicon).damage = 12f;
            },
            c -> {
                ItemTurret b = (ItemTurret)c;
                b.health = 360;
                b.requirements = ItemStack.with(Items.copper, 50, Items.lead, 30);
                b.reload = 18f;
                b.shoot.shots = 3;
                b.shoot.shotDelay = 5f;
                b.ammoTypes.get(Items.copper).damage = 11f;
                b.ammoTypes.get(Items.graphite).damage = 20f;
                b.ammoTypes.get(Items.silicon).damage = 14f;
            },
            c -> {
                ItemTurret b = (ItemTurret)c;
                b.health = 520;
                b.requirements = ItemStack.with(Items.copper, 85, Items.graphite, 35);
                b.reload = 16f;
                b.shoot.shots = 5;
                b.shoot.shotDelay = 2.5f;
                b.ammoTypes.get(Items.copper).damage = 14f;
                b.ammoTypes.get(Items.graphite).damage = 23f;
                b.ammoTypes.get(Items.silicon).damage = 15f;
            }
        ), Seq.with(0, 100, 300));
        level(Blocks.duo, settings.getInt("duo-slevel", 0));
        
        add(Blocks.copperWall, Seq.with(
            c -> {
                Wall b = (Wall)c;
                b.health = 320;
                b.requirements = ItemStack.with(Items.copper, 6);
            },
            c -> {
                Wall b = (Wall)c;
                b.health = 440;
                b.requirements = ItemStack.with(Items.copper, 12);
            },
            c -> {
                Wall b = (Wall)c;
                b.health = 560;
                b.requirements = ItemStack.with(Items.copper, 18);
            }
        ), Seq.with(0, 120, 250));
        level(Blocks.copperWall, settings.getInt("copper-wall-slevel", 0));
        
        add(Blocks.copperWallLarge, Seq.with(
            c -> {
                Wall b = (Wall)c;
                b.health = 1280;
                b.requirements = ItemStack.with(Items.copper, 24);
            },
            c -> {
                Wall b = (Wall)c;
                b.health = 1300;
                b.requirements = ItemStack.with(Items.copper, 32);
            },
            c -> {
                Wall b = (Wall)c;
                b.health = 1420;
                b.requirements = ItemStack.with(Items.copper, 40);
            }
        ), Seq.with(0, 250, 400));
        level(Blocks.copperWallLarge, settings.getInt("copper-wall-large-slevel", 0));
    }
    
    public static Seq<Levelable> all(){
        return levelables;
    }
    
    protected static void add(UnlockableContent content, Seq<Cons<UnlockableContent>> levelCons, Seq<Integer> prices){
        levelables.add(new Levelable(content, levelCons, prices));
    }
    
    protected static void level(UnlockableContent content, int level){
        Levelable lb = levelables.find(l -> l.content == content);
        lb.runCons(level);
    }
}
