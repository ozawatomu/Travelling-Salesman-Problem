# Travelling Salesman Problem
The Travelling Salesman Problem (TSP) asks the question:
> Suppose you are a salesman who needs to travel between multiple cities. Given their location, what is the shortest possible route you could take to visit each city once and return to the origin?

This problem has been historically difficult to solve. Although there are many approximation algorithms, the only exact solution runs in time **O(n!)**. This is the brute-force method of checking every permutation of cities and returning route with the shortest distance.

This program visualises the brute-force method on a slight variation of the TSP. The variation being, you do not need to start and end in the same city.

<img src="Travelling_Salesman_Demo.gif" width="600" alt="Program Demo">

The circles represent cities and the lines connecting them represent the route. The brighter lines are the best route that the algorithm has discovered so far and the dimmer lines are the routes that the algorithm is currently checking. The dimmmer lines will disappear once the algorithm has found the best route.

To create a city, **left** click anywhere in the window. **Click and drag** any of the cities to move it. **Right click** on any of the cities to delete it. The program will begin calculating the best route as soon as you complete any of these actions.
