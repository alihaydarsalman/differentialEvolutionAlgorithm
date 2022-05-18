import java.util.Random;
import java.util.Scanner;

public class DifferentialEvolutionAlg {

    /**
      This project is an algorithm that performs global optimization by taking the following control parameters from the user.

      control parameters:

      number of population: NP
      maximum generation(max iteration): maxGen
      decision parameter lower bound: lB
      decision parameter upper bound: uB
      scaling factor: f
      crossover rate: cR
      Run number: runNum
      dimension of problem (decision variable size): dim

      Since it is an algorithm that can show approximate but different results in each run,
      the average or standard deviation of the outputs should be taken to test its accuracy.
      Therefore,
      The runNum variable is taken from the user how many times the algorithm should be run in order to test its robustness.

      Note: Optimum objective function value is zero.
     **/

    public static int findBest(Individual[] pop) {

        double k = pop[0].objVal;
        int index = 0;

        for (int i = 0; i < pop.length; i++) {
            if (k > pop[i].objVal) {
                k = pop[i].objVal;
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {

        Random rn = new Random();
        Scanner scan = new Scanner(System.in);

        System.out.print("\n\t\t\tWELCOME TO DIFFERENTIAL ALGORITHM CALCULATOR\nPlease enter following control parameters to calculating your problem.\n\n");

        System.out.print("Enter number of population: ");
        int nP = scan.nextInt();

        System.out.print("Enter maximum iteration(generation): ");
        int maxGen = scan.nextInt();

        System.out.print("Enter lower bound of decision parameter: ");
        double lB = scan.nextDouble();

        System.out.print("Enter upper bound of decision parameter: ");
        double uB = scan.nextDouble();

        System.out.print("Enter scaling factor: ");
        double f = scan.nextDouble();

        System.out.print("Enter crossover rate: ");
        double cR = scan.nextDouble();

        System.out.print("Enter run number: ");
        int runNum = scan.nextInt();

        System.out.print("Enter dimension of problem: ");
        int dim = scan.nextInt();

        double[] runBests = new double[runNum];

        for (int i = 0; i < runNum; i++) {

            Individual[] pop = new Individual[nP];
            Individual[] popNext = new Individual[nP];

            for (int k = 0; k < nP; k++) {

                Individual i1 = new Individual(dim);

                for (int j = 0; j < dim; j++) {
                    i1.solString[j] = lB + rn.nextDouble() * (uB - lB);
                }

                i1.calculateObjVal();
                popNext[k] = i1.clone();
            }

            for (int t = 0; t < maxGen; t++) {

                for (int k = 0; k < nP; k++) {
                    pop[k] = popNext[k].clone();
                }

                for (int curr = 0; curr < nP; curr++) {

                    Individual uInd = pop[curr].clone(); //uInd: candidate individual(solution)
                    uInd.mutationAndRecombination(pop,curr,rn,lB,uB,cR,f);
                    uInd.calculateObjVal();
                    if (uInd.objVal <= pop[curr].objVal)
                        popNext[curr] = uInd.clone();
                    else
                        popNext[curr] = pop[curr].clone();
                }
            }
            int index = findBest(popNext);
            runBests[i] = popNext[index].objVal;
        }

        double totalObjVals=0;
        System.out.println("\n\n\tCALCULATING...");
        System.out.println("\nRun Bests\n");
        for (Double runb: runBests){
            System.out.println(runb);
            totalObjVals+=runb;
        }

        System.out.println("\nAverage: "+(totalObjVals/runBests.length));
    }
}


