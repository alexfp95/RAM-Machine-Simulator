import java.util.LinkedList;
import java.io.*;

class Entrada {

  private LinkedList<Integer> in_;

  Entrada (String file) {
    in_ = new LinkedList<Integer>();
    String datos = new String();

    try {
      BufferedReader reader = new BufferedReader( new FileReader(file) );
      while (reader.ready())
        datos = reader.readLine();
    }
    catch (FileNotFoundException e) {
      System.out.println("No se ha encontrado el fichero de la entrada.");
    }
    catch (IOException e) {
      System.out.println("Error en la E/S en el fichero de la entrada.");
    }

    String [] valores = datos.split("\\s");

    for(int k=0; k<valores.length; k++)
      in_.add(new Integer(valores[k]));
  }

  public Integer read () {
    return in_.removeFirst();
  }

  public void show () {
    String contenido = new String();
    for(int k=0; k<in_.size(); k++)
      contenido += in_.get(k) + " ";
    System.out.println(contenido);
  }

}
