package sardines.leveling;

import arc.func.*;
import arc.struct.*;
import mindustry.ctype.*;

public class Levelable{
    public UnlockableContent content;
    public Seq<Cons<UnlockableContent>> levelCons;
    
    public Levelable(UnlockableContent content, Seq<Cons<UnlockableContent>> levelCons){
        this.content = content;
        this.levelCons = levelCons;
    }
    
    public void runCons(int level){
        Cons<UnlockableContent> cons = levelCons.get(level);
        if(cons == null) return;
        cons.get(content);
    }
}
