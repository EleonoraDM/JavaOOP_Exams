package core;

import core.interfaces.Engine;
import io.ConsoleReader;
import io.ConsoleWriter;

import java.io.IOException;

public class EngineImpl implements Engine {
    private ConsoleReader reader;
    private ConsoleWriter writer;

    public EngineImpl(ConsoleReader reader, ConsoleWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run() {
        while (true) {
            String result = null;

            try {
                result = this.reader.readLine();

                if (result.equals("End")) {
                    break;
                }
            } catch (NullPointerException | IllegalArgumentException | IOException e) {
                result = e.getMessage();
            }
            this.writer.writeLine(result);
        }
    }
}
