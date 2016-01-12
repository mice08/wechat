import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/**
 * Created by Mi on 16/1/12.
 */
public class TokenController extends Controller {

    @ActionKey("/")
    public void token () {
        renderJson("echostr",getPara("echostr"));
    }
}
