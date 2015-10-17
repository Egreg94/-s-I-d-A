package IA.Bicing;

public class Trip{
  public int origin;
  public int nFirstBic;
  public int firstDest;
  public int nSecondBic;
  public int secondDest;
  
  public Trip(int o,int n1,int fd){
    origin = o;
    nFirstBic = n1;
    firstDest = fd;
    nSecondBic = 0;
    secondDest = -1;
  }
  
  public Trip(int o,int n1,int fd,int n2,int sd){
    origin = o;
    nFirstBic = n1;
    firstDest = fd;
    nSecondBic = n2;
    secondDest = sd;
  }
  
  public Trip(Trip other){
    origin = other.origin;
    nFirstBic = other.nFirstBic;
    firstDest = other.firstDest;
    nSecondBic = other.nSecondBic;
    secondDest = other.secondDest;
  }
  
  public String identifier(){
    if(origin == -1) return "NO MORE TRANSACTIONS";
    if(secondDest == -1) return origin + " -> " + firstDest + "," + nFirstBic;
    else return origin + " -> " + firstDest + "," + nFirstBic + " -> " + secondDest + "," + nSecondBic;
  }
};