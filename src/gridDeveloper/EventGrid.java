package gridDeveloper;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class EventGrid {

  // Assume x and y-axis axis span equally in +ive and -ive numbers, centered at
  // the origin (if the map area were to be expanded)
  public static final int X_BOUNDS = 10;
  public static final int Y_BOUNDS = 10;

  // Constants to determine the bounds of the randomly generated values. Assumes
  // the following bounds for randomly generated values
  public static final int NUM_EVENTS = 20;
  public static final int MIN_NUM_OF_EVENTS = 3;
  public static final int MAX_NUM_TICKETS = 5;
  public static final int MAX_TICKET_PRICE = 50;
  public static final int MIN_TICKET_PRICE = 5;

  private final Set<Coordinate> coordSet = new HashSet<>();
  private final Coordinate refCoord;
  private PriorityQueue<Event> eventQueue;

  public EventGrid(Coordinate refCoord) {
    this.refCoord = refCoord;
  }

  /**
   * Generates the seed data and adds the events to the queue, sorting them
   * based on their distance to the coordinate the user input.
   */
  private void generateEvents() {
    Random eventGenerator = new Random();
    Random ticketGenerator = new Random();
    Random coordinateGenerator = new Random();

    int numEvents = eventGenerator.nextInt(NUM_EVENTS + 1) + MIN_NUM_OF_EVENTS;
    int numTickets;

    // Create the queue of events with the comparator to specify the ordering
    eventQueue = new PriorityQueue<>(numEvents, new EventComparator(refCoord));

    int x;
    int y;
    double price;
    Coordinate coord;
    Event event;
    int numEventsGenerated = 0;

    // Ensure that you are generating numEvents events at different locations
    while (numEventsGenerated < numEvents) {
      x = coordinateGenerator.nextInt(2 * X_BOUNDS + 1) - X_BOUNDS;
      y = coordinateGenerator.nextInt(2 * Y_BOUNDS + 1) - Y_BOUNDS;
      coord = new Coordinate(x, y);

      // Loop again if an event already exists at that location
      if (coordSet.contains(coord)) {
        continue;
      }

      // Create the event
      event = new Event(coord);
      numTickets = ticketGenerator.nextInt(MAX_NUM_TICKETS + 1);

      // Add a random number of tickets to the event (possibly zero)
      for (int i = 0; i < numTickets; i++) {
        price =
            MIN_TICKET_PRICE + (ticketGenerator.nextDouble() * (MAX_TICKET_PRICE
                - MIN_TICKET_PRICE));
        price = ((double) Math.round(price * 100)) / 100;
        event.addTicket(price);
      }

      // Add the event to the queue
      eventQueue.add(event);

      // Add the coordinate to the set
      coordSet.add(coord);
      numEventsGenerated++;
    }
  }

  /**
   * Print the first five closest events in the queue, which can be polled off
   * the queue as they are sorted based on the distance to the specified
   * coordinate through the comparator. The code assumes that it is valid to
   * print an event which has no tickets left, assuming that the tickets have
   * "sold out"
   */
  public void printClosestFiveEvents() {
    Event event;
    for (int i = 0; i < 5 && !eventQueue.isEmpty(); i++) {
      event = eventQueue.poll();
      System.out.println(
          event.toString() + ", Distance " + event.distanceTo(refCoord));
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int coordX = 0;
    int coordY = 0;
    String line;
    String splitLine[];
    boolean repeat = true;
    String errorMsg = "Invalid Input: please enter the x and y coordinates separated by commas";

    System.out.println("Please input Coordinates");

    // Read the coordinate from standard input. Assumes correct input is of the
    // form: "x, y", where x and y are two integers in the range [-10, 10]
    do {
      try {
        System.out.print('>');
        line = scanner.nextLine().replace(" ", "");

        // Check the format of the input
        splitLine = line.split(",");
        if ((line.lastIndexOf(',') == line.length() - 1) || splitLine.length > 2) {
          System.out.println(errorMsg);
          continue;
        }
        coordX = Integer.parseInt(splitLine[0]);
        coordY = Integer.parseInt(splitLine[1]);

        // Check values are in bounds
        if (coordX > X_BOUNDS || coordX < -X_BOUNDS) {
          System.out.println(
              "x's value is out of bounds, please ensure that it's value lies"
                  + " between "
                  + X_BOUNDS + " and -" + X_BOUNDS);
          continue;
        } else if (coordY > Y_BOUNDS || coordY < -Y_BOUNDS) {
          System.out.println(
              "y's value is out of bounds, please ensure that it's value lies"
                  + " between "
                  + Y_BOUNDS + " and -" + Y_BOUNDS);
          continue;
        }
        repeat = false;
      } catch (Exception e) {
        System.out.println(errorMsg);
      }
    } while (repeat);
    scanner.close();

    // Create the coordinate to look up in the grid
    Coordinate refCoord = new Coordinate(coordX, coordY);

    EventGrid grid = new EventGrid(refCoord);

    // Generate the events in the grid and print the five closest ones
    grid.generateEvents();
    grid.printClosestFiveEvents();
  }
}
