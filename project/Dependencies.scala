import sbt._

object Dependencies {

  import Versions._

  lazy val munit       = "org.scalameta" %% "munit"        % munitVersion
  lazy val slf4jApi    = "org.slf4j"      % "slf4j-api"    % slf4jVersion
  lazy val slf4jSimple = "org.slf4j"      % "slf4j-simple" % slf4jVersion

  // https://github.com/typelevel/kind-projector
  lazy val kindProjectorPlugin    = compilerPlugin(
    compilerPlugin("org.typelevel" % "kind-projector" % kindProjectorVersion cross CrossVersion.full)
  )
  // https://github.com/oleg-py/better-monadic-for
  lazy val betterMonadicForPlugin = compilerPlugin(
    compilerPlugin("com.olegpy" %% "better-monadic-for" % betterMonadicForVersion)
  )

  lazy val compilerDependencies = Seq(
    munit,
    slf4jApi,
    slf4jSimple
  )

  lazy val testDependencies = Seq.empty

  def pluginDependencies(scalaVersion: String): Seq[ModuleID] = {
    CrossVersion.partialVersion(scalaVersion) match {
      case Some((2, 13)) => Seq(kindProjectorPlugin, betterMonadicForPlugin)
      case _             => Seq.empty
    }
  }

  def allDependencies(scalaVersion: String) =
    compilerDependencies ++ testDependencies ++ pluginDependencies(scalaVersion)
}
