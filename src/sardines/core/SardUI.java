package sardines.core;

import sardines.ui.*;

public class SardUI{
    public ProductionDialog prod;
    public LStatementInfoDialog lstInfo;
    
    public SardUI(){
        prod = new ProductionDialog();
        lstInfo = new LStatementInfoDialog();
    }
}
