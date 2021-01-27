package pithecine.sapiens.core

import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention
annotation class FeatureAlias(val alias: String) {

  // TODO: Also allow a flag for denoting runtime generated alias
  companion object {
    fun resolve(param: KParameter): String? {
      return param.findAnnotation<FeatureAlias>()?.alias ?: param.name
    }
  }
}
