package IA.Bicing;

import aima.search.framework.HeuristicFunction;

public class BicingFirstHeuristicFunction implements HeuristicFunction{
  //First heuristic
  //Only takes into account the number of bikes moved
  public double getHeuristicValue(Object state) {
    BicingState situation = (BicingState) state;
    double dif = 0;
    for(int i = 0; i < situation.tripList.size(); ++i){
      dif += situation.tripList.get(i).nFirstBic + situation.tripList.get(i).nSecondBic;
    }
    return -dif;
  }
}