package gridDeveloper;

import java.util.PriorityQueue;

public class Event {

  private static final int MAX_NUM_DIGITS = 3;
  private static int numEvents = 0;

  // Stores the ticket prices, where the head of the queue is always the
  // cheapest prize
  private final PriorityQueue<Double> tickets;
  private final int id;

  public Event() {
    this.tickets = new PriorityQueue<>();
    this.id = ++numEvents;
  }

  /**
   * Pads the id with zeros so that when printed, all ids have the same length
   *
   * @return A string containing the id number padded with zeros so that it has
   * the required length, specified by the static variable
   */
  public static String padIdWithZeros(int n) {
    int nDigits = ((int) Math.log10(n)) + 1;
    String padding = "";
    for (int i = 0; i < MAX_NUM_DIGITS - nDigits; i++) {
      padding += '0';
    }
    return padding + n;
  }

  /**
   * Adds a ticket prize to the queue
   *
   * @param ticketPrice the ticket's prize
   */
  public void addTicket(double ticketPrice) {
    tickets.add(ticketPrice);
  }

  @Override
  public String toString() {
    return "Event " + padIdWithZeros(id) + " - " + ((tickets.size() > 0) ? ("$"
        + tickets.peek()) : "SOLD OUT");
  }
}
