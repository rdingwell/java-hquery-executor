## java-hquery-gateway.gemspec
#

Gem::Specification::new do |spec|
  spec.name = "java-hquery-gateway"
  spec.version = "0.1"
  spec.platform = Gem::Platform::JRUBY
  spec.summary = "java-hquery-gateway"
  spec.description = "description: it's java"

  spec.files =
["README",
 "README.erb",
 "Rakefile",
 "lib",
 "lib/commons-io-2.1.jar",
"lib/jcoffeescript-1.1.jar",
 "lib/js-14.jar",
 "lib/js.jar",
 "lib/mongo-2.7.2.jar",
 "java-hquery-gateway.gemspec"]

  spec.executables = []
  
  spec.require_path = "lib"

  spec.test_files = nil

### spec.add_dependency 'lib', '>= version'
#### spec.add_dependency 'map'

  spec.extensions.push(*[])

  spec.author = "Rob Dingwell"
  spec.email = "bobd@mitre.org"
  spec.homepage = "https://github.com/rdingwell/java=hquery-gateway"
end
