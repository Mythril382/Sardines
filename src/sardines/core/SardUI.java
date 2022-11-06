package sardines.core;

import sardines.ui.*;

public class SardUI{
    public ProductionDialog prod;
    public LevelingDialog level;
    
    public SardUI(){
        prod = new ProductionDialog();
        level = new LevelingDialog();
    }
}
