# Trigon-strategy

This code is unpolished but finished. Acts as a strategy provider to the mobile game "Trigon". Right now, yields an average result of 700~900 points which is much better than the average human play.

## Updates

6/16: Extended pivots to all testing and boosted performance.
6/16: See the list of shapes by entering <code>CommandList</code>, and allows re-entering if shape is invalid. Improved performance by changing parameters in <code>countscore</code>.

## How to use

Compile and execute Trigon. Every turn the scanner takes three lines of input corresponding the the three given shapes. To input, simply type the three shapes in three lines and the resulting placement and sequence will be printed. Coordinates are given in (x,y) with x being row (1~8) and y being column (maximum of y depends on the row, but is 1 indexed for counting ease).

String corresponding to shapes:
  * "4-up" and "4-down" for 4-tile triangles.
  * "Hor-dudu" and "Hor-udud" for 4 tile horizontal lines; former one corresponds to left-most tile (pivot) being a down triangle and vice versa.
  * "L-dudu" and "L-udud" for 4 tile lines that go from top-left to bottom-right; former one corresponds to bottom tile (pivot) being a down triangle and vice versa.
  * "R-dudu" and "R-udud" for 4 tile lines that go from top-right to bottom-left; former one corresponds to bottom tile (pivot) being a down triangle and vice versa.
  * "1-up" and "1-down" for 1 tile up/down triangles.
  * "up-down" for 2-tile up-down connected shape; pivot is the upper triangle.
  * "3-l", "3-r" and "3-hor" for 3-tile lines; pivot is the bottom up triangle for "3-l" and "3-r", middle up triangle for "3-hor".
  * Every other shape can be fit in a single 6-tile hexagon; for these shapes, the string is a six-digit binary number corresponding to the six triangles in the hex. The first digit stands for the 12-o'clock down triangle (and also the pivot), and then clockwise from there, with a "1" meaning the triangle is occupied, and "0" otherwise. Example: a 6-tile hex is "111111".

## Current algorithm

For each turn, the algorithm brute-force searches all locations and sequences of placements, then evaluates the final board for a "score" *s*. The moves corresponding to the highest *s* is returned. Occupied triangles do not count towards *s*. Each unoccupied single triangle has 1~3 neighbors and contribute u-1.8 torwards *s*, where u is the number of empty neighbors.

## Future work
  * Test multiple values to determine the optimal values used in <code>countscore</code>.
  * Extend pivot testing to test every possibility (Completed).
  * Create a more user-friendly interface and support wrong input correction.
