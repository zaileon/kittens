name := "preowned-kittens"
version := "1.0"

val gitHeadCommitSha = taskKey[String]("Determine the current git commit SHA")
gitHeadCommitSha := Process("git rev-version HEAD").lines.head

libraryDependencies +=
	"org.specs2" % "specs2_2.10" % "1.14" % "test"