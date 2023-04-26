package org.example;
import static org.example.WasmUtils.*;

public class MarioStatemachine {
    public static void main(String[] args) {
        String wasmPath = "src/main/resources/rust_mario.wasm";
        int eatThis = convertItems("mushroom coin nothing feather bomb bomb bomb feather feather");
        int returnThis = convertReturnValue("final_coins");
        System.out.println(marioStateMachine(wasmPath, eatThis, returnThis));
    }
}
