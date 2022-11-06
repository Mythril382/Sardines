package sardines.leveling;

import arc.func.*;
import arc.struct.*;
import arc.util.*;
import mindustry.ctype.*;
import mindustry.gen.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import static arc.Core.*;

public class Levelable{
    public UnlockableContent content;
    public Seq<Cons<UnlockableContent>> levelCons;
    public Seq<Integer> prices;
    protected int level = 0;
    
    public Levelable(UnlockableContent content, Seq<Cons<UnlockableContent>> levelCons, Seq<Integer> prices){
        this.content = content;
        this.levelCons = levelCons;
        this.prices = prices;
        level = settings.getInt(content.name + "-slevel", 0);
    }
    
    public void runCons(int level){
        Cons<UnlockableContent> cons = levelCons.get(level);
        if(cons == null || level > levelCons.size) return;
        cons.get(content);
        this.level = level;
        settings.put(content.name + "-slevel", level);
        
        if(content instanceof Block block){
            Seq<Consume> consumers = new Seq<>(block.consumers);
            Seq<Consume> optionals = new Seq<>(block.optionalConsumers);
            consumers.removeAll(optionals);
            block.consumers = new Consume[0];
            block.nonOptionalConsumers = new Consume[0];
            block.optionalConsumers = new Consume[0];
            block.consPower = null;
            for(Consume consume : consumers) block.removeConsumer(consume);
            
            try{
                ObjectMap<String, Func<Building, Bar>> bars = (ObjectMap<String, Func<Building, Bar>>)Reflect.get(block, "barMap");
                bars.clear();
            }catch(Throwable ignored){}
        }
        content.stats = new Stats();
        content.init();
    }
    
    public int level(){
        return level;
    }
    
    public int price(int level){
        return prices.get(level);
    }
}
