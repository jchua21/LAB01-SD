public class CubbyHole {
  private int contents;
  private boolean available = false;

  public synchronized int get() {
    while (available == false) {
      try {
        wait();
      } catch (InterruptedException e) {
      }
    }
    available = false;
    notifyAll();
    return contents;
  }

  public synchronized void put(int value) {
    // El productor adquiere el monitor
    while (available == true) {
      try {
        wait();
      } catch (InterruptedException e) {
      }
    }
    contents = value;
    available = true;
    notifyAll();// El productor libera el monitor
  }
}
