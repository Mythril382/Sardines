package sardines.ui;

import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.ui.dialogs.*;
import sardines.*;
import sardines.leveling.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class LevelingDialog extends BaseDialog{
    public LevelingDialog(){
        super("@leveling");
        addCloseButton();
        buttons.button("@mini-carb.extra", Icon.upload, () -> ui.showInfo("[lightgray]..."));
        buttons.button("@mini-carb.prods", Icon.hammer, () -> { if(SardVars.ui.prod != null) SardVars.ui.prod.show(); });
        shown(this::rebuild);
        onResize(this::rebuild);
    }
    
    void rebuild(){
        cont.clear();
        boolean hintDone = settings.getBool("leveling-hint", false);
        if(!hintDone) ui.showInfo("@leveling.hint");
        Seq<Levelable> all = LevelingSystem.all();
        cont.table(t -> {
            t.setBackground(Tex.whiteui);
            t.setColor(Pal.darkishGray);
            t.image(Items.carbide.uiIcon).size(iconXLarge).scaling(Scaling.fit).pad(5);
            t.label(() -> Integer.toString(settings.getInt("mini-carbs", 0))).style(Styles.outlineLabel).pad(5);
        }).pad(20).row();
        cont.table(t -> {
            t.setBackground(Tex.whiteui);
            t.setColor(Pal.darkishGray);
            t.image(Blocks.carbideCrucible.uiIcon).size(iconXLarge).scaling(Scaling.fit).pad(5);
            t.label(() -> Integer.toString(settings.getInt("mini-carb-prods", 5))).style(Styles.outlineLabel).pad(5);
        }).pad(20).row();
        cont.pane(list -> {
            all.each(l -> {
                UnlockableContent c = l.content;
                list.table(t -> {
                    t.setBackground(Tex.button);
                    t.table(t1 -> {
                        t1.image(c.uiIcon).size(iconXLarge).scaling(Scaling.fit);
                        t1.add("[accent]" + c.localizedName).padLeft(5);
                    });
                    t.row();
                    if(c.description != null){
                        t.add("[lightgray]" + c.displayDescription()).wrap().fillX().padLeft(0).width(500f).padTop(10).left();
                        t.row();
                    }
                    t.button(l.level() < l.levelCons.size - 1 ? bundle.format("leveling.currentnext", l.level() + 1, l.level() + 2, l.price(l.level() + 1)) : "@leveling.max", () -> {
                        if(l.level() >= l.levelCons.size - 1){
                            ui.showInfo("@leveling.maxalready");
                        }else if(settings.getInt("mini-carbs", 0) < l.price(l.level() + 1)){
                            ui.showInfo("@mini-carb.cantbuy");
                        }else{
                            settings.put("mini-carbs", settings.getInt("mini-carbs", 0) - l.price(l.level() + 1));
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
