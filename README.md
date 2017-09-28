# KMeans -- Computing 

This assignment creates the KMeans algorithm from scratch with Point LinkedList as inputs. The major class function take the inputs and classify the points as required by the user. 

## Function/Class Description

This project consists of five .java files.

*  The Vector.java defines the vector class that will be inherited by Point. It contains basic algorithms that are frequently used for vectors.
*  The Point.java defines the Point class that inherits from Vector class. It contains the cluster id number and the method "findClosest" to find out the closest centroid. 
* The Centroid.java defines the centroid class for implementing in KMeans. Basically, it has no difference with Point.
*  The Cluster.java defines the cluster class that assembly points in specific cluster with its unique centroid and id number.
*  The KMeans.java, KMeans2.java are two main functionality in implementing the KMeans method. 
*  The KMeans_Example.java contains the examples of testing the KMeans functionality.
*  The RandomPoint.java, StdOut.java, Pair.java, doubleComparator.java are four classes that ease the implementation of the KMeans algorithm. 
*  The Test_Point.java, Test_Cluster.java are used to test the functionalities defined in the Point and Cluster class.

## KMeans Implementation

KMeans.java and KMeans2.java are both the algorithms for calculating the K means clusters. However, they differ in mechnism. The KMeans.java traverses all points and find the correct cluster to which each point belongs. Hence, if we assign the cluster number to be N, it won't be necessarily that the final cluster number would be N. It will be a number less than N, which means for some clusters there won't be any points in it. The KMeans2.java fixed the number of clusters and number of points in each cluster. Hence, we should gaurantee that the number of points can be divided by the number of initial clusters so that we can distribute evenly to each cluster. Moreover, it iterates over all centroids instead of points. Namely, we find the first N/c number of points ranked by the distance with the centroid where N stands for the number of points and c stands for the number clusters.

### Calling the function.

To run the KMeans method, we call the following function. If we already have the data, it can be called by the following.

```
KMeans cluster_1 = new KMeans(your points linkedlist, num of clusters, number of iterations, tolerance) ;
```
The last parameter controls the convergence of this method. If the sum of shifts of centroids are less than this tolerance, then the process stops.

If we do not have the data, we can just call it as 

```
KMeans cluster_1 = new KMeans() ;
```

and then set up the data as 

```
cluster_1.setInitialPointStatus(your point linkedlist);
cluster_1.setInitialClusterNumber(number of clusters);
cluster_1.setIterationNumber(number of iterations);
cluster_1.setConvergenceTolerance(tolerance)

```
*   The following method will initialize the KMeans.

```
cluster_1.init(true/false)
```
The parameter here controls whether you want the description of initial clusters: true stands for returning the description. Then we can fit the KMeans by calling the following. 

```
cluster_1.fit(true/false)
```
If the parameter is true, it will output the centroids list in each iteration. 
*   The following method will obtain the summary.
```
cluster_1.getSummary()
```
Notice this will output all the description of the clusters including the points. If you just want the centroids summary, you can call the following:
```
cluster_1.getCentroidSummary()
```

It then will returns the centroid of each cluster and number of points in each cluster.

## Authors

* **Xiao Guan** - *Initial work* - [XiaoGuan_Summer Assignment](https://github.com/guan4015/KMeans)


## Acknowledgments

The author thanks Professor Lee Maclin for his help on this assignment.
