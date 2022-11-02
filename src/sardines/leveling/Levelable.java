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
        Cons<UnlockableContent> cons = levelCons.get(level);
        if(cons == null || level == maxLevel) return;
        cons.get(content);
        this.level = level;
        settings.put(content.name + "-sardlevel", level);
    }
}
