import java.util.ArrayList;
import java.io.*;

class Programa {

  private ArrayList<ArrayList<String>> program_;

  Programa (String file) {
    program_ = new ArrayList<ArrayList<String>>();
    String linea = new String ();
    String [] token;

    try {
      BufferedReader reader = new BufferedReader ( new FileReader(file) );

      while(reader.ready()){
        linea = reader.readLine();

        if(!linea.matches(";(.*)")) {
          ArrayList<String> codigo = new ArrayList<String> ();
          token = linea.split("\\s+");
          /*for (int k = 1; k < token.length; k++)
            codigo.add(token[k]);*/
          for(int k=0; k< token.length; k++) {
            if(token[k].length() > 0)
              codigo.add(token[k]);
          }
          program_.add(codigo);
        }
      }
    }

    catch (FileNotFoundException e) {
      System.out.println("No se ha encontrado el programa a cargar.");
    }
    catch (IOException e) {
      System.out.println("Error en la E/S del fichero del programa a cargar.");
    }
  }

  public ArrayList<String> get (Integer index) {
    return program_.get(index);
  }

  public Integer size () {
    return program_.size();
  }

  public void show () {
    for(int k=0; k<size(); k++)
      System.out.println(get(k));
  }
}
