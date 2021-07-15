import org.scalajs.linker.interface.{ModuleInitializer, OutputPatterns}

ThisBuild / scalaVersion := "2.12.10"
ThisBuild / organization := "com.iamsmkr"

lazy val root = project.in(file(".")).aggregate(chesstourplayground.js, chesstourplayground.jvm).
  settings(
    publish := {},
    publishLocal := {},
  )

lazy val chesstourplayground = crossProject(JSPlatform, JVMPlatform).in(file(".")).
  settings(
    name := "chess-tour-playground",
    version := "0.1",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.9" % Test,
      "org.scalatestplus" %% "scalacheck-1-15" % "3.2.9.0" % Test
    )
  ).
  jsSettings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "upickle" % "1.3.15",
      "org.scala-js" %%% "scalajs-dom" % "1.1.0",
      "com.lihaoyi" %%% "scalatags" % "0.9.2"
    )
  ).
  jvmSettings(
    libraryDependencies ++= Seq(
      "org.scala-js" %% "scalajs-stubs" % "1.0.0" % "provided"
    )
  )
