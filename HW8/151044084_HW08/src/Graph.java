public class Graph {
    private boolean graph[][];//unweighted adjacency matrix
    private int numVertex;//Number of vertices in a graph
    private boolean transitive[][];//transitivity calculated unweighted adjacency matrix

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < numVertex; i++) {
            for (int j = 0; j < numVertex; j++) {
                if (transitive[i][j] == true)
                    result += "1";
                else
                    result += "0";

                if (j != numVertex - 1)
                    result += ",";
            }
            if (i != numVertex - 1)
                result += "\n";
        }
        return result;
    }

    public Graph(int vertex) {
        this.numVertex = vertex;
        graph = new boolean[vertex][vertex];
        transitive = new boolean[vertex][vertex];

        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                if (i == j)
                    graph[i][j] = transitive[i][j] = true;
                else
                    graph[i][j] = transitive[i][j] = false;
            }
        }
    }

    public boolean insert(Edge edge) {
        if ((edge.getDest() > 0 && edge.getDest() <= numVertex) && (edge.getSource() > 0 && edge.getSource() <= numVertex)) {
            graph[edge.getSource() - 1][edge.getDest() - 1] = transitive[edge.getSource() - 1][edge.getDest() - 1] = true;
            return true;
        } else {
            System.out.println("This is not a valid edge on the graph.");
            return false;
        }
    }


    private void calcTransitivity() {
        for (int i = 0; i < numVertex; i++) {
            for (int j = 0; j < numVertex; j++) {
                for (int k = 0; k < numVertex; k++) {
                    if (transitive[j][i] && transitive[i][k])//transitivity control
                    {
                        transitive[j][k] = true;
                    }
                }
            }
        }
    }


    public int numOfPoP() {

        calcTransitivity();

        int count;
        int countpeople = 0;
        for (int i = 0; i < numVertex; i++) {
            count = 0;
            for (int j = 0; j < numVertex; j++) {
                if (transitive[j][i] == true) {
                    count++;
                }
            }
            if (count == numVertex) {
                countpeople++;
            }
        }

        return countpeople;
    }
}
