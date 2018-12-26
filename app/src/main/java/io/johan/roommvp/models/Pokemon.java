package io.johan.roommvp.models;

import io.johan.roommvp.data.entity.PokeList;
import java.util.List;

public class Pokemon {

   private int count;
   private String next;
   private String previous;
   private List<PokeList> results;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getNext() {
    return next;
  }

  public void setNext(String next) {
    this.next = next;
  }

  public String getPrevious() {
    return previous;
  }

  public void setPrevious(String previous) {
    this.previous = previous;
  }

  public List<PokeList> getResults() {
    return results;
  }

  public void setResults(List<PokeList> results) {
    this.results = results;
  }
}
