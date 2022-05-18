import java.util.Random;

public class Individual implements Cloneable{

    //This class is written to create the individual(solution) population.

    public double[] solString; //solution string
    public double objVal=0; //objective function value is necessary for choosing better individuals to next generation.
                            //if obj val lower than other function value, it's probability of choose higher.

    public Individual(int dim){
        solString = new double[dim];
    }

    @Override
    public Individual clone() {

        Individual i1 = new Individual(this.solString.length);
        i1.objVal=this.objVal;

        for (int i=0;i<this.solString.length;i++){
            i1.solString[i] = this.solString[i];
        }
        return i1;
    }

    public void calculateObjVal(){

        double total=0;
        for (int i=0;i<this.solString.length;i++){
            total+= Math.pow(this.solString[i],2);
        }
        this.objVal= total;
    }

    public void mutationAndRecombination(Individual[] pop, int current, Random random, double lB, double uB, double cR, double f){

        int random1 = random.nextInt(pop.length);
        while (random1==current){
            random1=random.nextInt(pop.length);
        }

        int random2 = random.nextInt(pop.length);
        while (random2==current ||random2==random1){
            random2=random.nextInt(pop.length);
        }

        int random3 = random.nextInt(pop.length);
        while (random3==current || random3==random1 || random3==random2){
            random3=random.nextInt(pop.length);
        }

        int indJ= random.nextInt(this.solString.length);
        for (int i=0;i< this.solString.length;i++){

            if (random.nextDouble()<cR || indJ==i){

                this.solString[i] = pop[random3].solString[i] + f *(pop[random1].solString[i] - pop[random2].solString[i]);
                if (this.solString[i]<lB)
                    this.solString[i] = lB;
                else if (this.solString[i]>uB)
                    this.solString[i]=uB;
            }
        }
    }
}
