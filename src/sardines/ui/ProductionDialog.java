package sardines.ui;

import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.ui.dialogs.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class ProductionDialog extends BaseDialog{
    public ProductionDialog(){
        super("@mini-carb.prods");
        addCloseButton();
        shown(this::rebuild);
        onResize(this::rebuild);
    }
    
    void rebuild(){
        cont.clear();
        Table counter = cont.table(t -> {
            t.setBackground(Tex.whiteui);
            t.setColor(Pal.darkishGray);
            t.image(Blocks.carbideCrucible.uiIcon).size(iconXLarge).scaling(Scaling.fit).pad(5);
            t.label(() -> Integer.toString(settings.getInt("mini-carb-prods", 5))).style(Styles.outlineLabel).pad(5);
        }).pad(20).get();
        counter.clicked(() -> ui.showInfo("mini-carb.prodhint"));
        cont.row()
        cont.button("@mini-carb.prodbuy", () -> {
            int mc = settings.getInt("mini-carbs", 5);
            if(mc < 5){
                ui.showInfo("@mini-carb.cantbuy");
            }else{
                settings.put("mini-carbs", mc - 5);
                settings.put("mini-carb-prods", settings.getInt("mini-carb-prods", 5) + 1);
                ui.showInfo("@mini-carb.prodbought");
                this.rebuild();
            }
        }).growX();
    }
}
