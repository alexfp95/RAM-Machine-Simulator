import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

class Procesador {

  private Entrada entrada_;
  private Salida salida_;
  private Memoria memoria_;
  private Programa programa_;
  private Integer contador_;

  Procesador (String programa, String entrada, String sa) {
    entrada_ = new Entrada(entrada);
    salida_ = new Salida(sa);
    memoria_ = new Memoria();
    programa_ = new Programa(programa);
    contador_ = 0;
  }

  public void run (Boolean traza) {
    Boolean running = true;

    while(running) {
      ArrayList<String> linea = programa_.get(contador_);
      List<String> instruccion = linea;

      if(linea.size() > 1) {
        if(linea.get(1).equals(":"))
          instruccion = linea.subList(2,linea.size());
      }

      switch(instruccion.get(0).toUpperCase()) {
        case "READ" : running = read(instruccion, traza); contador_++; break;
        case "WRITE" : running = write(instruccion, traza); contador_++; break;
        case "LOAD" : running = load(instruccion, traza); contador_++; break;
        case "STORE" : running = store(instruccion, traza); contador_++; break;
        case "ADD" : running = add(instruccion, traza); contador_++; break;
        case "SUB" : running = sub(instruccion, traza); contador_++; break;
        case "MUL" : running = mul(instruccion, traza); contador_++; break;
        case "DIV" : running = div(instruccion, traza); contador_++; break;
        case "HALT" : running = false; break;
        case "JUMP" : running = jump(instruccion, traza, false); break;
        case "JZERO" : running = jzero(instruccion, traza); break;
        case "JGTZ" : running = jgtz(instruccion, traza); break;
        default: System.out.println("Se ha encontrado una funcion desconocida."); running = false; break;
      }
    }
    salida_.export();
  }

  public Boolean read (List<String> instruccion, Boolean traza) {
    Integer num = entrada_.read();
    Integer pos;
    if(instruccion.get(1).length() == 1)
      pos = new Integer (instruccion.get(1));
    else {
      if(instruccion.get(1).charAt(0) == '*')
        pos = memoria_.leer(new Integer (instruccion.get(1).substring(1)));
      else {
        System.out.println("Operacion de READ invalida.");
        return false;
      }
    }
    if(traza)
      System.out.println("READ: " + num + " se escribe en r" + pos + ".");

    memoria_.escribir(pos, num);
    return true;
  }

  public Boolean write (List<String> instruccion, Boolean traza) {
    Integer num, pos;
    if(instruccion.get(1).length() == 1)
      num = memoria_.leer(new Integer (instruccion.get(1)));
    else {
      if(instruccion.get(1).charAt(0) == '*') {
        pos = memoria_.leer(new Integer (instruccion.get(1).substring(1)));
        num = memoria_.leer(pos);
      }
      else if(instruccion.get(1).charAt(0) == '=')
        num = new Integer (instruccion.get(1).substring(1));
      else {
        System.out.println("Operacion de WRITE invalida.");
        return false;
      }
    }
    if(traza)
      System.out.println("WRITE: " + num + " se escribe en la cinta de salida.");

    salida_.write(num);
    return true;
  }

  public Boolean load (List<String> instruccion, Boolean traza) {
    Integer num, pos;
    if(instruccion.get(1).length() == 1)
      num = memoria_.leer(new Integer (instruccion.get(1)));
    else {
      if(instruccion.get(1).charAt(0) == '*') {
        pos = memoria_.leer(new Integer (instruccion.get(1).substring(1)));
        num = memoria_.leer(pos);
      }
      else if(instruccion.get(1).charAt(0) == '=')
        num = new Integer (instruccion.get(1).substring(1));
      else {
        System.out.println("Operacion de LOAD invalida.");
        return false;
      }
    }
    if(traza)
      System.out.println("LOAD: " + num + " se carga en el AC (r0).");

    memoria_.escribir(0, num);
    return true;
  }

  public Boolean store (List<String> instruccion, Boolean traza) {
    Integer num = memoria_.leer(0);
    Integer pos;
    if(instruccion.get(1).length() == 1)
      pos = new Integer (instruccion.get(1));
    else {
      if(instruccion.get(1).charAt(0) == '*')
        pos = memoria_.leer(new Integer (instruccion.get(1).substring(1)));
      else {
        System.out.println("Operacion de STORE invalida.");
        return false;
      }
    }
    if(traza)
      System.out.println("STORE: " + num + " se guarda en r" + pos + ".");

    memoria_.escribir(pos, num);
    return true;
  }

