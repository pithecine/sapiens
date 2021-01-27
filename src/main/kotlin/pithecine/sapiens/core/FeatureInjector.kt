package pithecine.sapiens.core

import kotlin.reflect.KCallable
import kotlin.reflect.KParameter

class FeatureInjector {

  companion object {
    fun <T> inject(callable: KCallable<T>, features: Map<String, Any?>): T? {
      return callable.callBy(buildArgs(callable.parameters, features))
    }

    private fun buildArgs(params: List<KParameter>, features: Map<String, Any?>): Map<KParameter, Any?> {
      val args = mutableMapOf<KParameter, Any?>()
      params.map { args.put(it, features[it.name]) }
      return args
    }
  }
}
