package sardines.core;

import sardines.ui.*;

public class SardUI{
    public LevelingDialog level;
    public ProductionDialog prod;
    
    public SardUI(){
        level = new LevelingDialog();
        prod = new ProductionDialog();
    }
}
