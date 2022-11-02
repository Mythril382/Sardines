package sardines.leveling;

import arc.func.*;
import arc.struct.*;
import mindustry.ctype.*;

import static arc.Core.*;

public class Levelable{
    public UnlockableContent content;
    public Seq<Cons<UnlockableContent>> levelCons;
    protected int level = 0, maxLevel;
    
    public Levelable(UnlockableContent content, Seq<Cons<UnlockableContent>> levelCons, int maxLevel){
        this.content = content;
        this.levelCons = levelCons;
        level = settings.getInt(content.name + "-sardlevel", 0);
        this.maxLevel = maxLevel;
    }
    
    public void runCons(int level){
        Cons<UnlockableContent> cons = levelCons.get(level - 1);
        if(cons == null || level - 1 > maxLevel) return;
        cons.get(content);
        this.level = level - 1;
        settings.put(content.name + "-sardlevel", level - 1);
    }
}
