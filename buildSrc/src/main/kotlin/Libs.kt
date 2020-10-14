import kotlin.String

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Update this file with
 *   `$ ./gradlew buildSrcVersions`
 */
object Libs {
  /**
   * https://detekt.github.io/detekt
   */
  const val detekt_formatting: String = "io.gitlab.arturbosch.detekt:detekt-formatting:" +
      Versions.io_gitlab_arturbosch_detekt

  const val io_gitlab_arturbosch_detekt_gradle_plugin: String =
      "io.gitlab.arturbosch.detekt:io.gitlab.arturbosch.detekt.gradle.plugin:" +
      Versions.io_gitlab_arturbosch_detekt

  /**
   * https://github.com/bumptech/glide
   */
  const val com_github_bumptech_glide_compiler: String = "com.github.bumptech.glide:compiler:" +
      Versions.com_github_bumptech_glide

  /**
   * https://github.com/bumptech/glide
   */
  const val glide: String = "com.github.bumptech.glide:glide:" + Versions.com_github_bumptech_glide

  /**
   * https://github.com/square/retrofit
   */
  const val adapter_rxjava2: String = "com.squareup.retrofit2:adapter-rxjava2:" +
      Versions.com_squareup_retrofit2

  /**
   * https://github.com/square/retrofit
   */
  const val converter_moshi: String = "com.squareup.retrofit2:converter-moshi:" +
      Versions.com_squareup_retrofit2

  /**
   * https://github.com/square/retrofit
   */
  const val retrofit: String = "com.squareup.retrofit2:retrofit:" + Versions.com_squareup_retrofit2

  /**
   * https://square.github.io/okhttp/
   */
  const val logging_interceptor: String = "com.squareup.okhttp3:logging-interceptor:" +
      Versions.com_squareup_okhttp3

  /**
   * https://square.github.io/okhttp/
   */
  const val okhttp: String = "com.squareup.okhttp3:okhttp:" + Versions.com_squareup_okhttp3

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_android_extensions: String = "org.jetbrains.kotlin:kotlin-android-extensions:" +
      Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_android_extensions_runtime: String =
      "org.jetbrains.kotlin:kotlin-android-extensions-runtime:" + Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_annotation_processing_gradle: String =
      "org.jetbrains.kotlin:kotlin-annotation-processing-gradle:" + Versions.org_jetbrains_kotlin

  const val kotlin_gradle_plugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:" +
      Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_reflect: String = "org.jetbrains.kotlin:kotlin-reflect:" +
      Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_scripting_compiler_embeddable: String =
      "org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:" + Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_stdlib_jdk8: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:" +
      Versions.org_jetbrains_kotlin

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val navigation_fragment_ktx: String = "androidx.navigation:navigation-fragment-ktx:" +
      Versions.androidx_navigation

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val navigation_safe_args_gradle_plugin: String =
      "androidx.navigation:navigation-safe-args-gradle-plugin:" + Versions.androidx_navigation

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val navigation_ui_ktx: String = "androidx.navigation:navigation-ui-ktx:" +
      Versions.androidx_navigation

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val lifecycle_compiler: String = "androidx.lifecycle:lifecycle-compiler:" +
      Versions.androidx_lifecycle

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val lifecycle_extensions: String = "androidx.lifecycle:lifecycle-extensions:" +
      Versions.androidx_lifecycle

  /**
   * https://developer.android.com/jetpack/androidx
   */
  const val lifecycle_runtime_ktx: String = "androidx.lifecycle:lifecycle-runtime-ktx:" +
      Versions.androidx_lifecycle

  /**moshi_adapters
   * https://github.com/square/moshi/
   */
  const val moshi_adapters: String = "com.squareup.moshi:moshi-adapters:" +
      Versions.com_squareup_moshi

  /**
   * https://github.com/square/moshi/
   */
  const val moshi_kotlin: String = "com.squareup.moshi:moshi-kotlin:" + Versions.com_squareup_moshi

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val work_runtime_ktx: String = "androidx.work:work-runtime-ktx:" + Versions.androidx_work

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val work_rxjava2: String = "androidx.work:work-rxjava2:" + Versions.androidx_work

  const val koin_android: String = "org.koin:koin-android:" + Versions.org_koin

  const val koin_androidx_viewmodel: String = "org.koin:koin-androidx-viewmodel:" +
      Versions.org_koin

  /**
   * https://developer.android.com/studio
   */
  const val com_android_tools_build_gradle: String = "com.android.tools.build:gradle:" +
      Versions.com_android_tools_build_gradle

  const val de_fayard_buildsrcversions_gradle_plugin: String =
      "de.fayard.buildSrcVersions:de.fayard.buildSrcVersions.gradle.plugin:" +
      Versions.de_fayard_buildsrcversions_gradle_plugin

