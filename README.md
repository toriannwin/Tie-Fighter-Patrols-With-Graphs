# Tie-Fighter-Patrols-With-Graphs
Utilizes an input file to create a galaxy of systems using a graph that indicates which systems can be gotten to by other systems. Then reads in a file of routes, determines whether they are valid, and if so, calculates the distance of the routes.

Requires file: "galaxy.txt" which holds the list of systems (represented by an intenger number) and which ones they are connected to (and the integer distance between those systems). Used to create the graph in the program. Sample galaxy.txt is in the repository.

Requires file: "pilot_routes.txt" which holds the list of pilots and the systems they are supposed to patrol. Will create a file "patrols.txt" which will display which of the routes were valid, and if they were valid, how long the path to patrol is.

Project performs input validation on the pilot_routes.txt file. First uses the galaxy file to create a directed graph in memory, which is then used to determine whether the routes are valid, and if so, how long their patrol length will be by totalling the distances at each node. Uses a generic Node in the Graph, and the Pilot object is used as the object for the Node.
