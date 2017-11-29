package gridDeveloper;

public class Coordinate {

  private final int x;
  private final int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Calculates the Manhattan distance between two coordinates
   *
   * @param coordinate the coordinate to calculate the distance from
   * @return the Manhattan distance
   */
  public Integer distanceTo(Coordinate coordinate) {
    return Math.abs(this.x - coordinate.x) + Math.abs(this.y - coordinate.y);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Coordinate)) {
      return false;
    }

    Coordinate other = (Coordinate) o;
    return this.x == other.x && this.y == other.y;
  }

  @Override
  public int hashCode() {
    return ((int) Math.pow(2, x)) + (2 * y + 1) - 1;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
