## Mutual Exclusion

Only one thread is in a critical section at a time

## Deadlock freedom

There will always be a thread able to enter the critical section

## Starvation freedom

All threads wanting to enter a critical section will eventually be able to do so.

## Lock-Free

A method is lock-free if it guarantees that infinitely often some method call 
finishes in a finite number of steps. 
No assumption on other threads

## Wait-Free

The wait-free property guarantees that every thread that takes steps makes 
progress. 


## Problems of Starvation and Deadlock freedom

Both properties make assumptions on the machine / OS:

1. No thread dies
2. Every thread is scheduled by the OS

These assumptions can be too strong for real word applications →  We need stronger properties


## Producer Consumer

...