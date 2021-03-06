package IA.Bicing;

import java.util.Random;
import java.util.List;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;

import IA.Bicing.Estaciones;

public class Bicing{
  
  static BicingState inic;
  
  public static void main(String[] args){
    //STANDAR INITIALIZATION
    int n = 1;
    int nEst = n*25;
    int nBic = n*1250;
    int furg = n*5;
    int rush = 0;
    int seed = 1234;
    
    //RANDOM INITIALIZATION
//     Random rand = new Random();
//     int furg = rand.nextInt(8)+10;
//     int nEst = furg*5;
//     int nBic = nEst*50;
//     int rush = 0;
//     int seed = rand.nextInt();
    
    Estaciones b = new Estaciones(nEst, nBic, rush, seed);
    inic = new BicingState(b,furg);
    //Here call desired function depending on which algorithm you want
    criteria1();
  }
  
  // Hillclimbing, first heuristic
  public static void criteria1(){
    try{
      Problem problem = new Problem(inic,
			    new BicingSuccessorFunction(),
			    new BicingGoalTest(),
			    new BicingFirstHeuristicFunction());
      Search search =  new HillClimbingSearch();
      SearchAgent agent = new SearchAgent(problem,search);
      printActions(agent.getActions());
    } catch(Exception e){
      e.printStackTrace();
    }
  }
  
  // Hillclimbing, second heuristic
  public static void criteria2(){
    try{
      Problem problem = new Problem(inic,
			    new BicingSuccessorFunction(),
			    new BicingGoalTest(),
			    new BicingSecondHeuristicFunction());
      Search search =  new HillClimbingSearch();
      SearchAgent agent = new SearchAgent(problem,search);
      printActions(agent.getActions());
    } catch(Exception e){
      e.printStackTrace();
    }
  }
  
  // Simulated annealing, first heuristic
  public static void anhealing1(){
    try{
      Problem problem = new Problem(inic,
			    new BicingSuccessorFunction(),
			    new BicingGoalTest(),
			    new BicingFirstHeuristicFunction());
      Search search =  new SimulatedAnnealingSearch(20000,100,20,0.01);
      SearchAgent agent = new SearchAgent(problem,search);
      printState(agent.getActions());
    } catch(Exception e){
      e.printStackTrace();
    }
  }
  
  // Simulated annealing, second heuristic
  public static void anhealing2(){
    try{
      Problem problem = new Problem(inic,
			    new BicingSuccessorFunction(),
			    new BicingGoalTest(),
			    new BicingSecondHeuristicFunction());
      Search search =  new SimulatedAnnealingSearch(20000,100,20,0.01);
      SearchAgent agent = new SearchAgent(problem,search);
      printState(agent.getActions());
    } catch(Exception e){
      e.printStackTrace();
    }
  }
  
  private static void printActions(List actions) {
    System.out.println("actions");
    for (int i = 0; i < actions.size(); i++) {
      String action = (String) actions.get(i);
      System.out.println(action);
    }
  }
  
  private static void printState(List actions) {
    System.out.println("Final State");
    BicingState b = (BicingState) actions.get(0);
    for(int i = 0; i < b.tripList.size(); ++i){
      System.out.println(b.tripList.get(i).identifier());
    }
  }
};