package sardines.ui;

import arc.struct.*;
import arc.util.*;
import mindustry.ctype.*;
import mindustry.gen.*;
import mindustry.ui.dialogs.*;
import sardines.leveling.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class LevelingDialog extends BaseDialog{
    public LevelingDialog(){
        super("@sardui.level");
        addCloseButton();
        shown(this::rebuild);
        onResize(this::rebuild);
    }
    
    void rebuild(){
        cont.clear();
        Seq<Levelable> all = LevelingSystem.all();
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
                    t.button(l.level() < l.levelCons.size - 1 ? bundle.format("leveling.level", l.level()) : "@leveling.max", () -> {
                        if(l.level() >= l.levelCons.size - 1) return;
                        l.runCons(l.level() + 1);
                        ui.showInfo(bundle.format("sard.levelup", c.localizedName, l.level()));
                        this.rebuild();
                    }).pad(10).growX();
                }).pad(10).growX();
                if(all.indexOf(l) < all.size - 1) list.row();
            });
        });
    }
}
