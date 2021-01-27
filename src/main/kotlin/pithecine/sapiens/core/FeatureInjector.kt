package pithecine.sapiens.core

import kotlin.reflect.KCallable
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.isAccessible

class FeatureInjector {

  companion object {
    fun <T> inject(callable: KCallable<T>, features: Map<String, Any?>): T? {
      return callable.apply { isAccessible = true }.callBy(buildArgs(callable.parameters, features))
    }

    private fun buildArgs(params: List<KParameter>, features: Map<String, Any?>): Map<KParameter, Any?> {
      val args = mutableMapOf<KParameter, Any?>()
      params.map { args.put(it, features[FeatureAlias.resolve(it)]) }
      return args
    }
  }
}
