package IA.Bicing;

public class StationData{
  public int NumBicNow;
  public int Difer;
  
  public StationData(int nbnow,int nbnext,int dem){
    NumBicNow = nbnow;
    Difer = nbnext - dem;
  }
  
  public StationData(StationData other){
    NumBicNow = other.NumBicNow;
	Difer = other.Difer;
  }
  
  public void takeBic(int num){
    NumBicNow -= num;
    Difer -= num;
  }
  
  public void leaveBic(int num){
    Difer += num;
  }
};