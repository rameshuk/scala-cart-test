import ModelProtocol._


class CheckoutService {

  def calculateCostOfBasket(basket: List[Fruit]): String = {
    val sum = basket.map(_.value).sum / 100
    f"$sum%1.2f"
  }

}

object ModelProtocol {

  sealed trait Fruit{ def value: BigDecimal  }
  case object Apple extends Fruit {override val value: BigDecimal  = 60}
  case object Orange extends Fruit {override val value: BigDecimal  = 25}

}