public class Edge {
    private int source;//The source vertex for an unweighted edge
    private int dest;//The destination vertex for an unweighted edge


    //Constructs an Edge from source to dest
    public Edge(int source, int dest) {
        /*if (source == dest)
            throw new IllegalArgumentException("Source and destination cannot be the same");*/
        this.source = source;
        this.dest = dest;
    }

    //Returns the source vertex.
    public int getSource() {
        return source;
    }

    //Returns the destination vertex.
    public int getDest() {
        return dest;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", destination=" + dest +
                '}';
    }
}
