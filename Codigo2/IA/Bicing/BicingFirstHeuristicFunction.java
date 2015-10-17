package IA.Bicing;

import aima.search.framework.HeuristicFunction;

public class BicingFirstHeuristicFunction implements HeuristicFunction{
  public double getHeuristicValue(Object state) {
    BicingState situation = (BicingState) state;
//     System.out.println(situation.tripList.size());
//     for(int i = 0; i < situation.tripList.size(); ++i){
//       System.out.println(situation.tripList.get(i).identifier());
//     }
    double dif = 0;
    for(int i = 0; i < situation.Sd.size(); ++i){
      StationData s = situation.Sd.get(i);
      dif += Math.max(0,s.Demand-s.NumBicNext);
    }
//     System.out.println(dif);
    return dif;
  }
}