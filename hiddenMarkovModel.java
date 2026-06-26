public class hiddenMarkovModel {

    public static void main(String[] args) {

        double[][] A = {
                {0.7, 0.3},
                {0.4, 0.6}
        };

        double[][] B = {
                {0.5, 0.5},
                {0.1, 0.9}
        };

        double[] PI = {0.6, 0.4};

        int[] O = {0, 1, 0}; 

        int N = A.length;
        int T = O.length;

        double[][] alpha = new double[T][N];

        for (int i = 0; i < N; i++) {
            alpha[0][i] = PI[i] * B[i][O[0]];
        }

        for (int t = 1; t < T; t++) {
            for (int j = 0; j < N; j++) {
                alpha[t][j] = 0;
                for (int i = 0; i < N; i++) {
                    alpha[t][j] += alpha[t - 1][i] * A[i][j];
                }
                alpha[t][j] *= B[j][O[t]];
            }
        }

        double probability = 0;
        for (int i = 0; i < N; i++) {
            probability += alpha[T - 1][i];
        }

        System.out.println("Probability = " + probability);
    }
}