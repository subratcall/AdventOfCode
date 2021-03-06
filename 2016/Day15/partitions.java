import java.util.Scanner;
import java.io.*;
/*
--- Day 15: Timing is Everything ---

The halls open into an interior plaza containing a large kinetic sculpture.
The sculpture is in a sealed enclosure and seems to involve a set of identical
spherical capsules that are carried to the top and allowed to bounce through
the maze of spinning pieces.

Part of the sculpture is even interactive! When a button is pressed, a capsule
is dropped and tries to fall through slots in a set of rotating discs to
finally go through a little hole at the bottom and come out of the sculpture.
If any of the slots aren't aligned with the capsule as it passes, the capsule
bounces off the disc and soars away. You feel compelled to get one of those
capsules.

The discs pause their motion each second and come in different sizes; they seem
to each have a fixed number of positions at which they stop. You decide to call
the position with the slot 0, and count up for each position it reaches next.

Furthermore, the discs are spaced out so that after you push the button, one
second elapses before the first disc is reached, and one second elapses as the
capsule passes from one disc to the one below it. So, if you push the button at
time=100, then the capsule reaches the top disc at time=101, the second disc at
time=102, the third disc at time=103, and so on.

The button will only drop a capsule at an integer time - no fractional seconds
allowed.

For example, at time=0, suppose you see the following arrangement:

Disc #1 has 5 positions; at time=0, it is at position 4.
Disc #2 has 2 positions; at time=0, it is at position 1.
If you press the button exactly at time=0, the capsule would start to fall; it
would reach the first disc at time=1. Since the first disc was at position 4 at
time=0, by time=1 it has ticked one position forward. As a five-position disc,
the next position is 0, and the capsule falls through the slot.

Then, at time=2, the capsule reaches the second disc. The second disc has
ticked forward two positions at this point: it started at position 1, then
continued to position 0, and finally ended up at position 1 again. Because
there's only a slot at position 0, the capsule bounces away.

If, however, you wait until time=5 to push the button, then when the capsule
reaches each disc, the first disc will have ticked forward 5+1 = 6 times (to
position 0), and the second disc will have ticked forward 5+2 = 7 times (also
to position 0). In this case, the capsule would fall through the discs and come
out of the machine.

However, your situation has more than two discs; you've noted their positions
in your puzzle input. What is the first time you can press the button to get a
capsule?

--- Part Two ---

After getting the first capsule (it contained a star! what great fortune!),
the machine detects your success and begins to rearrange itself.

When it's done, the discs are back in their original configuration as if it
were time=0 again, but a new disc with 11 positions and starting at position 0
has appeared exactly one second below the previously-bottom disc.

With this new disc, and counting again starting from time=0 with the
configuration in your puzzle input, what is the first time you can press the
button to get another capsule?

*/
public class partitions{
  //For part 1 reduce diskammounts to 6
  static int disksAmmounts = 7;

  //At 0 = MaxPosition, at 1= actualPosition
  static int[][] disks = new int[disksAmmounts][2];
  
  public static boolean rotateDisks(int time){
    int movements = 0;
    int count = 0;
    // System.out.println("Time: "+time);
    for(int i = 0; i < disksAmmounts; i++){
      int maxPosition = disks[i][0];
      int actualPosition = disks[i][1];
      actualPosition += time+i+1;
      while(actualPosition > (maxPosition-1)) actualPosition %= maxPosition;
      // System.out.println("Disk "+(i+1)+" - Max Position: "+maxPosition+" & Actual Position: "+actualPosition);
      if(actualPosition == 0) ++count;
    }
    if(count == disksAmmounts)
      return true;
    return false;
  }

  public static void main(String[] args) throws FileNotFoundException{
    //For Part1 un uncomment this
    // Scanner tcl = new Scanner(new File("C:/Users/Alexis/OneDrive/Documents/AdventOfCode/Day15/disks.txt"));

    //And Comment this
    Scanner tcl = new Scanner(new File("C:/Users/Alexis/OneDrive/Documents/AdventOfCode/Day15/disks2.txt"));

    int index = 0;
    int time = 0;
    boolean retrievedCapsuled = false;

    while(tcl.hasNext()){
      String[] parts = tcl.nextLine().split(" ");
      int maxPosition = Integer.parseInt(parts[3]);
      int actualPosition = Integer.parseInt(parts[parts.length-1].substring(0,parts[parts.length-1].length()-1));
      disks[index][0] = maxPosition;
      disks[index++][1] = actualPosition;
    }

    while(!retrievedCapsuled){
      if(rotateDisks(time++))
        retrievedCapsuled = true;
    }
    System.out.println("Press button at time "+ (time-1));
  }
}
