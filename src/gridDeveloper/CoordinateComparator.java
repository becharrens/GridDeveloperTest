package gridDeveloper;

import java.util.Comparator;

public class CoordinateComparator implements Comparator<Coordinate> {

  // Coordinate used to determine the ordering of the events on the queue,
  // based on the distance between the event and the coordinate
  private final Coordinate refCoord;

  public CoordinateComparator(Coordinate refCoord) {
    this.refCoord = refCoord;
  }

  @Override
  public int compare(Coordinate coord1, Coordinate coord2) {
    return coord1.distanceTo(refCoord).compareTo(coord2.distanceTo(refCoord));
  }
}
