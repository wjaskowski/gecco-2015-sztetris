About
-----
Code for running the experiments for the paper "N-Tuple Network for Knowledge-Free Reinforcement Learning in High Dimensions: A Case Study in SZ-Tetris" published at GECCO 2015.

Authors
-------
Wojciech Jaśkowski
Marcin Szubert
Paweł Liskowski

Demo
----
<a href="http://www.youtube.com/watch?feature=player_embedded&v=0bJFt2ltPyA
" target="_blank"><img src="http://img.youtube.com/vi/0bJFt2ltPyA/0.jpg" 
alt="IMAGE ALT TEXT HERE" width="640" height="480" border="10" /></a>

Prerequisites
-------------
Java 8, Maven

Building
--------
```bash
> mvn package
```

Running
-------
To evaluate the best agents on 10000 games:
```bash
java -jar cevo.jar put.ci.cevo.experiments.gecco2015tetris.TetrisEvaluateAgentsPerformanceExperiment
```

To run the experiments described in the paper (for a given seed):
```
java -Xmx2g -Dlog4j.configuration=file:log4j.properties -Dresults_dir=results/BI-CEM/123 -Dseed=123 -Dframework.properties=configs/tetris/BI-p1000_g100_CEM.properties -jar cevo.jar put.ci.cevo.experiments.gecco2015tetris.FeaturesBasedTetrisExperiment
```

```
java -Xmx2g -Dlog4j.configuration=file:log4j.properties -Dresults_dir=results/BI-CMAES/123 -Dseed=123 -Dframework.properties=configs/tetris/BI-p1000_g100_CMAES.properties -jar cevo.jar put.ci.cevo.experiments.gecco2015tetris.FeaturesBasedTetrisExperiment
```

```
java -Xmx2g -Dlog4j.configuration=file:log4j.properties -Dresults_dir=results/NTuples3x3-CEM/123 -Dseed=123 -Dframework.properties=configs/tetris/all-3x3_p1000_g100_CEM.properties -jar cevo.jar put.ci.cevo.experiments.gecco2015tetris.NTuplesTetrisExperiment
```

```
java -Xmx2g -Dlog4j.configuration=file:log4j.properties -Dresults_dir=results/NTuples3x3-VD_CMAES/123 -Dseed=123 -Dframework.properties=configs/tetris/all-3x3_p1000_g100_CMAES-VD.properties -jar cevo.jar put.ci.cevo.experiments.gecco2015tetris.NTuplesTetrisExperiment
```

```
java -Xmx2g -Dlog4j.configuration=file:log4j.properties -Dexperiment.output=results/TDL-4x4 -Dexperiment.seed=123 -Dframework.properties=configs/tetris/tdl-4x4_alpha-0001_exp-01_dec.properties -jar cevo.jar put.ci.cevo.experiments.gecco2015tetris.TDLTetrisExperiment
```

```
java -Xmx2g -Dlog4j.configuration=file:log4j.properties -Dexperiment.output=results/TDL-4x4 -Dexperiment.seed=123 -Dframework.properties=configs/tetris/tdl-3x3_alpha-0001_exp-01_dec.properties -jar cevo.jar put.ci.cevo.experiments.gecco2015tetris.TDLTetrisExperiment
```
