package IA.Bicing;

public class StationData{
  public int NumBicNow;
  public int NumBicNext;
//   public int Demand;
  
  public StationData(int nbnow,int nbnext,int dem){
    NumBicNow = nbnow;
    NumBicNext = nbnext;
//     Demand = dem;
  }
  
  public StationData(StationData other){
    NumBicNow = other.NumBicNow;
    NumBicNext = other.NumBicNext;
//     Demand = other.Demand;
  }
  
//   public int Difer(){
//     return NumBicNext-Demand;
//   }
//   
//   public int Available(){
//     if(this.Difer() <= 0) return 0;
//     else return Math.min(this.Difer(),NumBicNow);
//   }
  
  public void takeBic(int num){
    NumBicNow -= num;
    NumBicNext -= num;
  }
  
  public void leaveBic(int num){
    NumBicNext += num;
  }
  //PREGUNTAR POR GETS Y SETS
};