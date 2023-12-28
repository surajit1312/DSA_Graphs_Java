package topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * CodingNinjas: Course Schedule II
 * 
 * Link: https://www.codingninjas.com/studio/problems/course-schedule-ii_1069243
 * 
 * TC: O(V + E)
 * SC: O(2V) ~ O(V)
 * 
 */
public class P4_Course_Schedule_I {
    public static void main(String[] args) {
        int n = 4;
        int[][] courses = {
                { 2, 1 },
                { 3, 1 },
                { 4, 2 },
                { 4, 3 }
        };
        List<List<Integer>> prerequisites = getCoursePrerequisites(courses, n);
        List<Integer> schedule = findSchedule(n, prerequisites);
        System.out.println("Course schedule to finish all courses : " + schedule);
    }

    private static List<Integer> findSchedule(int numCourses, List<List<Integer>> prerequisites) {
        ArrayList<ArrayList<Integer>> adj = getAdjacencyList(prerequisites, numCourses);
        List<Integer> toposort = new ArrayList<Integer>();
        int[] indegrees = getInDegrees(adj, numCourses);

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            toposort.add(current);
            for (Integer it : adj.get(current)) {
                indegrees[it]--;
                if (indegrees[it] == 0) {
                    queue.offer(it);
                }
            }
        }
        for (int i = 0; i < toposort.size(); i++) {
            toposort.set(i, toposort.get(i) + 1);
        }
        if (toposort.size() == numCourses) {
            return toposort;
        }
        return new ArrayList<Integer>();
    }

    private static int[] getInDegrees(ArrayList<ArrayList<Integer>> adj, int n) {
        int[] indegrees = new int[n];
        for (int i = 0; i < indegrees.length; i++) {
            for (Integer it : adj.get(i)) {
                indegrees[it]++;
            }
        }
        return indegrees;
    }

    private static ArrayList<ArrayList<Integer>> getAdjacencyList(List<List<Integer>> prerequisites, int n) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < prerequisites.size(); i++) {
            adj.get(prerequisites.get(i).get(1) - 1).add(prerequisites.get(i).get(0) - 1);
        }
        return adj;
    }

    private static List<List<Integer>> getCoursePrerequisites(int[][] courses, int n) {
        List<List<Integer>> prerequisites = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            List<Integer> coursePair = new ArrayList<Integer>();
            coursePair.add(courses[i][0]);
            coursePair.add(courses[i][1]);
            prerequisites.add(coursePair);
        }
        return prerequisites;
    }
}
