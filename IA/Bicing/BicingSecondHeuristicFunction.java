package IA.Bicing;

import aima.search.framework.HeuristicFunction;

public class BicingSecondHeuristicFunction implements HeuristicFunction{  //Devuelve -(beneficios*100)

  public double getHeuristicValue(Object state) {
    BicingState situation = (BicingState) state;
    int profit = 0;
    for(int i = 0; i < situation.tripList.size(); ++i){
      Trip t = situation.tripList.get(i);
      profit += 1000*(t.nFirstBic + t.nSecondBic);
      profit -= situation.dist(t.origin,t.firstDest)*((t.nFirstBic+t.nSecondBic+9)/10);
      if(t.secondDest != -1){
	      profit -= situation.dist(t.firstDest,t.secondDest)*((t.nSecondBic+9)/10);
      }
    }
    return -profit;
  }
}