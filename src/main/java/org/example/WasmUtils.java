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

    public static String marioStatemachine(String wasmPath, Object... inputs) {
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
}
