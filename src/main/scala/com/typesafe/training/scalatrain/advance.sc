import com.typesafe.training.scalatrain.CustomColl

object advance {

val myColl = CustomColl

val aList = List(1,2,3)

  private def addOne(n: Int) = n + 1
myColl.map(aList)(addOne)

}