package common;

/**
 * This exception should be used to indicate the illegal state when multiple products share the same lane in the vending machine.
 *
 */
public class LaneCodeAlreadyInUseException extends Exception {

    @Override
    public String getMessage() {
        return "This lane code is already in use.";
    }
}
