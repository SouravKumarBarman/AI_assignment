public class alphaBetaPruning {

    static int alphaBeta(int depth, int nodeIndex,
                         boolean maximizingPlayer,
                         int values[],
                         int alpha, int beta) {

        if (depth == 3)
            return values[nodeIndex];

        if (maximizingPlayer) {
            int best = Integer.MIN_VALUE;

            for (int i = 0; i < 2; i++) {
                int val = alphaBeta(depth + 1,
                        nodeIndex * 2 + i,
                        false,
                        values,
                        alpha,
                        beta);

                best = Math.max(best, val);
                alpha = Math.max(alpha, best);

                if (beta <= alpha) {
                    System.out.println("Pruned at depth " + depth);
                    break;
                }
            }
            return best;
        } else {

            int best = Integer.MAX_VALUE;

            for (int i = 0; i < 2; i++) {
                int val = alphaBeta(depth + 1,
                        nodeIndex * 2 + i,
                        true,
                        values,
                        alpha,
                        beta);

                best = Math.min(best, val);
                beta = Math.min(beta, best);

                if (beta <= alpha) {
                    System.out.println("Pruned at depth " + depth);
                    break;
                }
            }
            return best;
        }
    }

    public static void main(String[] args) {

        int values[] = {3, 5, 6, 9, 1, 2, 0, -1};

        int result = alphaBeta(
                0,
                0,
                true,
                values,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE
        );

        System.out.println("Optimal Value = " + result);
    }
}