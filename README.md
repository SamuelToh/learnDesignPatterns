LearnDesignPatterns
===================

I was challenged to complete a programming assignment by somebody earlier this year. (refer to problem specification below)<br>
Even though I was able to accomplish the task within a tight 3 days timeframe (Note: I'm working full time :[  )<br>

After reading the Head first design patterns book by "Elisabeth Freeman and Eric Freeman", I realized I didn't really do a good job with it as I have failed to implement good designs to my solution and I have not make use of java interfaces.<br>

According to "Head first design patterns" we should always design first and then "code according to interfaces".<br>

In this programming exercise, I would like to show case what I have learned from the book by trying to challenge myself into using the gang of 4 design patterns where appropriate.
<br>

Problem Specification
======================

 PROBLEM: KIWILAND TRAINS

 Problem: The local commuter railroad services a number of towns in Kiwiland. Because of monetary concerns, all of the tracks are 'one-way.'
 That is, a route from Kaitaia to Invercargill does not imply the existence of a route from Invercargill to Kaitaia. In fact, even if both of these routes do happen to exist, they are distinct and are not necessarily the same distance!

 The purpose of this problem is to help the railroad provide its
 customers with information about the routes. In particular, you will compute the distance along a certain route, the number of different routes between two towns, and the shortest route between two towns.

 Input: A directed graph where a node represents a town and an edge
 represents a route between two towns. The weighting of the edge represents the distance between the two towns. A given route will never appear more than once, and for a given route, the starting and ending town will not be the same town.

 Output: For test input 1 through 5, if no such route exists, output
 'NO SUCH ROUTE'. Otherwise, follow the route as given; do not make any extra stops! For example, the first problem means to start at city A, then travel directly to city B (a distance of 5), then directly to city C (a distance of 4).

 1. The distance of the route A-B-C.
 2. The distance of the route A-D.
 3. The distance of the route A-D-C.
 4. The distance of the route A-E-B-C-D.
 5. The distance of the route A-E-D.
 6. The number of trips starting at C and ending at C with a maximum of 3 stops. In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
 7. The number of trips starting at A and ending at C with exactly 4 stops.
 In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
 8. The length of the shortest route (in terms of distance to travel) from A to C.
 9. The length of the shortest route (in terms of distance to travel) from B to B.
 10. The number of different routes from C to C with a distance of less than 30. In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.


TODO
======================
As the primary focus of this project is to learn writing java codes with good practise and designs where appropriate. 
Hence the current test coverage is very minimal and you may also realized that they are not very well written, because testing is not the primary learning goal of this exercise.

