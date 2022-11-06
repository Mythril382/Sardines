package sardines.ui;

import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.dialogs.*;
import sardines.leveling.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class LevelingDialog extends BaseDialog{
    public LevelingDialog(){
        super("@leveling");
        addCloseButton();
        shown(this::rebuild);
        onResize(this::rebuild);
    }
    
    void rebuild(){
        cont.clear();
        Seq<Levelable> all = LevelingSystem.all();
        cont.table(count -> {
            count.setBackground(Tex.whiteui);
            count.setColor(Pal.darkishGray);
            count.image(Items.carbide.uiIcon).size(iconXLarge).scaling(Scaling.fit);
            count.label(() -> Integer.toString(settings.getInt("mini-carbs", 0))).padLeft(5);
        }).padBottom(10).row();
        cont.pane(list -> {
            all.each(l -> {
                UnlockableContent c = l.content;
                list.table(t -> {
                    t.setBackground(Tex.button);
                    t.table(title1 -> {
                        title1.image(c.uiIcon).size(iconXLarge).scaling(Scaling.fit);
                        title1.add("[accent]" + c.localizedName).padLeft(5);
                    });
                    t.row();
                    if(c.description != null){
                        t.add("[lightgray]" + c.displayDescription()).wrap().fillX().padLeft(0).width(500f).padTop(10).left();
                        t.row();
                    }
                    t.button(l.level() < l.levelCons.size - 1 ? bundle.format("leveling.currentnext", l.level() + 1, l.level() + 2) : "@leveling.max", () -> {
                        if(l.level() >= l.levelCons.size - 1){
                            ui.showInfo("@leveling.maxalready");
                        }else{
                            l.runCons(l.level() + 1);
                            ui.showInfo(bundle.format("leveling.up", c.localizedName, l.level() + 1));
                        }
                        this.rebuild();
                    }).pad(10).growX();
                }).pad(10).growX();
                if(all.indexOf(l) < all.size - 1) list.row();
            });
        });
    }
}
