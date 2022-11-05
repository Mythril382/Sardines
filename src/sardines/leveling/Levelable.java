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
        
        content.stats = new Stats();
        if(content instanceof Block block){
            Seq<Consume> consumers = Seq.with(something.consumers);
            Seq<Consume> optional = Seq.with(something.optionalConsumers);
            consumers.removeAll(optional);
            something.consumers = optional.toArray(Consume.class);
            for(Consume consumer : consumers) something.removeConsumer(consumer);
        }
        content.init();
    }
}
