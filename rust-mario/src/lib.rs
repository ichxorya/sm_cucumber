#[derive(Debug, PartialEq, Clone, Copy)]
enum State {
    Dead,
    Mario,
    SuperMario,
    FireMario,
    CapeMario
}
use State::*;

#[derive(Debug)]
enum Item {
    Mushroom,
    Flower,
    Feather,
    Coin,
    EnemyBomb
}
use Item::*;

#[no_mangle]
pub extern fn lets_a_go(items: u32, return_type: u32) -> u32 {
    /*
        Return types:
            0: Final state
            1: Final coins

        Return one of these states:
            1: Mario
            2: SuperMario
            3: FireMario
            4: CapeMario
            420: Dead

        Or the number of coins.
    */
    let mut state: State = Mario;
    let mut coin: u32 = 0;

    // For each digit in the number, convert it to an Item and add it to the vector.
    /*
        0: Do nothing
        1: Mushroom
        2: Flower
        3: Feather
        4: Coin
        5: EnemyBomb
        Others: Error

        If the number is 0, there is no state transition.
    */

    if items == 0 {
        if return_type == 0 {
            return 1; // Mario
        } else {
            return coin;
        }
    }

    let mut items_list: Vec<Item> = Vec::new();

    for c in items.to_string().chars() {
        match c {
            '0' => (),
            '1' => items_list.push(Mushroom),
            '2' => items_list.push(Flower),
            '3' => items_list.push(Feather),
            '4' => items_list.push(Coin),
            '5' => items_list.push(EnemyBomb),
            _ => panic!("Invalid item")
        }
    }

    // State transitions.
    for i in items_list {
        match (state, i) {
            (Dead, _) => (),
            (Mario, EnemyBomb) => state = Dead,
            (Mario, Mushroom) => state = SuperMario,
            (SuperMario, EnemyBomb) => state = Mario,
            (_, Flower) => state = FireMario,
            (_, Feather) => state = CapeMario,
            (_, EnemyBomb) => state = SuperMario,
            (_, Coin) => coin += 1,
            _ => ()
        }
    }

    // Return the coin count if that's what we want.
    if return_type == 1 {
        return coin;
    }

    // Return the state (if we don't want the coin count).
    match state {
        Mario => 1,
        SuperMario => 2,
        FireMario => 3,
        CapeMario => 4,
        Dead => 420
    }
}