# RingList

RingList is an ultra fast generic implementation of a collection that solve the [22th day of advent of code 2019](https://adventofcode.com/2019/day/22)  

The collection can be updated with 3 actions:
- `reverseOrder` which reverse the list so that so first element become the last one, the second one become the second to last etc ...  
`[0 1 2 3 4 5 6 7 8 9]` -> `[9 8 7 6 5 4 3 2 1 0]`
- `cut N` which takes the first N element and put them at the end  
`[0 1 2 3 4 5 6 7 8 9]` -> `[3 4 5 6 7 8 9 0 1 2]` (example for cut 3)
- `deal with increment N` which take the nth element of the list (starting at index 0) and put it at the n times N position, wrapping around when the final index is longuer than the list.  
`[0 1 2 3 4 5 6 7 8 9]` -> `[0 7 4 1 8 5 2 9 6 3]` (example for deal with increment 3)  
:warning: The number N and the size of the list must be relatively prime numbers and the implementation does not double check than condition for performance motive


