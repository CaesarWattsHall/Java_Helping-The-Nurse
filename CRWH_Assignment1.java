/*
*By: Caesar R. Watts-Hall
*Date: February 21, 2019
*Class: JAVA II
*Instructor: Dr.Primo
*Assignment: Indie Assignment #1
*Due Date: February 21, 2019 @ 1:55PM
*/
//START
package crwh_assignment1;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class CRWH_Assignment1 {
    private int          crwhdistances[];
    private Set<Integer> crwhsettled;
    private Set<Integer> crwhunsettled;
    private int          crwhNumNodes;
    private int          crwhadjacencyMatrix[][];
    
    public CRWH_Assignment1(int crwhNumberNodes)
    {
        this.crwhNumNodes = crwhNumberNodes;
        crwhdistances = new int[crwhNumberNodes + 1];
        crwhsettled = new HashSet<>();
        crwhunsettled = new HashSet<>();
        crwhadjacencyMatrix = new int[crwhNumberNodes + 1][crwhNumberNodes + 1];
    }
 public void dijkstra_algorithm(int adjacency_matrix[][], int source)
    {
        int evaluationNode;
        for (int i = 1; i <= crwhNumNodes; i++)
            for (int j = 1; j <= crwhNumNodes; j++)
                crwhadjacencyMatrix[i][j] = adjacency_matrix[i][j];
 
        for (int i = 1; i <= crwhNumNodes; i++)
        {
            crwhdistances[i] = Integer.MAX_VALUE;
        }
 
        crwhunsettled.add(source);
        crwhdistances[source] = 0;
        while (!crwhunsettled.isEmpty())
        {
            evaluationNode = getNodeWithMinimumDistanceFromUnsettled();
            crwhunsettled.remove(evaluationNode);
            crwhsettled.add(evaluationNode);
            evaluateNeighbours(evaluationNode);
        }
    }
 
    private int getNodeWithMinimumDistanceFromUnsettled()
    {
        int min;
        int node = 0;
 
        Iterator<Integer> iterator = crwhunsettled.iterator();
        node = iterator.next();
        min = crwhdistances[node];
        for (int i = 1; i <= crwhdistances.length; i++)
        {
            if (crwhunsettled.contains(i))
            {
                if (crwhdistances[i] <= min)
                {
                    min = crwhdistances[i];
                    node = i;
                }
            }
        }
        return node;
    }
 
    private void evaluateNeighbours(int evaluationNode)
    {
        int edgeDistance = -1;
        int newDistance = -1;
 
        for (int destinationNode = 1; destinationNode <= crwhNumNodes; destinationNode++)
        {
            if (!crwhsettled.contains(destinationNode))
            {
                if (crwhadjacencyMatrix[evaluationNode][destinationNode] != Integer.MAX_VALUE)
                {
                    edgeDistance = crwhadjacencyMatrix[evaluationNode][destinationNode];
                    newDistance = crwhdistances[evaluationNode] + edgeDistance;
                    if (newDistance < crwhdistances[destinationNode])
                    {
                        crwhdistances[destinationNode] = newDistance;
                    }
                    crwhunsettled.add(destinationNode);
                }
            }
        }
    }
 
    public static void main(String... arg)
    {
        int adjacency_matrix[][];
        int number_of_vertices;
        int source = 0, destination = 0;
        Scanner scan = new Scanner(System.in);
        try
        {
            System.out.println("Enter the number of vertices");
            number_of_vertices = scan.nextInt();
            adjacency_matrix = new int[number_of_vertices + 1][number_of_vertices + 1];
 
            System.out.println("Enter the Weighted Matrix for the graph");
            for (int i = 1; i <= number_of_vertices; i++)
            {
                for (int j = 1; j <= number_of_vertices; j++)
                {
                    adjacency_matrix[i][j] = scan.nextInt();
                    if (i == j)
                    {
                        adjacency_matrix[i][j] = 0;
                        continue;
                    }
                    if (adjacency_matrix[i][j] == 0)
                    {
                        adjacency_matrix[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
 
            System.out.println("Enter the source ");
            source = scan.nextInt();
 
            System.out.println("Enter the destination ");
            destination = scan.nextInt();
 
            CRWH_Assignment1 crwhAlgorithm = new CRWH_Assignment1(
                    number_of_vertices);
            crwhAlgorithm.dijkstra_algorithm(adjacency_matrix, source);
 
            System.out.println("The Shorted Path from " + source + " to " + destination + " is: ");
            for (int i = 1; i <= crwhAlgorithm.crwhdistances.length - 1; i++)
            {
                if (i == destination)
                    System.out.println(source + " to " + i + " is "
                            + crwhAlgorithm.crwhdistances[i]);
            }
        } 
        
        catch (InputMismatchException inputMismatch)
        {
            System.out.println("Wrong Input Format");
        }
        scan.close();
    }
}
//END