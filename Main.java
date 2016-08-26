import java.util.ArrayList;
import java.util.List;
import java.io.*;

class Main {
  public static void main (String args[]) throws IOException {

    Procesador procesador = new Procesador (args[0], args[1], args[2]);
    BufferedReader br = new BufferedReader ( new InputStreamReader (System.in) );
    Boolean traza = false;

    System.out.print("\n¿Quieres ver el programa cargado? [S/N]: ");
    String user = br.readLine(); user = user.toUpperCase();

    if(user.equals("S"))
      procesador.showProgram();

    System.out.print("\n¿Quieres ver la cinta de entrada? [S/N]: ");
    user = br.readLine(); user = user.toUpperCase();

    if(user.equals("S"))
      procesador.showInput();

    System.out.print("\n¿Quieres ver una traza de la ejecucion? [S/N]: ");
    user = br.readLine(); user = user.toUpperCase();

    if(user.equals("S"))
      traza = true;

    procesador.run(traza);

    System.out.print("\n¿Quieres ver la cinta de salida? [S/N]: ");
    user = br.readLine(); user = user.toUpperCase();

    if(user.equals("S"))
      procesador.showOutput();
  }
}
