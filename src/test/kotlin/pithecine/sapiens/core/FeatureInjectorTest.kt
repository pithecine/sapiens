package pithecine.sapiens.core

import kotlin.test.Test
import kotlin.test.assertEquals

const val FEATURE_A = "Feature A"
const val FEATURE_B = "Feature B"

class FeatureInjectorTest {

  private fun calcQuotient(a: Double, b: Double): Double {
    return a / b
  }

  @Test
  fun testInject() {

    class Product : (Double, Double) -> Double {
      override fun invoke(
        a: Double,
        b: Double
      ): Double {
        return a * b
      }
    }

    class FeatureCalc : (Double, Double) -> Double {
      override fun invoke(
        @FeatureAlias(FEATURE_A) featureA: Double,
        @FeatureAlias(FEATURE_B) featureB: Double
      ): Double {
        return featureA / featureB
      }
    }

    // Introspecting local functions, lambdas, anonymous functions and local variables is not yet fully supported in
    // Kotlin reflection

//    fun calcQuotient(a: Double, b: Double): Double {
//      return a / b
//    }

    val features = mapOf(
      "a" to 5.5,
      "b" to 6.7,
      FEATURE_A to 1820831.57,
      FEATURE_B to 86.50
    )

    val product = FeatureInjector.inject(Product()::invoke, features)
    val quotient = FeatureInjector.inject(::calcQuotient, features)
    val sfoc = FeatureInjector.inject(FeatureCalc()::invoke, features)

    assertEquals(5.5 * 6.7, product)
    assertEquals(5.5 / 6.7, quotient)
    assertEquals(1820831.57 / 86.50, sfoc)
  }
}
