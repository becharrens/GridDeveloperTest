package gridDeveloper;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {

  // Coordinate used to determine the ordering of the events on the queue,
  // based on the distance between the event and the coordinate
  private final Coordinate refCoord;

  public EventComparator(Coordinate refCoord) {
    this.refCoord = refCoord;
  }

  @Override
  public int compare(Event event1, Event event2) {
    return event1.distanceTo(refCoord).compareTo(event2.distanceTo(refCoord));
  }
}
