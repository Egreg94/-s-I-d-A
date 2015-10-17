package IA.Bicing;

import aima.search.framework.GoalTest;

public class BicingGoalTest implements GoalTest{
  
  public boolean isGoalState(Object state) {
    return false;
  }
//   public boolean isGoalState(Object state) {
//     BicingState situation = (BicingState) state;
//     return situation.cars == 0;
//   }
};