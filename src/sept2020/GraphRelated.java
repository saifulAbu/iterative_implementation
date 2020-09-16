package sept2020;

import java.net.NetworkInterface;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class GraphRelated {
  static class GNode {
    int src;
    int dest;
    int weight;
    GNode(int src, int dest, int weight) {
      this.src = src;
      this.dest = dest;
      this.weight = weight;
    }
  }

  static class UndirectedGraph {
    List<List<GNode>> graph;
    int numVertices;

    UndirectedGraph(int numVertices) {
      this.numVertices = numVertices;
      graph = new ArrayList<>();
      for(int i = 0; i < numVertices; i++) {
        graph.add(new ArrayList<GNode>());
      }
    }

    void connect(int src, int dest) {
      if(!isValidVertex(src) || !isValidVertex(dest)) {
        throw new IllegalStateException();
      }
      graph.get(src).add(new GNode(src, dest, 1));
      graph.get(dest).add(new GNode(dest, src, 1));
    }

    int[] bfs(int startVertex) {
      int[] path = new int[numVertices];
      for(int i = 0; i < numVertices; i++) {
        path[i] = -1;
      }
      return bfsHelper(startVertex, path);
    }

    private int[] bfsHelper(int src, int[] path) {
      boolean [] visited = new boolean[numVertices];
      for(int i = 0; i < numVertices; i++) {
        visited[i] = false;
      }
      Queue<Integer> queue = new ArrayDeque<>();
      queue.add(src);
      visited[src] = true;
      while(!queue.isEmpty()) {
        int curVertex = queue.remove();
        for(GNode neighbor : graph.get(curVertex)) {
          int dest = neighbor.dest;
          if(!visited[dest]) {
            path[dest] = curVertex;
            queue.add(dest);
            visited[dest] = true;
          }
        }
      }
      return path;
    }

    private boolean isValidVertex(int vertex) {
      if(vertex < 0 || vertex >= numVertices) {
        return false;
      }
      return true;
    }
  }

  public static void main(String args[]) {
    UndirectedGraph undirectoedGraph = new UndirectedGraph(4);
    undirectoedGraph.connect(0 , 1);
    undirectoedGraph.connect(1 , 3);
    undirectoedGraph.connect(1 , 2);
    undirectoedGraph.connect(0 , 3);

    int[] path = undirectoedGraph.bfs(0);
    return;
  }
}
