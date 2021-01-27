package pithecine.sapiens.core

import kotlin.test.Test
import kotlin.test.assertEquals

const val MASS_FUEL_CONSUMPTION_RATE = "Mass Fuel Consumption Rate"
const val POWER_DEVELOPED = "Power Developed"

class FeatureInjectorTest {

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

    class SpecificFuelOilConsumption : (Double, Double) -> Double {
      override fun invoke(
        @FeatureAlias(MASS_FUEL_CONSUMPTION_RATE) fuelConsumption: Double,
        @FeatureAlias(POWER_DEVELOPED) powerDeveloped: Double
      ): Double {
        return fuelConsumption / powerDeveloped
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
      MASS_FUEL_CONSUMPTION_RATE to 1820831.57,
      POWER_DEVELOPED to 86.50
    )

    val product = FeatureInjector.inject(Product()::invoke, features)
//    val quotient = FeatureInjector.inject(::calcQuotient, features)
    val sfoc = FeatureInjector.inject(SpecificFuelOilConsumption()::invoke, features)

    assertEquals(5.5 * 6.7, product)
//    assertEquals(5.5 / 6.7, quotient)
    assertEquals(1820831.57 / 86.50, sfoc)
  }
}
