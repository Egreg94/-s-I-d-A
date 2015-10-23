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
					 if(s2.Difer() < 0){																										// for each s1, s2
					 	int maxBic = Math.min(30,Math.min(s1.Available(),-s2.Difer()));					//single stop
					 	Trip oper = new Trip(i,maxBic,j);
					  BicingState newState = new BicingState(situation,oper);
					  successors.add(new Successor(oper.identifier(),newState));
					 	int nearest = (maxBic/10)*10;																							//If using first Heuristic, comment from this line...
					 	if (nearest > 0 && nearest != maxBic){
					 		oper = new Trip(i,nearest,j);
					    newState = new BicingState(situation,oper);
					    successors.add(new Successor(oper.identifier(),newState));							//Up to this line
					 	}

					  for(int k = 0; k < situation.Sd.size(); ++k){														//two stops
					    if(k == j) continue;
					    StationData s3 = situation.Sd.get(k);
					    if(s3.Difer() < 0){
								for(int bicis2 = 10; bicis2 <= 30; bicis2 += 10){
						  		if(bicis2 > s1.Available() || bicis2 > -s3.Difer()){
						   			bicis2 = Math.min(s1.Available(),-s3.Difer());
						  		}
						  		for(int bicis = 10-bicis2; bicis+bicis2 <= 30; bicis += 10){
						    		if(bicis > s1.Available()-bicis2 || bicis > -s2.Difer()){
						      		bicis = Math.min(s1.Available()-bicis2,-s2.Difer());
						      		if(bicis <= 0) break;
						    		}
						    		if(bicis <= 0) continue;
						    		oper = new Trip(i,bicis,j,bicis2,k);
						    		newState = new BicingState(situation,oper);
						    		successors.add(new Successor(oper.identifier(),newState));
						    		if(bicis == Math.min(s1.Available()-bicis2,-s2.Difer())) break;
						 			}
						  		if(bicis2 == Math.min(s1.Available(),-s3.Difer())) break;
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