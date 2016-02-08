/**
  * Created by mordonez on 2/3/16.
  */
object Utils {
  def fibonacci(i : Int) : Int = {
    def h(last : Int, cur: Int, num : Int) : Int = {
      if ( num == 0) cur
      else h(cur, last + cur, num - 1)
    }

    if (i < 0) - 1
    else if (i == 0 || i == 1) 1
    else h(1,2,i - 2)
  }
}