  /**
   * https://github.com/florent37/RuntimePermission
   */
  const val runtime_permission_kotlin: String = "com.github.florent37:runtime-permission-kotlin:" +
      Versions.runtime_permission_kotlin

  const val firebase_analytics: String = "com.google.firebase:firebase-analytics-ktx"

  const val firebase_messaging: String = "com.google.firebase:firebase-messaging-ktx"

  const val firebase_crashlytics: String = "com.google.firebase:firebase-crashlytics-ktx"

  /**
   * http://github.com/square/leakcanary/
   */
  const val leakcanary_android: String = "com.squareup.leakcanary:leakcanary-android:" +
      Versions.leakcanary_android

  /**
   * https://developer.android.com/jetpack/androidx
   */
  const val swiperefreshlayout: String = "androidx.swiperefreshlayout:swiperefreshlayout:" +
      Versions.swiperefreshlayout

  /**
   * http://tools.android.com
   */
  const val constraintlayout: String = "androidx.constraintlayout:constraintlayout:" +
      Versions.constraintlayout

  /**
   * https://github.com/hdodenhof/CircleImageView
   */
  const val circleimageview: String = "de.hdodenhof:circleimageview:" + Versions.circleimageview

  const val google_services: String = "com.google.gms:google-services:" + Versions.google_services

  const val camera_camera2: String = "androidx.camera:camera-camera2:" + Versions.camera_camera2

  /**
   * https://github.com/mockito/mockito
   */
  const val mockito_inline: String = "org.mockito:mockito-inline:" + Versions.mockito_inline

  /**
   * https://github.com/nhaarman/mockito-kotlin
   */
  const val mockito_kotlin: String = "com.nhaarman.mockitokotlin2:mockito-kotlin:" +
      Versions.mockito_kotlin

  /**
   * https://developer.android.com/testing
   */
  const val espresso_core: String = "androidx.test.espresso:espresso-core:" + Versions.espresso_core

  /**
   * http://assertj.org
   */
  const val assertj_core: String = "org.assertj:assertj-core:" + Versions.assertj_core

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val core_testing: String = "androidx.arch.core:core-testing:" + Versions.core_testing

  /**
   * https://developer.android.com/jetpack/androidx
   */
  const val fragment_ktx: String = "androidx.fragment:fragment-ktx:" + Versions.fragment_ktx

  /**
   * https://github.com/Dhaval2404/ImagePicker/
   */
  const val imagepicker: String = "com.github.dhaval2404:imagepicker:" + Versions.imagepicker

  /**
   * https://developer.android.com/studio
   */
  const val lint_gradle: String = "com.android.tools.lint:lint-gradle:" + Versions.lint_gradle

  /**
   * https://github.com/ButterflyTV/Librtmp-Client-for-Android
   */
  const val rtmp_client: String = "net.butterflytv.utils:rtmp-client:" + Versions.rtmp_client

  /**
   * http://developer.android.com/tools/extras/support-library.html
   */
  const val annotation: String = "androidx.annotation:annotation:" + Versions.annotation

  /**
   * http://plattysoft.github.io/Leonids/
   */
  const val leonidslib: String = "com.plattysoft.leonids:LeonidsLib:" + Versions.leonidslib

  /**
   * https://developer.android.com/jetpack/androidx
   */
  const val viewpager2: String = "androidx.viewpager2:viewpager2:" + Versions.viewpager2

  /**
   * https://developer.android.com/jetpack/androidx
   */
  const val appcompat: String = "androidx.appcompat:appcompat:" + Versions.appcompat

  const val exoplayer: String = "com.google.android.exoplayer:exoplayer:" + Versions.exoplayer

  /**
   * https://github.com/auth0/jwtdecode.android
   */
  const val jwtdecode: String = "com.auth0.android:jwtdecode:" + Versions.jwtdecode

  /**
   * https://developer.android.com/jetpack/androidx
   */
  const val core_ktx: String = "androidx.core:core-ktx:" + Versions.core_ktx

  /**
   * https://github.com/material-components/material-components-android
   */
  const val material: String = "com.google.android.material:material:" + Versions.material

  /**
   * https://github.com/ReactiveX/RxKotlin
   */
  const val rxkotlin: String = "io.reactivex.rxjava2:rxkotlin:" + Versions.rxkotlin

  const val rxpaper2: String = "com.github.pakoito:RxPaper2:" + Versions.rxpaper2

  /**
   * https://github.com/ReactiveX/RxJava
   */
  const val rxjava: String = "io.reactivex.rxjava2:rxjava:" + Versions.rxjava

  /**
   * https://developer.android.com/studio
   */
  const val aapt2: String = "com.android.tools.build:aapt2:" + Versions.aapt2

  /**
   * http://junit.org
   */
  const val junit: String = "junit:junit:" + Versions.junit
}
