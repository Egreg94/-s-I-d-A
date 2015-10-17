package IA.Bicing;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class BicingSuccessorFunction implements SuccessorFunction{

  public List getSuccessors(Object state) {
    BicingState situation = (BicingState) state;
    List successors = new ArrayList();
    if(situation.cars == 0) return successors;
    for(int i = 0; i < situation.Sd.size(); ++i){
      StationData s1 = situation.Sd.get(i);
      if(s1.Available() > 0){
	for(int j = 0; j < situation.Sd.size(); ++j){
	  StationData s2 = situation.Sd.get(j);
	  if(s2.Difer() < 0){
	    for(int bicis = 1; bicis <= Math.min(30,Math.min(s1.Available(),-s2.Difer())); ++bicis){
	      Trip oper = new Trip(i,bicis,j);
	      BicingFirstHeuristicFunction h1 = new BicingFirstHeuristicFunction();
	      BicingState newState = new BicingState(situation,oper);
	      successors.add(new Successor(oper.identifier(),newState));
	    }
	    for(int k = 0; k < situation.Sd.size(); ++k){
	      if(k == j) continue;
	      StationData s3 = situation.Sd.get(k);
	      if(s3.Difer() < 0){
		for(int bicis = 1; bicis <= Math.min(30,Math.min(s1.Available(),-s2.Difer()-s3.Difer())); ++bicis){
		  for(int bicis2 = 1; bicis2 < Math.min(bicis,-s2.Difer()+1); ++bicis2){
		    Trip oper = new Trip(i,bicis2,j,bicis-bicis2,k);
		    BicingState newState = new BicingState(situation,oper);
		    successors.add(new Successor(oper.identifier(),newState));
		  }
		}
	      }
	    }
	  }
	}
      }
    }
//     Trip oper = new Trip(-1,0,0);
//     BicingState newState = new BicingState(situation,oper);
//     successors.add(new Successor(oper.identifier(),newState));
    return successors;
  }
};