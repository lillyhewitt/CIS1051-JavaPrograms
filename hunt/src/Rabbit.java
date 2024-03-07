// strategy: stay put until see fox, then move out of its sight line

public class Rabbit extends Animal {
    // stores whether the fox is in the rabbit's sight line or not
    private boolean canSeeFoxNow = false;
    // stores how many times the fox has moved without seeing the rabbit in its sight line
    private int distanceToFox;
    // stores the direction that the fox is in
    private int directionToFox;
    // stores the current/last direction of the rabbit
    private int currentDirection;

    // creates the rabbit
    public Rabbit(Model model, int row, int column) {

        super(model, row, column);

    }

    // how the rabbit decides to make their move
    int decideMove() {
        // resets variable so that rabbit is unsure if they have seen the fox yet
        canSeeFoxNow = false;
        // look in all possible directions for the fox (if in rabbit's sight line)
        for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; i++) {
            // if they can see the fox, move
            if (look(i) == Model.FOX) {
                // turn and move southwest if possible
                if (canMove(Model.turn(i, 5))) {
                    return Model.turn(i, 5);
                }
                else {
                    // if unable to move southwest, move southeast
                    if (canMove(Model.turn(i, 3))) {
                        return Model.turn(i, 3);
                    }
                    // if unable to move southwest or southeast, move west
                    else if (canMove(Model.turn(i, 6))) {
                        return Model.turn(i, 6);
                    }
                    // if unable to move southwest, southeast, or west, move east
                    else if (canMove(Model.turn(i, 2))) {
                        return Model.turn(i, 2);
                    }
                    // if unable to move southwest, southeast, west, or east, move northeast
                    else if (canMove(Model.turn(i, 1))) {
                        return Model.turn(i, 1);
                    }
                    // if unable to move southwest, southeast, west, east, or northeast, move northwest
                    else if (canMove(Model.turn(i, 7))){
                        return Model.turn(i, 7);
                    }
                }
            }
        }

        // stay if you cannot see the fox or stuck
        return Model.STAY;
    }
}
