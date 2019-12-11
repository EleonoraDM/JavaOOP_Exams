package io;

import io.interfaces.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ConsoleReader implements InputReader {
    private BufferedReader reader;
    private CommandsProcessing commands;

    public ConsoleReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.commands = new CommandsProcessing();
    }

    @Override
    public String readLine() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s+");
        String command = tokens[0];
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        return this.commands.commandExecution(command, data);
    }
}
