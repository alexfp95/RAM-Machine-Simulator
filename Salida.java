import java.util.LinkedList;
import java.io.*;

class Salida {

  private LinkedList<Integer> out_;
  private String fich_;

  Salida (String file) {
    out_ = new LinkedList<Integer>();
    fich_ = file;
  }

  public void write (Integer num) {
    out_.add(num);
  }

  public void show () {
    String contenido = new String();
    for(int k=0; k<out_.size(); k++)
      contenido += out_.get(k) + " ";
    System.out.println(contenido);
  }

  public void export () {
    try {
      FileWriter fichero = null;
      fichero = new FileWriter(fich_);
      for(int k=0; k<out_.size(); k++) {
        fichero.write(out_.get(k) + " ");
      }
      fichero.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("No se ha encontrado el fichero a exportar.");
    }
    catch (IOException e) {
      System.out.println("Error en la E/S del fichero de exportación.");
    }
  }
}
