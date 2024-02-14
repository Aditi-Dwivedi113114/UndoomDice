import java.util.Arrays;

public class dice{

    static int[][] calculateDistributionMatrix(int[] dieA, int[] dieB) {
        int facesOnDieA = dieA.length;
        int facesOnDieB = dieB.length;
        int totalCombinations = facesOnDieA * facesOnDieB;
        System.out.println("Total Combinations: " + totalCombinations);
        
        int[][] distributionMatrix = new int[facesOnDieA][facesOnDieB];

        for (int i = 0; i < facesOnDieA; i++) {
            for (int j = 0; j < facesOnDieB; j++) {
                int sum = dieA[i] + dieB[j];
                distributionMatrix[i][j] += 1;
            }
        }

        return distributionMatrix;
    }

    static void displayDistributionMatrix(int[][] distributionMatrix) {
        System.out.println("Distribution of Combinations:");
        for (int i = 0; i < distributionMatrix.length; i++) {
            System.out.println(Arrays.toString(distributionMatrix[i]));
        }
    }

    static double[] calculateProbabilityOfSums(int[] dieA, int[] dieB, int[][] distributionMatrix) {
        int facesOnDieA = dieA.length;
        int facesOnDieB = dieB.length;
        int totalCombinations = facesOnDieA * facesOnDieB;

        System.out.println("\nProbability of Sums:");
        double[] probabilities = new double[facesOnDieA + facesOnDieB + 1];

        for (int sum = 2; sum <= facesOnDieA + facesOnDieB; sum++) {
            int count = 0;
            for (int i = 0; i < facesOnDieA; i++) {
                int j = sum - dieA[i];
                if (j >= 1 && j <= facesOnDieB) {
                    count += distributionMatrix[i][j - 1];
                }
            }
            probabilities[sum] = (double) count / totalCombinations;
            System.out.println("P(Sum = " + sum + ") = " + probabilities[sum]);
        }

        return probabilities;
    }

    static int[] undoomDice(int[] dieA, int[] dieB) {
        int facesOnDieA = dieA.length;
        int maxSpots = 4;

        int[][] distributionMatrix = calculateDistributionMatrix(dieA, dieB);
        displayDistributionMatrix(distributionMatrix);

        double[] originalProbabilities = calculateProbabilityOfSums(dieA, dieB, distributionMatrix);

        // Calculate and display the undoomed dice
        System.out.println("\nUndoomed Dice:");
        int[] newDieA = new int[facesOnDieA];
        for (int i = 0; i < facesOnDieA; i++) {
            newDieA[i] = Math.min(maxSpots, dieA[i]);
        }
        System.out.println("New Die A: " + Arrays.toString(newDieA));

        return newDieA;
    }

    public static void main(String[] args) {
        int[] dieA = {1, 2, 3, 4, 5, 6};
        int[] dieB = {1, 2, 3, 4, 5, 6};

        int[] newDieA = undoomDice(dieA, dieB);
    }
}
