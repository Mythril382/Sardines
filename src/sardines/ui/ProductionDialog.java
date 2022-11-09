package sardines.ui;

import arc.scene.style.*;
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
        
        cont.table(t -> {
            t.setBackground(Tex.whiteui);
            t.setColor(Pal.darkishGray);
            t.button(new TextureRegionDrawable(Blocks.carbideCrucible.uiIcon), Styles.clearNonei, () -> ui.showInfo("@mini-carb.prodhint")).size(iconXLarge).pad(5);
            t.label(() -> Integer.toString(settings.getInt("mini-carb-prods", 5))).style(Styles.outlineLabel).pad(5);
        }).pad(20);
        cont.row();
        
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
