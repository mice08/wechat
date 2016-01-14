import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/**
 * Created by BurizaDo on 1/14/16.
 */
public class TokenController extends Controller {
    @ActionKey("/")
    public void token () {
        renderText(getPara("echostr"));
    }

}
