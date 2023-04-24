package org.example;

import org.wasmer.Instance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WasmUtils {
    public static Instance createInstance(String wasmPath) {
        byte[] wasmBytes = WasmUtils.readWasmBytes(wasmPath);
        return new Instance(wasmBytes);
    }

    public static byte[] readWasmBytes(String wasmPathStr) {
        Path wasmPath = Path.of(wasmPathStr);
        byte[] wasmBytes;
        try {
            wasmBytes = Files.readAllBytes(wasmPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return wasmBytes;
    }

    public static int functionAndInputs(String wasmPath, String functionName, Object... inputs) {
        Instance instance = createInstance(wasmPath);
        Object[] results = instance.exports.getFunction(functionName).apply(inputs);
        instance.close();
        return (int) results[0];
    }

    public static String marioStateMachine(String wasmPath, Object... inputs) {
        int marioState = functionAndInputs(wasmPath, "lets_a_go", inputs);

        /*
            Switch-case:
                1: Mario
                2: SuperMario
                3: FireMario
                4: CapeMario
        */
        return switch (marioState) {
            case 1 -> "Mario";
            case 2 -> "SuperMario";
            case 3 -> "FireMario";
            case 4 -> "CapeMario";

            // This won't happen.
            default -> throw new IllegalStateException("Unexpected value: " + marioState);
        };
    }

    public static int convert(String input) {
        // Upper case the input then split the string by spaces.
        String[] split = input.toUpperCase().split(" ");

        // Replace every string in that array with the corresponding integer.
        for (int i = 0; i < split.length; i++) {
            split[i] = switch (split[i]) {
                case "NOTHING" -> "0";
                case "MUSHROOM" -> "1";
                case "FLOWER" -> "2";
                case "FEATHER" -> "3";
                default -> throw new IllegalStateException("Unexpected value: " + split[i]);
            };
        }

        // Turn the array into a string and parse it to an integer.
        try {
            return Integer.parseInt(String.join("", split));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid input.");
        }
    }
}
