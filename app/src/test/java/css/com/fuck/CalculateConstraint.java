package css.com.fuck;

import org.junit.Test;

public class CalculateConstraint {
    @Test
    public void show() {
        double[] input = new double[]{
                20, 162,
                1, 196,
                29, 476,
                17, 528.4,
                17, 564.8,
        };
        for (int i = 0; i < input.length / 2; i++) {
            pringVers(input[i * 2], input[i * 2 + 1]);
        }

    }

    private void pringVers(double height, double viewTop2PageTop) {
        pringMsg(0, height, 0, viewTop2PageTop);
    }

    private void pringHors(double witdh, double viewLeft2PageLeft) {
        pringMsg(witdh, 0, viewLeft2PageLeft, 0);
    }

    private void pringMsg(double witdh, double height, double viewLeft2PageLeft, double viewTop2PageTop) {
        double measureWidth = 375;
        double measureHeight = 667;
        double constraintVertical = viewTop2PageTop / (measureHeight - height);
        double constraintHorizontal = viewLeft2PageLeft / (measureWidth - witdh);
        System.out.println("ver:" + constraintVertical + " hor:" + constraintHorizontal);
    }
}
