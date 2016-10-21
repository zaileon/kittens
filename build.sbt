name := "preowned-kittens"

val gitHeadCommitSha = taskKey[String]("Determine the current git commit SHA")
val makeVersionProperties = taskKey[Seq[File]]("Makes a version.properties file.")

// Common settings/definitions for the build
def PreownedKittenProject(name: String): Project = 
    Project(name, file(name))
    .settings(
        version := "1.0",
        organization := "com.preownedkittens",
        libraryDependencies += "org.specs2" % "specs2_2.10" % "1.14" % "test"
    )
gitHeadCommitSha in ThisBuild := Process("git rev-parse HEAD").lines.head

// Projects in this build
lazy val common = (
    PreownedKittenProject("common")
    .settings(
        makeVersionProperties := {
            val propFile = new File((resourceManaged in Compile).value, "version.properties")
            val content = "version=%s" format (gitHeadCommitSha.value)
            IO.write(propFile, content)
            Seq(propFile)
        }
    )
)

lazy val analytics = (
    PreownedKittenProject("analytics")
    .dependsOn(common)
    .settings()
)

lazy val website = (
    PreownedKittenProject("website")
    .dependsOn(common)
    .settings()
)





// resourceGenerators in Compile += makeVersionProperties
