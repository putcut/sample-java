package util;

import java.util.ArrayList;
import java.util.List;

public class Partition {
  public static void main(String[] args) {
    Partition p = new Partition();
    List<List<Integer>> list = p.partPattern(6, 3);
    System.out.println(list);
  }

  /**
   * 自然数 n を分割したリストを返す
   * 
   * @param n
   * @return
   */
  private List<List<Integer>> partOfNumber(int n) {
    return partOfNumber(n, n);
  }

  /**
   * 自然数 n を m 以下の整数で分割したリストを返す
   * 
   * @param n
   * @param m
   * @return
   */
  private List<List<Integer>> partOfNumber(int n, int m) {
    List<List<Integer>> list = new ArrayList<>();
    partOfNumber(n, m, new ArrayList<Integer>(), list);
    return list;
  }

  /**
   * 自然数を分割をする再帰関数
   * 
   * @param n
   * @param m
   * @param list
   * @param all
   * @see http://www.nct9.ne.jp/m_hiroi/puzzle/partition.html#ans02
   */
  private void partOfNumber(int n, int m, List<Integer> list, List<List<Integer>> all) {
    if (n == 0) {
      all.add(list);
    } else if (n == 1) {
      List<Integer> tmp = new ArrayList<>(list);
      tmp.add(1);
      all.add(tmp);
    } else if (m == 1) {
      List<Integer> tmp = new ArrayList<>(list);
      for (int i = 0; i < n; i++) {
        tmp.add(1);
      }
      all.add(tmp);
    } else {
      if (n >= m) {
        List<Integer> tmp = new ArrayList<>(list);
        tmp.add(m);
        partOfNumber(n - m, m, tmp, all);
      }
      partOfNumber(n, m - 1, list, all);
    }
  }

  /**
   * 数値リストをサイズ n にゼロパディングする<br>
   * n より大きいリストは取り除かれる
   * 
   * @param list
   * @param n
   * @return
   */
  private List<List<Integer>> paddingZero(List<List<Integer>> list, int n) {
    return list.stream()
        .filter(l -> l.size() <= n)
        .map(l -> {
          for (int i = l.size(); i < n; i++) {
            l.add(0);
          }
          return l;
        })
        .toList();
  }

  /**
   * 自然数 n を 1 以上の整数で m 個に分割したパターンを返す<br>
   * 順序を考慮しない
   * 
   * @param n
   * @param m
   * @return
   * @see http://techtipshoge.blogspot.com/2011/01/blog-post_28.html
   */
  private List<List<Integer>> partPattern(int n, int m) {
    List<List<Integer>> partPattern = paddingZero(partOfNumber(n - m), m);

    for (int i = 0; i < partPattern.size(); i++) {
      List<Integer> part = partPattern.get(i);
      for (int j = 0; j < part.size(); j++) {
        part.set(j, part.get(j) + 1);
      }
    }

    return partPattern;
  }
}
