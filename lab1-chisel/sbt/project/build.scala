import sbt._
import Keys._

object BuildSettings
{
  val buildOrganization = "edu.berkeley.cs"
  val buildVersion = "1.1"
  val buildScalaVersion = "2.9.2"
  // val buildScalaVersion = "2.10.0-M4"

  def apply(projectdir: String) = {
    Defaults.defaultSettings ++ Seq (
      organization := buildOrganization,
      version      := buildVersion,
      scalaVersion := buildScalaVersion,
      scalaSource in Compile := Path.absolute(file(projectdir + "/src/main/scala"))
    )
  }
}

object ChiselBuild extends Build
{
  import BuildSettings._

  lazy val chisel = Project("chisel", file("chisel"), settings = BuildSettings(java.lang.System.getenv("CHISEL")))
//  lazy val tutorial = Project("work", file("work"), settings = BuildSettings("..")) dependsOn(chisel)
  lazy val work = Project("work", file("work"), settings = BuildSettings(".")) dependsOn(chisel)
}
