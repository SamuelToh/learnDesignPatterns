LearnDesignPatterns
===================

Earlier this year, I was challenged to complete a programming assignment by somebody whom I don't wish to disclose here.<br>
*Refer to the problem specification below.<br>

Well, I was able to accompslish the programming task witin a tight 3 weekday timeframe (Note: I'm working full time :[ ) without too much sweat but I wasn't very happy with the overall architecture design of my solution, as it was far from acceptable.<br>

To achieve a working solution is usually not a difficult task for programmers in general but to come out with an architecture design that is extensible and easily understood by others can be a little tricky.<br>

Just last month, I was recommended by a good friend of mine to check out a design pattern book by called the Head first design patterns book by "Elisabeth Freeman and Eric Freeman". After finishing the book, I was pretty keen on trying out the gang of 4 design patterns, and hence the motivation in revisiting this interesting problem.

A few pointers that I have kept in mind when doing this project.<br>
* Design the classes upfront first
* Define the java interfaces before coding. That is, to code according to interfaces
* Use design patterns where appropriate
* Also, don't implement patterns only because you have to.

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


Note
======================
As the primary focus of this project is to learn to code with good practises, I am not going to put too much focus on the testing bit. Therefore you will find that the test coverage of my little project is very minimal. You may also realized that the tests are not very well written as again, testing is not the primary learning goal of this exercise.

