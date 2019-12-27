# Monitors and Semaphores

## Monitor

A monitor is an object which exports a collection of methods, each of which aquires a lock when it is called and releases it when it returns. Depending on the method it might make sense to either wait or spin.


## Barrier using Semaphore

### Single use for two threads

Thread P:
```
P_arrived = 0;
...
release(P_arrived)
aquire(Q_arrived)
...
```

Thread Q:
```
Q_arrived = 0;
...
release(Q_arrived)
aquire(P_arrived)
...
```

### Mutli-use n-thread barrier

```java
mutex=1; barrier1=0; barrier2=1; count=0;

aquire(mutex)
count++
if(count == n){
	aquire(barrier2) // to stop them at next turnstyle
	release(barrier1) // to let them pass this turnstile
}
release(mutex)

aquire(barrier1)
release(barrier1)

// second round to reset the turnstyle
aquire(mutex)
count--
if(count == 0){
	aquire(barrier1) // to stop them at next turnstyle -> if they were to aquire the semaphore again
	release(barrier2) // to let them pass this turnstile
}
release(mutex)


aquire(barrier2)
release(barrier2)
```