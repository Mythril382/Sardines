package sardines;

import arc.*;
import arc.graphics.*;
import arc.math.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.mod.*;
import sardines.content.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class Sardines extends Mod{
    public float counter = 0f;
    
    public Sardines(){
        Events.on(ClientLoadEvent.class, e -> {
            ((BasicBulletType)SardBullets.wallBowling).frontRegion = Blocks.copperWall.fullIcon;
            
            ui.menufrag.addButton("@production", Icon.effect, SardVars.ui.prod::show);
            
            Events.run(Trigger.update, () -> {
                counter += Time.delta;
                
                if(counter >= (60 * 60)){
                    settings.put("mini-carbs", settings.getInt("mini-carbs") + settings.getInt("mini-carb-prods", 5) * 1);
                    counter = 0f;
                }
            });
            
            ui.database.shown(this::setupDatabase);
            Events.run(ResizeEvent.class, () -> {
                setupDatabase();
            });
        });
    }
    
    @Override
    public void loadContent(){
        SardBullets.load();
    }
    
    public void setupDatabase(){
        Table db = (Table)Reflect.get(ui.database, "all");
        TextField search = (TextField)Reflect.get(ui.database, "search");
        
        if(!search.getText().isEmpty()) return;
        
        Seq<LStatement> statements = LogicIO.allStatements.copy().map(prov -> prov.get()).select(lst -> lst.category() != LCategory.unknown);
        
        db.add("@content.lstatement.name").growX().left().color(Pal.spore.cpy().mul(1.25f));
        db.row();
        db.image().growX().pad(5).padLeft(0).padRight(0).height(3).color(Pal.spore.cpy().mul(1.25f));
        db.row();
        
        db.table(list -> {
            list.left();
            
            int cols = (int)Mathf.clamp((graphics.getWidth() - Scl.scl(30)) / Scl.scl(32 + 12), 1, 22);
            int count = 0;
            
            for(int i = 0; i < statements.size; i++){
                LStatement lst = statements.get(i);
                Image image = list.image(Icon.logic).color(lst.category().color).size(8 * 4).pad(3).get();
                
                image.clicked(() -> SardVars.ui.lstInfo.show(lst));
                
                if((++count) % cols == 0){
                    list.row();
                }
            }
        }).growX().left().padBottom(20);
        db.row();
    }
}
