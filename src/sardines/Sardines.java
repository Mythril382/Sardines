package sardines;

import arc.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import sardines.leveling.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class Sardines extends Mod{
    public float counter = 0f;
    
    public Sardines(){
        Events.on(ClientLoadEvent.class, e -> {
            ui.menufrag.addButton("@leveling", Icon.effect, SardVars.ui.level::show);
            Events.run(Trigger.update, () -> {
                counter += Time.delta;
                if(counter >= (60 * 60)){
                    settings.put("mini-carbs", settings.getInt("mini-carbs") + settings.getInt("mini-carb-prods", 5) * 1);
                    counter = 0f;
                }
            });
        });
    }
    
    @Override
    public void loadContent(){
        LevelingSystem.load();
    }
}
