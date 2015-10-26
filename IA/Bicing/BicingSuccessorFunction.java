package IA.Bicing;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class BicingSuccessorFunction implements SuccessorFunction{
  public List getSuccessors(Object state) {
    BicingState situation = (BicingState) state;
    List successors = new ArrayList();
    
    
    for(int i = 0; i < situation.tripList.size(); ++i){
      String id = "take " + situation.tripList.get(i).identifier();
      BicingState newState = new BicingState(situation,i);
      successors.add(new Successor(id,newState));
    }
    
    if(situation.cars == 0) return successors;

    for(int i = 0; i < situation.Sd.size(); ++i){
      StationData s1 = situation.Sd.get(i);
      if(situation.Available(i) > 0){
	for(int j = 0; j < situation.Sd.size(); ++j){
	  if(situation.Difer(j) < 0){
	    {
	      int maxBic = Math.min(30,Math.min(situation.Available(i),-situation.Difer(j))); //single stop
	      Trip oper = new Trip(i,maxBic,j);
	      BicingState newState = new BicingState(situation,oper);
	      successors.add(new Successor(oper.identifier(),newState));
	      int nearest = (maxBic/10)*10; //If using first Heuristic, comment from this line...
	      if (nearest > 0 && nearest != maxBic){
		oper = new Trip(i,nearest,j);
		newState = new BicingState(situation,oper);
		successors.add(new Successor(oper.identifier(),newState)); //Up to this line
	      }
	    }
            for(int k = 0; k < situation.Sd.size(); ++k){ //two stops
              if(k == j) continue;
	      StationData s3 = situation.Sd.get(k);
	      if(situation.Difer(k) < 0){
		for(int bicis2 = 10; bicis2 <= 30; bicis2 += 10){
		  if(bicis2 > situation.Available(i) || bicis2 > -situation.Difer(k)){
		    bicis2 = Math.min(situation.Available(i),-situation.Difer(k));
		  }
		  for(int bicis = 10-bicis2; bicis+bicis2 <= 30; bicis += 10){
		    if(bicis > situation.Available(i)-bicis2 || bicis > -situation.Difer(j)){
		      bicis = Math.min(situation.Available(i)-bicis2,-situation.Difer(j));
		      if(bicis <= 0) break;
		    }
		    if(bicis <= 0) continue;
		    Trip oper = new Trip(i,bicis,j,bicis2,k);
		    BicingState newState = new BicingState(situation,oper);
		    successors.add(new Successor(oper.identifier(),newState));
		    if(bicis == Math.min(situation.Available(i)-bicis2,-situation.Difer(j))) break;
		  }
		  if(bicis2 == Math.min(situation.Available(i),-situation.Difer(k))) break;
                }
              }
            }
          }
        }
      }
    }
    
    return successors;
  }
};