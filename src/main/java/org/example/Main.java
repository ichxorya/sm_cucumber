package org.example;

import org.wasmer.Instance;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

class WasmTest {
    public static void main(String[] args) {
        Path wasmPath = Path.of("src", "main", "resources", "rust_mario.wasm");
        byte[] wasmBytes;
        try {
            wasmBytes = Files.readAllBytes(wasmPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Instance instance = new Instance(wasmBytes);
        Object[] results = instance.exports.getFunction("lets_a_go").apply(3);
        System.out.println((results[0]));
        instance.close();
    }
}
