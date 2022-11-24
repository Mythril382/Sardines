package sardines.entities.bullet;

import arc.math.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;

public class BowlingBulletType extends BasicBulletType{
    public float rotChange = 45f;
    
    public BowlingBulletType(float speed, float damage){
        super(speed, damage);
        
        pierce = pierceBuilding = true;
    }
    
    public BowlingBulletType(float speed, float damage, float rotChange){
        this(speed, damage);
        
        this.rotChange = rotChange;
    }
    
    // there's 100% a better way to do this
    public void changeRot(Bullet b){
        float f = b.fdata;
        
        if(f != 1f && f != 2f){
            b.fdata = f = (float)((int)Mathf.random(1, 2));
            b.rotation(b.rotation() + (f == 1f ? rotChange : -rotChange));
        }else if(Mathf.chance(0.5f)){
            b.fdata = 2f;
            b.rotation(b.rotation() - (rotChange * 2f));
        }else{
            b.fdata = 1f;
            b.rotation(b.rotation() + (rotChange * 2f));
        }
    }
    
    @Override
    public void hitTile(Bullet b, Building build, float x, float y, float initialHealth, boolean direct){
        super.hitTile(b, build, x, y, initialHealth, direct);
        if(direct) changeRot(b);
    }
    
    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){
        super.hitEntity(b, entity, health);
        changeRot(b);
    }
}
