import ModelProtocol._
import org.scalatest.{Matchers, Outcome, fixture}



class CheckoutServiceSpec extends fixture.FlatSpec with Matchers {

  override type FixtureParam = CheckoutService

  override protected def withFixture(test: OneArgTest): Outcome = test(new CheckoutService)

  behavior of "CheckoutService"

  it should "return 0.00 when checkout basket is empty" in {
    s => s.calculateCostOfBasket(List.empty[Fruit]) should equal("0.00")
  }

  it should "return 2.05 when checkbox contains 3 Apples and 1 Orange" in {

    s =>
      val basket = List(Apple, Apple, Orange, Apple)
      s.calculateCostOfBasket(basket) should equal("2.05")
  }

  it should "return 47.50 when checkbox contains 50 Apples and 70 Oranges" in {
    s => s.calculateCostOfBasket(createBasket(50, 70)) should equal("47.50")
  }


  private def createBasket(apples: Int, oranges: Int): List[Fruit] = {
    List.fill(apples)(Apple) ::: List.fill(oranges)(Orange)
  }
}

