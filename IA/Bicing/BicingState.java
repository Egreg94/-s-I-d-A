package IA.Bicing;

import java.util.Random;
import java.util.ArrayList;


public class BicingState{
  public int cars;
  public ArrayList<StationData> Sd;
  public ArrayList<Trip> tripList;
  
  public static ArrayList<Integer> Coordx;
  public static ArrayList<Integer> Coordy;
  
  public int Difer(int i){
    return Sd.get(i).Difer;
  }
  
  public int Available(int i){
    if(this.Difer(i) <= 0) return 0;
    else return Math.min(this.Difer(i),Sd.get(i).NumBicNow);
  }
  
  public int dist(int i,int j){
    return Math.abs(Coordx.get(i)-Coordx.get(j))+Math.abs(Coordy.get(i)-Coordy.get(j));
  }
  
  //initializes the state from the information taken from b, with ncars
  public BicingState(Estaciones b, int ncars) {
    cars = ncars;
    Sd = new ArrayList<StationData>();
    tripList = new ArrayList<Trip>();
    Coordx = new ArrayList<Integer>();
    Coordy = new ArrayList<Integer>();
    for(int i = 0; i < b.size(); ++i){
      if (b.get(i).getNumBicicletasNext() != b.get(i).getDemanda()) {
        StationData t = new StationData(b.get(i).getNumBicicletasNoUsadas(),b.get(i).getNumBicicletasNext(),b.get(i).getDemanda());
        Coordx.add(b.get(i).getCoordX());
        Coordy.add(b.get(i).getCoordY());
        Sd.add(t);
      }  
    }
  }
  
  //Copy an state and adds the trip 'op' to it
  public BicingState(BicingState inic, Trip op){
    cars = inic.cars;
    Sd = new ArrayList<StationData>();
    for(int i = 0; i < inic.Sd.size(); ++i){
      Sd.add(new StationData(inic.Sd.get(i)));
    }
    tripList = new ArrayList<Trip>();
    for(int i = 0; i < inic.tripList.size(); ++i){
      tripList.add(new Trip(inic.tripList.get(i)));
    }

    --cars;
    tripList.add(op);
    Sd.get(op.origin).takeBic(op.nFirstBic+op.nSecondBic);
    Sd.get(op.firstDest).leaveBic(op.nFirstBic);
    if(op.secondDest != -1) Sd.get(op.secondDest).leaveBic(op.nSecondBic);
  }
  
  //Copy an state erasing the trip indexed by tripout
  public BicingState(BicingState inic, int tripout){
    cars = inic.cars;
    Sd = new ArrayList<StationData>();
    for(int i = 0; i < inic.Sd.size(); ++i){
      Sd.add(new StationData(inic.Sd.get(i)));
    }
    tripList = new ArrayList<Trip>();
    for(int i = 0; i < inic.tripList.size(); ++i){
      if(i == tripout) continue;
      tripList.add(new Trip(inic.tripList.get(i)));
    }

    Trip op = inic.tripList.get(tripout);
    ++cars;
    Sd.get(op.origin).takeBic(-op.nFirstBic-op.nSecondBic);
    Sd.get(op.firstDest).leaveBic(-op.nFirstBic);
    if(op.secondDest != -1) Sd.get(op.secondDest).leaveBic(-op.nSecondBic);
  }
};