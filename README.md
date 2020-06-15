# Trigon-strategy

This code is unpolished but finished. Acts as a strategy provider to the mobile game "Trigon". Right now, yields an average result of ~700 points which is much better than the average of human play.

## How to use

Every turn the scanner takes three lines of input corresponding the the three given shapes. To input, simply type the three shapes in three lines and the resulting placement and sequence will be printed. Coordinates are given in (x,y) with x being row (1~8) and y being column (maximum of y depends on the row, but is 1 indexed for counting ease).

String corresponding to shapes:
  * "up" and "down" for 4-tile triangles
  * "Hor-dudu" and "Hor-udud" for 4 tile horizontal lines; former one corresponds to left-most tile being a down triangle and vice versa.
  * "L-dudu" and "L-udud" for 4 tile lines that go from top-left to bottom-right; former one corresponds to bottom tile being a down triangle and vice versa.
  * "R-dudu" and "R-udud" for 4 tile lines that go from top-right to bottom-left; former one corresponds to bottom tile being a down triangle and vice versa.
  * Every other shape can be fit in a single 6-tile hexagon; for these shapes, the string is a six-digit binary number corresponding to the six triangles in the hex. The first digit stands for the 12-o'clock down triangle, and then clockwise from there, with a "1" meaning the triangle is occupied, and "0" otherwise. Example: a 6-tile hex is "111111" and a 1-tile up triangle is "010000" or "000100" or "000001".

## Current algorithm

For each turn, the algorithm brute-force searches all locations and sequences of placements, then evaluates the final board for a "score" *s*. The moves corresponding to the highest *s* is returned. Occupied triangles do not count towards *s*. Each unoccupied single triangle has 1~3 neighbors and contribute u-1.8 torwards *s*, where u is the number of empty neighbors.

## Future work
  * Test multiple values to determine the optimal values used in <code>countscore</code>.
  * Extend pivot testing to test every possibility.
  * Create a more user-friendly interface and support wrong input correction.
