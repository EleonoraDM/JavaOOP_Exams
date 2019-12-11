import core.EngineImpl;
import core.interfaces.Engine;
import io.ConsoleReader;
import io.ConsoleWriter;

public class Main {
    public static void main(String[] args) {

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();

        Engine engine = new EngineImpl(reader, writer);
        engine.run();
    }
}
