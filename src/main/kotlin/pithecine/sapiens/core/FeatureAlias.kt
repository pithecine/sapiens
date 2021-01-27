package pithecine.sapiens.core

import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention
annotation class FeatureAlias(val alias: String) {

  // TODO: Also allow a flag for denoting runtime generated alias
  companion object {
    fun resolveAlias(param: KParameter): String? {
      val ann = param.findAnnotation<FeatureAlias>()
      if (ann != null) {
        return ann.alias
      }
      return param.name
    }
  }
}
