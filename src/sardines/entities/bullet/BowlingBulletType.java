package sardines.entities.bullet;

import arc.math.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;

public class BowlingBulletType extends BasicBulletType{
    public float rotChange = 45f;
    
    public BowlingBulletType(float speed, float damage){
        super(speed, damage);
        
        pierce = true;
    }
    
    public BowlingBulletType(float speed, float damage, float rotChange){
        this(speed, damage);
        
        this.rotChange = rotChange;
    }
    
    @Override
    public void hit(Bullet b){
        super.hit(b);
        
        float f = b.fdata;
        
        if(f != 1f && f != 2f){
            // there's 100% a better way to do this
            b.fdata = f = (float)((int)Mathf.random(1, 2));
            b.rotation(b.rotation() + (f == 1f ? rotChange : -rotChange));
        }else if(f == 1f){
            b.fdata = 2f;
            b.rotation(b.rotation() - (rotChange * 2f));
        }else{
            b.fdata = 1f;
            b.rotation(b.rotation() + (rotChange * 2f));
        }
    }
}
