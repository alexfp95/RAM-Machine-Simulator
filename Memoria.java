import java.util.ArrayList;

class Memoria {

  private ArrayList<Integer> mem_;

  Memoria () {
    mem_ = new ArrayList<Integer>();
    mem_.add(0);
  }

  public void escribir (Integer pos, Integer num) {
    Integer size = mem_.size();
    if (size < pos+1) {
      size += 1;
      while (size < pos+1) {
        mem_.add(0);
        size += 1;
      }
      mem_.add(num);
    }
    else
      mem_.set(pos, num);
  }

  public Integer leer (Integer pos) {
    return mem_.get(pos);
  }

  public void show () {
    for(int k=0; k<mem_.size(); k++)
      System.out.println(leer(k));
  }
}
