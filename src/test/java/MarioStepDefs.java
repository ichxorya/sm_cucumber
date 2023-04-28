import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.example.WasmUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class MarioStepDefs {
    String items = "";
    String returnType;
    String returnValue;

    @Given("Mario is alive and we want to know his final state")
    public void marioIsAliveAndWeWantToKnowHisFinalState() {
        this.returnType = "final_state";
        this.items = "Nothing ";
    }

    @Given("Mario is alive and we want to know his final coins")
    public void marioIsAliveAndWeWantToKnowHisFinalCoins() {
        this.returnType = "final_coins";
        this.items = "Nothing ";
    }

    @Given("Mario is dead and we want to know his final state")
    public void marioIsDeadAndWeWantToKnowHisFinalState() {
        this.items = "Bomb ";
        this.returnType = "final_state";
    }
    @When("Mario touches {string}")
    public void marioTouches(String items) {
        this.items += items;
        this.returnValue = marioStateMachine(
                "src/main/resources/rust_mario.wasm",
                convertItems(this.items),
                convertReturnValue(this.returnType)
        );
    }

    @Then("Mario is {string}")
    public void marioIs(String returnValue) {
        assertEquals(returnValue, this.returnValue);
    }

    @Then("Mario has {string} coins")
    public void marioHasCoins(String returnValue) {
        assertEquals(returnValue, this.returnValue);
    }

    @Given("Mario is dead")
    public void marioIsDead() {
        this.items = "Bomb ";
    }


    @And("We want to know {string}")
    public void weWantToKnow(String returnType) {
        this.returnType = returnType;
    }

    @Then("What we get is {string}")
    public void whatWeGetIs(String returnValue) {
        assertEquals(returnValue, this.returnValue);
    }

    @And("we want to know the return value of {string}")
    public void weWantToKnowTheReturnValueOf(String returnType) {
        this.returnType = returnType;
    }

    @Given("Mario will be dead and we want to know his final value of {string}")
    public void marioWillBeDeadAndWeWantToKnowHisFinalValueOf(String returnType) {
        this.returnType = returnType;
    }
}
