package sardines;

import arc.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import sardines.leveling.*;

import static mindustry.Vars.*;

public class Sardines extends Mod{
    public Sardines(){
        Events.on(ClientLoadEvent.class, e -> {
            Vars.ui.menufrag.addButton("@sardui.level", Icon.effect, SardVars.ui.level::show);
        });
    }
    
    @Override
    public void loadContent(){
        LevelingSystem.load();
    }
}
