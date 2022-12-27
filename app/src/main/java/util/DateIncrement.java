package util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class DateIncrement {
  public static void main(String[] args) {
    LocalDate now = LocalDate.now();
    LocalDate result = increment(now, 5, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    System.out.println(result);
  } 

  /**
   * 指定曜日は除いて日付に日数を加算する
   *
   * @param ld 基準日
   * @param days 加算日数
   * @param excludes 除外曜日
   * @return 計算後の日付
   */
  private static LocalDate increment(LocalDate ld, int days, DayOfWeek... excludes) {
    List<DayOfWeek> list = List.of(excludes);
    for (int i = 0; i < days;) {
      ld = ld.plusDays(1);
      if (!list.contains(ld.getDayOfWeek())) {
        i++;
      }
    }
    return ld;
  }
}
