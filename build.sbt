name := "akka-routing-bug"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val akkaVersion       = "2.4.4"
  val scalaTestVersion  = "3.0.0-M15"

  Seq(
    "com.typesafe.akka"    %% "akka-actor"                           % akkaVersion,
    "com.typesafe.akka"    %% "akka-slf4j"                           % akkaVersion,
    "com.typesafe.akka"    %% "akka-stream"                          % akkaVersion,
    "com.typesafe.akka"    %% "akka-http-core"                       % akkaVersion,
    "com.typesafe.akka"    %% "akka-http-experimental"               % akkaVersion,
    "com.typesafe.akka"    %% "akka-http-spray-json-experimental"    % akkaVersion,
    "com.typesafe.akka"    %% "akka-http-testkit"                    % akkaVersion,
    "org.scalatest"        %% "scalatest"                            % scalaTestVersion
  )
}