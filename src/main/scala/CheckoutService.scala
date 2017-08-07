import ModelProtocol._


class CheckoutService {

  /*
   * calculates cost of basket
   * by default offers not applied to basket
   */
  def calculateCostOfBasket(basket: List[Fruit])(implicit applyOffer: Boolean = false): String = {

    val total = {
      if (applyOffer) applyCurrentOffers(basket) else basket
    }.map(_.value).sum / 100
    "%1.2f".format(total)
  }


  //list offers can apply here
  private def applyCurrentOffers(basket: List[Fruit]): List[Fruit] = {
    val basketAfterApplyOffer = applyOffer(basket, Apple, _ / 2) //buy one get one offer
    applyOffer(basketAfterApplyOffer, Orange, _ * 2 / 3) //3 for 2

  }

  private def applyOffer(basket: List[Fruit], prod: Fruit, apply: Double => Double): List[Fruit] = {
    val partition = basket.partition(_ == prod)
    val noItemsAfterApply = math.ceil(apply(partition._1.length.toDouble)).toInt
    List.fill[Fruit](noItemsAfterApply)(prod) ::: partition._2
  }
}

object ModelProtocol {

  sealed trait Fruit { def value: BigDecimal }

  case object Apple extends Fruit { override val value: BigDecimal = 60 }

  case object Orange extends Fruit { override val value: BigDecimal = 25 }

}