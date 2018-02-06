package proj2.document;

/*
* Dummy class to hold basic state info
* Like a string name and x/y coords
*/

public class State {

  String name;
  int x;
  int y;

  public State(String n, int xloc, int yloc) {
    name = this.n;
    x = xloc;
    y = yloc;
  }

  /*
  * Name getter
  */
  public String getName() {
    return name;
  }

  /*
  * X location getter
  */
  public int getX() {
    return x;
  }

  /*
  * Y location getter
  */
  public int getY() {
    return y;
  }
}
