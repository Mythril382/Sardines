package sardines.leveling;

import arc.func.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.type.*;
import mindustry.world.defense.turrets.*;

import static arc.Core.*;

public class LevelingSystem{
    protected static Seq<Levelable> levelables = new Seq<>();
    
    public static void load(){
        add(Blocks.duo, Seq.with(
            c -> {
                Turret b = (Turret)c;
                b.health = 250;
                b.requirements = ItemStack.with(Items.copper, 35);
                b.shoot.shots = 1;
                b.shoot.shotDelay = 0f;
            },
            c -> {
                Turret b = (Turret)c;
                b.health = 360;
                b.requirements = ItemStack.with(Items.copper, 50);
                b.shoot.shots = 2;
                b.shoot.shotDelay = 5f;
            },
            c -> {
                Turret b = (Turret)c;
                b.health = 520;
                b.requirements = ItemStack.with(Items.copper, 85, Items.lead, 30);
                b.shoot.shots = 4;
                b.shoot.shotDelay = 2.5f;
            }
        ));
        level(Blocks.duo, settings.getInt("duo-sardlevel", 0));
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
