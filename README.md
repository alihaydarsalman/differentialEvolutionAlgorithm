# Differential Evolution Algorithm

### This project is an algorithm that performs global optimization by taking the following control parameters from the user.
control parameters:

      number of population: NP
      maximum generation(max iteration): maxGen
      decision parameter lower bound: lB
      decision parameter upper bound: uB
      scaling factor: f
      crossover rate: cR
      Run number: runNum
      dimension of problem (decision variable size): dim

<p> Since it is an algorithm that can show approximate but different results in each run,
      the average or standard deviation of the outputs should be taken to test its accuracy.
      Therefore, the runNum variable is taken from the user how many times the algorithm should be run in order to test its robustness.

      Note: Optimum objective function value is zero.
</p>
