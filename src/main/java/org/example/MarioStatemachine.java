package org.example;
import static org.example.WasmUtils.*;

public class MarioStatemachine {
    public static void main(String[] args) {
        String wasmPath = "src/main/resources/rust_mario.wasm";
        int eatThis = convertItems("mushroom nothing mushroom nothing feather flower");
        int returnThis = convertReturnValue("state");
        System.out.println(marioStateMachine(wasmPath, eatThis, returnThis));
    }
}
