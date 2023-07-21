import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public class Names {

    @MyTarget(value = "halView")
    private String hal = "My mind is going. I can feel it";

    @MyTarget(value = "davidView")
    private String david = "David here";

    public String getHal() {
        return hal;
    }

    public void setHal(String hal) {
        this.hal = hal;
    }

    public String getDavid() {
        return david;
    }

    public void setDavid(String david) {
        this.david = david;
    }
}
