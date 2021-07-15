# chess-tour-playground
This is a scalajs based project that makes use of a shared library built in Scala which implements chess tour algorithm. This shared library is cross-compiled to both Scala and javascript so as to facilitate usage of the library in a front-end app.

## Solution
The solution is based on Warnsdorff Heuristic Algorithm implemented as tail recursive back-tracking. See a sample dry-run of a simplified problem statement [here](https://docs.google.com/spreadsheets/d/13-e_vpze8RIx9d_2ZaKqY4_NoKBgP1p4cKifGu-lvhg/edit?usp=sharing).

## Test
The tests are written as property based test cases.
```
$ sbt test
```

## Build
Build chess tour shared library as a distributable optimized version javascript file like so:
```
$ sbt fullOptJS
```

## Deploy
The optimized file is built under `js/target/scala-2.12` as `chess-tour-playground-opt.js`. This file needs to be moved to the `/public` directory.
```
$ mv js/target/scala-2.12/chess-tour-playground-opt.js public
```

## Usage
Open `index.html` in a browser of your choice to launch **Chess Tour Playground**. You can also access already deployed version [here](https://iamsmkr.github.io/chess-tour-playground/).
