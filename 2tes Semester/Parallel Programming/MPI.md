# Message Passing Interface


## Synchronous

`sSend` Synchronous send waits until recipient is ready to accept the message before sending.

`Recv` Synchronous receive waits until expected message arrives

This is useful for:

- transfer data
- synchronize processes


## Asynchronous

`Send` does not wait actions to complete before returning
-> this requires local storage for the messages (either manual or automatic)

## Blocking / Non-Blocking


Blocking communication is done using MPI_Send() and MPI_Recv(). These functions do not return (i.e., they block) until the communication is finished. Simplifying somewhat, this means that the buffer passed to MPI_Send() can be reused, either because MPI saved it somewhere, or because it has been received by the destination. Similarly, MPI_Recv() returns when the receive buffer has been filled with valid data.

In contrast, non-blocking communication is done using MPI_Isend() and MPI_Irecv(). These function return immediately (i.e., they do not block) even if the communication is not finished yet. You must call MPI_Wait() or MPI_Test() to see whether the communication has finished.

Blocking communication is used when it is sufficient, since it is somewhat easier to use. Non-blocking communication is used when necessary, for example, you may call MPI_Isend(), do some computations, then do MPI_Wait(). This allows computations and communication to overlap, which generally leads to improved performance.


## Overview:

**Synchronous vs Asynchronous:** about communication between sender and reciever (if there should be a working channel with both parties active or just dumping in information)

**Blocking vs Non-blocking:** 
A local property about handling data to be sent/recieved. Should we wait until sending / recieving finishes or do that in background




## Problems with MPI
Assume we have:

1. T0: Send(1) | T1: Send(0) 
2. T0: Recv(1) | T1: Recv(0) 

We will deadlock ourselves if the buffer is full and we cannot continue since we use blocking operations.

**Solutions:**

- Reorder the instructions
	1. T0: Send(1) | T1: Recv(0) 
	2. T0: Recv(1) | T1: Send(0) 
- Use nonblocking operations
	1. T0: Isend(1) | T1: Irecv(0) 
	2. T0: Irecv(1) | T1: Isend(0) 
	3. T0: waitall  | T1: waitall

-> the waitall is very important since it stops the programs until all non-blocking calls finish.


## List of important functions

- MPI.Init -> initialize the library
- MPI.COMM_SIZE -> number of processes in the communicator
- MPI.COMM_RANK -> rank of the calling process
- MPI.Send -> send a message to another process
- MPI.Recv -> recieve a message from another process
- MPI.Finalize -> clean up all of MPI

## More advanced functions:

- Reduce -> one process collects all data from all processes
- Scan -> ??
- Broadcast -> send a piece of data that one process has to all processes
- scatter -> partition array amongst processes
- gather -> collect array contetx from processes into one single array for a single process (reverse of scatter)
- Allreduce -> reduce and then broadcast but in a more efficient way
- allgather -> every process has a piece of information, at end all processes have a copy of every piece of information
- alltoall -> all processes have array: first element goes to first process etc. for all objects in the array.
- barrier -> blocks caller until all processes in the communicator have called it.
