package StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.example.WasmUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinition {
    String input;
    String return_type;
    String output;
    @Given("I have a Java program")
    public void i_have_a_java_program() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("This is my program");
    }
    @When("I enter the string {string} and {string} as input")
    public void i_enter_the_string_as_input(String input, String return_type) {
        // Write code here that turns the phrase above into concrete actions
        this.input = input;
        this.return_type = return_type;
        this.output = marioStateMachine("src/main/resources/rust_mario.wasm", convertItems(input), convertReturnValue(return_type));
    }
    @Then("I should get {string} as output")
    public void i_should_get_as_output(String output) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(this.output, output);
    }
}
