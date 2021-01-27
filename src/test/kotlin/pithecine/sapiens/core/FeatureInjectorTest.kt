package pithecine.sapiens.core

import kotlin.test.Test
import kotlin.test.assertEquals

class FeatureInjectorTest {

  @Test
  fun testInject() {

    class TestCalc {
      operator fun invoke(a: Double, b: Double): Double {
        return a * b
      }
    }

    val features = mapOf("a" to 5.5, "b" to 6.7)
    val product = FeatureInjector.inject(TestCalc()::invoke, features)

    assertEquals(5.5 * 6.7, product)
  }
}
