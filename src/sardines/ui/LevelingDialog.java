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
                    l.levelCons.each(cons -> {
                        t.button(bundle.format("sardmain.level", l.levelCons.indexOf(cons) + 1), () -> {
                            l.runCons(l.levelCons.indexOf(cons));
                            ui.showInfo(bundle.format("sardmain.levelup", c.localizedName, l.levelCons.indexOf(cons) + 1));
                        }).growX();
                        if(l.levelCons.indexOf(cons) < l.levelCons.size - 1) t.row();
                    });
                }).growX();
                if(all.indexOf(l) < all.size - 1) list.row();
            });
        });
    }
}
