package org.example;
import static org.example.WasmUtils.*;

public class MarioStatemachine {
    public static void main(String[] args) {
        String wasmPath = "src/main/resources/rust_mario.wasm";
        int eatThis = convert("mushroom nothing mushroom nothing feather flower");
        System.out.println(marioStateMachine(wasmPath, eatThis));
    }
}

//    @Given("I have a Java program")
//    public void i_have_a_java_program(){
//        // Write code here that turns the phrase above into concrete actions
////        marioStatemachine = new MarioStatemachine();\
//        System.out.println("this is my program");
//    }
//    @When("I enter the string {string} as input")
//    public void i_enter_the_string_as_input(String string) throws Throwable{
//        input = string;
//        output = marioStateMachine("src/main/resources/rust_mario.wasm", convert(input));
//    }
//    @Then("I should get {string} as output")
//    public void i_should_get_as_output(String string) throws Throwable{
//        // Write code here that turns the phrase above into concrete actions
//        assertEquals(output, string);
//    }
