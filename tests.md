/**
  * Tries to fire the torpedo stores of the ship.
  *
  * @param firingMode how many torpedo bays to fire
  * 	SINGLE: fires only one of the bays.
  * 			- For the first time the primary store is fired.
  * 			- To give some cooling time to the torpedo stores, torpedo stores are fired alternating.
  * 			- But if the store next in line is empty, the ship tries to fire the other store.
  * 			- If the fired store reports a failure, the ship does not try to fire the other one.
  * 	ALL:	tries to fire both of the torpedo stores.
  *
  * @return whether at least one torpedo was fired successfully
  */


# Test single fire (fired alternating, empty condition)

In: GT4500,1,0,1,0 
Expected: SUCCESS
In: TORPEDO,SINGLE
Expected: SUCCESS
In: TORPEDO,SINGLE
Expected: SUCCESS
In: TORPEDO,SINGLE
Expected: FAIL

# Test all fire (fire ALL fires all loaded)

In: GT4500,1,0,1,0 
Expected: SUCCESS
In: TORPEDO,ALL
Expected: SUCCESS
In: TORPEDO,ALL
Expected: FAIL

# Test empty (fire fails when no torpedoes loaded)

In: GT4500,0,0,0,0
Expected: SUCCESS
In: TORPEDO, ALL
Expected: FAIL

# Test ALL with only one loaded (no fail when ALL is called with single bay loaded)

In: GT4500,1,0,0,0
Expected: SUCCESS
In: TORPEDO,ALL
Expected: SUCCESS
In: TORPEDO,ALL
Expected: FAIL

In: GT4500,0,0,1,0
Expected: SUCCESS
In: TORPEDO,ALL
Expected: SUCCESS
In: TORPEDO,ALL
Expected: FAIL

# Test fire malfunction

In: GT4500,1,1,0,0
Expected: SUCCESS
In: TORPEDO,SINGLE
Expected: FAIL

# Test two in primary

In: GT4500,2,0,0,0 
Expected: SUCCESS
In: TORPEDO,SINGLE
Expected: SUCCESS
In: TORPEDO,SINGLE
Expected: SUCCESS
In: TORPEDO,SINGLE
Expected: FAIL

# Test two in secondary

In: GT4500,0,0,2,0 
Expected: SUCCESS
In: TORPEDO,SINGLE
Expected: SUCCESS
In: TORPEDO,SINGLE
Expected: SUCCESS
In: TORPEDO,SINGLE
Expected: FAIL