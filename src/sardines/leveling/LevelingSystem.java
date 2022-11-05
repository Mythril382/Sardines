package sardines.leveling;

import arc.func.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.type.*;
import mindustry.world.blocks.defense.turrets.*;

import static arc.Core.*;

public class LevelingSystem{
    protected static Seq<Levelable> levelables = new Seq<>();
    
    public static void load(){
        add(Blocks.duo, Seq.with(
            c -> {
                Turret b = (Turret)c;
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
                Turret b = (Turret)c;
                b.health = 360;
                b.requirements = ItemStack.with(Items.copper, 50);
                b.reload = 18f;
                b.shoot.shots = 3;
                b.shoot.shotDelay = 5f;
                b.ammoTypes.get(Items.copper).damage = 11f;
                b.ammoTypes.get(Items.graphite).damage = 20f;
                b.ammoTypes.get(Items.silicon).damage = 14f;
            },
            c -> {
                Turret b = (Turret)c;
                b.health = 520;
                b.requirements = ItemStack.with(Items.copper, 85, Items.lead, 30);
                b.reload = 16f;
                b.shoot.shots = 5;
                b.shoot.shotDelay = 2.5f;
                b.ammoTypes.get(Items.copper).damage = 14f;
                b.ammoTypes.get(Items.graphite).damage = 23f;
                b.ammoTypes.get(Items.silicon).damage = 15f;
            }
        ));
        level(Blocks.duo, settings.getInt("duo-slevel", 0));
    }
    
    public static Seq<Levelable> all(){
        return levelables;
    }
    
    protected static void add(UnlockableContent content, Seq<Cons<UnlockableContent>> levelCons){
        levelables.add(new Levelable(content, levelCons));
    }
    
    protected static void level(UnlockableContent content, int level){
        Levelable lb = levelables.find(l -> l.content == content);
        lb.runCons(level);
    }
}
