package weaponsystem;
import com.almasb.fxgl.entity.component.Component;
public class WeaponComponent extends Component{
private Weapon weapon;

    public WeaponComponent(Weapon weapon) {
        this.weapon = weapon;
    }

    public void playerAttack(){
        weapon.attack();
    }
}
