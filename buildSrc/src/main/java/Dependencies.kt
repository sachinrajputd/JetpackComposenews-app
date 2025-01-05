// we created buildSrc directory in central bcz we need to create multiple module in app
// to create module level app we no need to write every module gradle file

// one more things we no need to create these  buildSrc folder, if we have lib.version.toml

object Dependencies {
    //    implementation(libs.androidx.core.ktx)
    val coreKtx by lazy {  }

}
object Modules{
    const val utilities= ":utilities"
}