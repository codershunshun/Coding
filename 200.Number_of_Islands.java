//并查集
class UnionFind {
  private int[] father;
  private int count;

  UnionFind(int n) {
    father = new int[n];

    for (int i = 0; i < n; i++) {
      father[i] = i;
    }
  }

  private int find(int x) {
    if (father[x] == x) {
      return x;
    }

    return father[x] = find(father[x]);
  }

  public void connect(int x, int y) {
    int xRoot = find(x);
    int yRoot = find(y);

    if (xRoot != yRoot) {
      father[xRoot] = yRoot;
      count--;
    }
  }

  public int query() {
    return count;
  }

  public void setCount(int total) {
    count = total;
  }
}

public class Solution {
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }

    int n = grid.length;
    int m = grid[0].length;
    int total = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == '1') {
          total++;
        }
      }
    }

    UnionFind uf = new UnionFind(n * m);
    uf.setCount(total);

    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == '1') {
          for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == '1') {
              uf.connect(i * m + j, nx * m + ny);
            }
          }
        }
      }
    }

    return uf.query();
  }
}

//BFS
class Solution {
  private class Coordinate {
    int x, y;

    Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }

    int islands = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '1') {
          islands++;
          markByBFS(grid, i, j);
        }
      }
    }

    return islands;
  }

  private void markByBFS(char[][] grid, int x, int y) {
    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};

    Queue<Coordinate> queue = new LinkedList<>();
    queue.add(new Coordinate(x, y));
    grid[x][y] = '0';

    while (!queue.isEmpty()) {
      Coordinate head = queue.poll();

      for (int i = 0; i < 4; i++) {
        int nx = head.x + dx[i];
        int ny = head.y + dy[i];

        if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == '1') {
          queue.add(new Coordinate(nx, ny));
          grid[nx][ny] = '0';
        }
      }
    }
  }
}
