# Grid Developer Test
Used to simulate software that lists the 5 closest events to a set of coordinates input by the user.

## Assumptions
1) I made assumptions on several bounds for the random generation, specified in
the constants in EventGrid
2) It is valid to consider and print out events which have no tickets remaining

## How might the program be changed to support multiple events at the same location?
Currently, the program uses a map of coordinates to events to store all the
events, meaning that only one event can be stored at each location, which
ensured by checking that the event's coordinate is not a key in the map already.
In order to allow multiple events, only a few changes would need to be made:
Firstly, the map should now map coordinates to list of events, and the check
would need to be modified so that if the coordinate hadn't been added, it would
add the coordinate to the map along with an empty list. Then, any event created
should  always be added to the list which the coordinate maps to. In this case,
the printing of the five closest events would probably need to change to
account for the possibility of each coordinate mapping to more than one event.

## How might the program be changed to work on a much larger scale (e.g. on a world size scale)?
The main issue with the current implementation is the memory usage. All the data
is currently being stored in a single map, something which would be unfeasible
for a much larger data set. For this reason, I believe the best approach would
be to divide the data set into several maps, each storing the events of
different regions, so that the memory size is more manageable. Then, when
querying for the closest events to a certain coordinate, you would simply need
to query the region it belongs to and the adjacent regions. For this reason,
to speed up the lookup, these maps would have to be stored in some sort of tree
structure, like a binary tree, which splits the search space as you traverse it.
Because this tree would likely take up a large amount of data, it would probably
be stored in external memory, and small parts of it could be read in at a time
to speed up the queries.

## How to run
The solution is written in Java, so the java need the
1) To compile, from the project's home directory run: make
2) cd into bin/
3) Run: java EventGrid
4) To remove all generated files: make clean
