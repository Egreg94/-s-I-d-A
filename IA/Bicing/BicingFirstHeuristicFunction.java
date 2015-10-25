package IA.Bicing;

import aima.search.framework.HeuristicFunction;

public class BicingFirstHeuristicFunction implements HeuristicFunction{
  public double getHeuristicValue(Object state) {
    BicingState situation = (BicingState) state;
    double dif = 0;
    for(int i = 0; i < situation.Sd.size(); ++i){
      StationData s = situation.Sd.get(i);
      dif += Math.max(0,situation.Demand.get(i)-s.NumBicNext);
    }
    return dif;
  }
}