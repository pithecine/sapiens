package pithecine.sapiens.core

import kotlin.reflect.KCallable
import kotlin.test.Test
import kotlin.test.assertEquals

class FeatureCalcTest {
  @Test
  fun testInjectInvoke() {
    val features = mapOf("a" to 5.5, "b" to 6.7)
    val callable: KCallable<Double> = FeatureCalc::invoke
    val params = callable.parameters

    val a = params[1].name
    val b = params[2].name
    assertEquals(3, params.size)
    assertEquals("a", a)
    assertEquals("b", b)

    val namedParams = listOf(params[1].name, params[2].name)
    val opaqueParams: List<Any> = namedParams.map {
      features[it]!!.toDouble()
    }

    val product = callable.call(FeatureCalc(), *opaqueParams.toTypedArray())
    assertEquals(5.5 * 6.7, product)
  }
}
