package EventBus;
 
import com.google.common.eventbus.EventBus;
 
public class SimpleEventBusExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        System.out.println("Post Simple EventBus Example");
        
        eventBus.register(new OtroSimpleListener());
        System.out.println("post another simple eventbus example");
        eventBus.post("Simple EventBus Example");

        eventBus.post("otro nuevo mensage event bus");
    }
}