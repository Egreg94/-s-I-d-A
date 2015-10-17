package IA.Bicing;

import java.util.Random;
import java.util.List;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.informed.HillClimbingSearch;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;

import IA.Bicing.Estaciones;

import aima.search.informed.GreedyBestFirstSearch;

public class Bicing{
  
  static BicingState inic;
  
  public static void main(String[] args){
    int nEst = 25;
    int nBic = 1250;
    int furg = 5;
    Boolean rush = false;
    Estaciones b = new Estaciones(25, 1250, 0, 1234); //CAMBIAR A RANDOM... El 3cer argumento es rush
    inic = new BicingState(b,5);
    criteria1();
  }
  
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
  

  private static void printActions(List actions) {
    System.out.println("actions");
    for (int i = 0; i < actions.size(); i++) {
      String action = (String) actions.get(i);
      System.out.println(action);
    }
  }
};