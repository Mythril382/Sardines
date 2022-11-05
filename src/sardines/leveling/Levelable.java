package sardines.leveling;

import arc.func.*;
import arc.struct.*;
import mindustry.ctype.*;
import mindustry.world.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import static arc.Core.*;

public class Levelable{
    public UnlockableContent content;
    public Seq<Cons<UnlockableContent>> levelCons;
    protected int level = 0;
    
    public Levelable(UnlockableContent content, Seq<Cons<UnlockableContent>> levelCons){
        this.content = content;
        this.levelCons = levelCons;
        level = settings.getInt(content.name + "-sardlevel", 0);
    }
    
    public void runCons(int level){
        Cons<UnlockableContent> cons = levelCons.get(level);
        if(cons == null || level > levelCons.size) return;
        cons.get(content);
        this.level = level;
        settings.put(content.name + "-sardlevel", level);
        
        if(content instanceof Block block){
            Seq<Consume> save = new Seq<>(block.consumers);
            block.consumers = new Consume[0];
            block.nonOptionalConsumers = new Consume[0];
            block.optionalConsumers = new Consume[0];
            block.consPower = null;
            for(Consume consume : save) block.removeConsumer(consume);
        }
        content.stats = new Stats();
        content.init();
    }
}
