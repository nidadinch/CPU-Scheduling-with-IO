# CPU-Scheduling-with-IO

CPU Scheduling with IO using FCFS algorithm


## About The Project

It is a project for Akdeniz University Computer Science Department - Operating Systems Lecture.

A  program  to compare the  performance of FCFS algorithm.This program  gets a filename (e.g., “jobs.txt”) as the command-line input and read the contents  of  the file. Provided file contains a set of processes and a set of associated CPU and I/O bursts that shown in below:

```sh

1:(45,15);(16,20);(80,10);(40,-1)

2:(15,10);(60,15);(90,10);(85,20);(20,-1)

3:(30,15);(40,20);(5,15);(10,15);(15,-1)

   ```

The general format of a line is as follows:

```sh

<process-id>:(<cpu-burst1, io-burst1>);(<cpu-burst2, io-burst2>);...(<cpu-bursti, io-bursti>)

   ```


The first token is the unique process id. After process-id we have a colon(:) delimiter. Then you will see a list of tuples separated semicolons (;). Each tuple in parentheses indicates the next cpu-burst and io-burst lengths of the process.The cpu and io burst length in terms of milliseconds.

If the last io-burst is -1, then it means that the process terminates without making an I/O.

Assume that ;
* All the jobs arrive at  the same time (t=0), the  order  of arrival is  the  same as  the  order  of process-ids (i.e., smaller ids arrive earlier).
* The process never waits at the device queues and I/O starts immediately.

Program prints the following:
* Average turnaround time: The average of the turnaround times of all process
* Average waiting time: The average of the total waiting time for all processes.

## Built With

* Java

## Getting Started


* ```sh
   javac cpuScheduling.java

   ```
* ```sh
   java cpuScheduling jobs.txt

   ```


## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


## License

Distributed under the MIT License. See `LICENSE` for more information.


## Contact

Nida Dinç - niddinc@gmail.com

Project Link: [https://github.com/nidadinch/CPU-Scheduling-with-IO](https://github.com/nidadinch/CPU-Scheduling-with-IO)