  public Boolean add (List<String> instruccion, Boolean traza) {
    Integer num, pos;
    if(instruccion.get(1).length() == 1)
      num = memoria_.leer(new Integer (instruccion.get(1)));
    else {
      if(instruccion.get(1).charAt(0) == '*') {
        pos = memoria_.leer(new Integer (instruccion.get(1).substring(1)));
        num = memoria_.leer(pos);
      }
      else if(instruccion.get(1).charAt(0) == '=')
        num = new Integer (instruccion.get(1).substring(1));
      else {
        System.out.println("Operacion de ADD invalida.");
        return false;
      }
    }
    Integer aux = memoria_.leer(0);
    aux = aux + num;

    if(traza)
      System.out.println("ADD: " + num + " se le suma al AC (r0).");

    memoria_.escribir(0, aux);
    return true;
  }

  public Boolean sub (List<String> instruccion, Boolean traza) {
    Integer num, pos;
    if(instruccion.get(1).length() == 1)
      num = memoria_.leer(new Integer (instruccion.get(1)));
    else {
      if(instruccion.get(1).charAt(0) == '*') {
        pos = memoria_.leer(new Integer (instruccion.get(1).substring(1)));
        num = memoria_.leer(pos);
      }
      else if(instruccion.get(1).charAt(0) == '=')
        num = new Integer (instruccion.get(1).substring(1));
      else {
        System.out.println("Operacion de SUB invalida.");
        return false;
      }
    }
    Integer aux = memoria_.leer(0);
    aux = aux - num;

    if(traza)
      System.out.println("SUB: " + num + " se le resta al AC (r0).");

    memoria_.escribir(0, aux);
    return true;
  }

  public Boolean mul (List<String> instruccion, Boolean traza) {
    Integer num, pos;
    if(instruccion.get(1).length() == 1)
      num = memoria_.leer(new Integer (instruccion.get(1)));
    else {
      if(instruccion.get(1).charAt(0) == '*') {
        pos = memoria_.leer(new Integer (instruccion.get(1).substring(1)));
        num = memoria_.leer(pos);
      }
      else if(instruccion.get(1).charAt(0) == '=')
        num = new Integer (instruccion.get(1).substring(1));
        //num = Integer.parseInt("" + instruccion.get(1).charAt(1));
      else {
        System.out.println("Operacion de MUL invalida.");
        return false;
      }
    }
    Integer aux = memoria_.leer(0);
    aux = aux * num;

    if(traza)
      System.out.println("MUL: " + num + " se le multiplica al AC (r0).");

    memoria_.escribir(0, aux);
    return true;
  }

  public Boolean div (List<String> instruccion, Boolean traza) {
    Integer num, pos;
    if(instruccion.get(1).length() == 1)
      num = memoria_.leer(new Integer (instruccion.get(1)));
    else {
      if(instruccion.get(1).charAt(0) == '*') {
        pos = memoria_.leer(new Integer (instruccion.get(1).substring(1)));
        num = memoria_.leer(pos);
      }
      else if(instruccion.get(1).charAt(0) == '=')
        num = new Integer (instruccion.get(1).substring(1));
      else {
        System.out.println("Operacion de DIV invalida.");
        return false;
      }
    }
    Integer aux = memoria_.leer(0);
    Double d = Math.floor(aux / num);
    aux = d.intValue();

    if(traza)
      System.out.println("DIV: " + num + " se le divide al AC (r0).");

    memoria_.escribir(0, aux);
    return true;
  }

  public Boolean jump (List<String> instruccion, Boolean traza, Boolean other) {
    for(int k = 0; k<programa_.size(); k++) {
      ArrayList<String> linea = programa_.get(k);
      if(linea.size() > 1) {
        if(linea.get(1).equals(":"))
          if(linea.get(0).equals(instruccion.get(1))){
            contador_ = k;

            if(traza && !other)
              System.out.println("JUMP: Se salta a " + instruccion.get(1) + ".");

            return true;
          }
      }
    }
    System.out.println("Etiqueta inexistente en un salto.");
    return false;
  }

  public Boolean jzero (List<String> instruccion, Boolean traza) {
    Boolean aux = true;
    if(memoria_.leer(0) == 0) {
      aux = jump(instruccion, traza, true);
      if(traza)
        System.out.println("ZERO: Se salta a " + instruccion.get(1) + ".");
    }
    else {
      contador_++;
      if(traza)
        System.out.println("ZERO: No hay salto.");
    }
    return aux;
  }

  public Boolean jgtz (List<String> instruccion, Boolean traza) {
    Boolean aux = true;
    if(memoria_.leer(0) > 0) {
      aux = jump(instruccion, traza, true);
      if(traza)
        System.out.println("JGTZ: Se salta a " + instruccion.get(1) + ".");
    }
    else {
      contador_++;
      if(traza)
        System.out.println("JGTZ: No hay salto.");
    }
    return aux;
  }

  public void showProgram () {
    programa_.show();
  }

  public void showInput () {
    entrada_.show();
  }

  public void showOutput () {
    salida_.show();
  }

}
