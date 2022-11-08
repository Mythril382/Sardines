package sardines.ui;

import arc.func.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.ui.dialogs.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class LStatementInfoDialog extends BaseDialog{
    protected final ObjectMap<String, Func<LStatement, String>> lStats = new ObjectMap<>();
    
    public LStatementInfoDialog(){
        super("@info.title");
        
        lStats.put("privileged", lst -> bundle.get(lst.privileged() ? "yes" : "no"));
        lStats.put("nonprivileged", lst -> bundle.get(lst.nonPrivileged() ? "yes" : "no"));
        lStats.put("category", lst -> lst.category().localized());
        
        addCloseButton();
    }
    
    public void show(LStatement statement){
        String smallName = statement.name().toLowerCase().replace(" ", "");
        
        cont.clear();
        
        Table table = new Table();
        table.margin(10);
        
        table.table(title1 -> {
            title1.image(Icon.logic).color(statement.category().color).size(iconXLarge).scaling(Scaling.fit);
            title1.add("[accent]" + statement.name() + (settings.getBool("console") ? "\n[gray]" + smallName : "")).padLeft(10);
        });
        table.row();
        
        if(bundle.has("lst." + smallName)){
            table.add("@category.purpose").color(Pal.accent).fillX().left();
            table.row();
            table.add("[lightgray]" + bundle.get("lst." + smallName)).wrap().fillX().padLeft(10).width(500f).left();
            table.row();
        }
        
        table.add("@category.general").color(Pal.accent).fillX().left();
        table.row();
        
        lStats.each((k, v) -> {
            table.table(inset -> {
                inset.left();
                inset.add("[lightgray]" + bundle.get("lstat." + k) + ":[] " + v.get(statement)).fillX().padLeft(10).left().top();
            }).fillX();
            table.row();
        });
        
        ScrollPane pane = new ScrollPane(table);
        cont.add(pane);
        
        show();
    }
}
