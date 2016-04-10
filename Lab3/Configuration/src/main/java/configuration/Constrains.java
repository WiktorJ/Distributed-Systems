package configuration;


/**
 * Created by wiktor on 09/04/16.
 */
public class Constrains {
    private final int leftOperandMin;
    private final int leftOperandMax;
    private final int rightOperandMin;
    private final int rightOperandMax;

    public Constrains(int leftOperandMin, int leftOperandMax, int rightOperandMin, int rightOperandMax) {
        this.leftOperandMin = leftOperandMin;
        this.leftOperandMax = leftOperandMax;
        this.rightOperandMin = rightOperandMin;
        this.rightOperandMax = rightOperandMax;
    }

    public int getLeftOperandMin() {
        return leftOperandMin;
    }

    public int getLeftOperandMax() {
        return leftOperandMax;
    }

    public int getRightOperandMin() {
        return rightOperandMin;
    }

    public int getRightOperandMax() {
        return rightOperandMax;
    }
}
