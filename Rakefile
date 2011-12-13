require 'ant'
includeantruntime=true
directory "pkg/classes"

desc "Clean up build artifacts"
task :clean do
  rm_rf "pkg/classes"
  rm_rf "lib/jars/jqg.jar"
end

desc "Compile the extension"
task :compile => "pkg/classes" do |t|
  ant.javac :srcdir => "ext/java", :destdir => t.prerequisites.first,
    :source => "1.6", :target => "1.6", :debug => true,:includeantruntime=>true,
    :classpath => "${java.class.path}:${sun.boot.class.path}:lib/rhino-1.7R3.jar:./lib/mongo-2.7.2.jar:lib/commons-io-2.1.jar:lib/jcoffeescript-1.1.jar"
end

desc "Build the jar"
task :jar => :compile do
  ant.jar :basedir => "pkg/classes", :destfile => "lib/jars/jqg.jar", :includes => "**/*.class"
end
 
task :package => :jar

desc "Run the specs"
task :spec => :jar do
  ruby "-S", "spec", "spec"
end