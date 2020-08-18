package com.maslke.dubbo.samples.local;

import java.util.ArrayList;
import java.util.List;

public class GraphTest {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int[] begins = graph[0];
        for (int i = 0; i < begins.length; i++) {
            List<Integer> path = new ArrayList<>();
            boolean[] visited = new boolean[graph.length];
            path.add(0);
            visited[0] = true;
            visit(begins[i], visited, graph, path, ret);
        }
        return ret;
    }


    private void visit(int begin, boolean[] visited, int[][] graph, List<Integer> ret, List<List<Integer>> result) {
        if (visited[begin]) return;
        visited[begin] = true;
        ret.add(begin);
        if (ret.get(ret.size() - 1) == graph.length - 1) {
            result.add(new ArrayList<>(ret));
            return;
        }
        int[] another = graph[begin];
        for (int i = 0; i < another.length; i++) {
            visit(another[i], visited.clone(), graph, new ArrayList<>(ret), result);
        }
    }

    public static void main(String[] args) {
        int[][] input = new int[5][];
        input[0] = new int[]{4,3,1};
        input[1] = new int[] {3,2,4};
        input[2] = new int[]{3};
        input[3] = new int[]{4};
        input[4] = new int[]{};
        GraphTest test = new GraphTest();
        List<List<Integer>> output = test.allPathsSourceTarget(input);
        System.out.println(output.toString());
    }
}
