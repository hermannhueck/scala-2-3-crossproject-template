import Versions._
import Dependencies._
import ScalacOptions._

val projectName        = "scala-2-3-crossproject-template"
val projectDescription = "Scala 2 / Scala 3 Cross Project Template"

ThisBuild / fork                   := true
ThisBuild / turbo                  := true // default: false
ThisBuild / includePluginResolvers := true // default: false
Global / onChangedBuildSource      := ReloadOnSourceChanges

lazy val commonSettings = Seq(
  version            := projectVersion,
  scalaVersion       := scala3Version,
  crossScalaVersions := Seq(scala2Version, scala3Version),
  publish / skip     := true,
  semanticdbEnabled  := true,
  semanticdbVersion  := scalafixSemanticdb.revision,
  // scalafixDependencies ++= Seq("com.github.liancheng" %% "organize-imports" % scalafixOrganizeImportsVersion),
  // Test / parallelExecution := false,
  // run 100 tests for each property // -s = -minSuccessfulTests
  // Test / testOptions += Tests.Argument(TestFrameworks.ScalaCheck, "-s", "100"),
  initialCommands    :=
    s"""|
        |import scala.util.chaining._
        |import scala.concurrent.duration._
        |println()
        |""".stripMargin // initialize REPL
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(
    name        := projectName,
    description := projectDescription,
    // Compile / console / scalacOptions := consoleScalacOptions,
    libraryDependencies ++= Dependencies.allDependencies(scalaVersion.value),
    scalacOptions ++= defaultScalacOptions(scalaVersion.value)
  )
