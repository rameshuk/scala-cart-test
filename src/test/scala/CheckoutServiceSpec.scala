import ModelProtocol._
import org.scalatest.{Matchers, Outcome, fixture}



class CheckoutServiceSpec extends fixture.FlatSpec with Matchers {

  override type FixtureParam = CheckoutService

  override protected def withFixture(test: OneArgTest): Outcome = test(new CheckoutService)

  behavior of "CheckoutService without any offers"

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

  behavior of "CheckoutService with  offers"

  it should "return 4.10 when 12 Apples and 2 Oranges with buy one and get one offer on Apples" in {
    s => s.calculateCostOfBasket(createBasket(12, 2))(applyOffer = true) should equal("4.10")
  }

  it should "return 0.85 when 2 Apples and 1 Oranges with buy one and get one offer on Apples" in {
    s => s.calculateCostOfBasket(createBasket(2, 1))(applyOffer = true) should equal("0.85")
  }

  it should "return 9.25 when 55 Oranges with three for two offer on oranges" in {
    s => s.calculateCostOfBasket(createBasket(0, 55))(applyOffer = true) should equal("9.25") //cost for 37 oranges
  }

  it should "return 6.10 when 2 Apples and 32 Oranges with 2 for 1 apples and three for two offer on oranges" in {
    s => s.calculateCostOfBasket(createBasket(2, 32))(applyOffer = true) should equal("6.10") //cost for 1 apple, 22 oranges
  }

  private def createBasket(apples: Int, oranges: Int): List[Fruit] = {
    List.fill(apples)(Apple) ::: List.fill(oranges)(Orange)
  }
}

