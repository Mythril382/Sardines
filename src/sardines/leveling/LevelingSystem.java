package sardines.leveling;

import arc.func.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.type.*;
import mindustry.world.*;

import static arc.Core.*;

public class LevelingSystem{
    protected Seq<Levelable> levelables = new Seq<>();
    
    public static void load(){
        add(Blocks.duo, Seq.with(
            c -> {}, // level 1, nothing
            c -> {
                Block b = (Block)c;
                b.health = 400;
                b.requirements = ItemStack.with(Items.copper, 45);
            },
            c -> {
                Block b = (Block)c;
                b.health = 480;
                b.requirements = ItemStack.with(Items.copper, 50, Items.lead, 20);
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
