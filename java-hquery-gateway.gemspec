## java-hquery-gateway.gemspec
#

Gem::Specification::new do |spec|
  spec.name = "java_query_executor"
  spec.version = "0.1"
  spec.platform = 'java'
  spec.summary = "java_query_executor"
  spec.description = "description: it's java"

  spec.files = Dir['{lib,ext}/**/*'] + Dir['{java_query_executor.rb,*.gemspec,Rakefile}']

  spec.executables = []
  
  spec.require_path = "lib"

  spec.test_files = nil

  spec.extensions.push(*[])

  spec.author = "Rob Dingwell"
  spec.email = "bobd@mitre.org"
  spec.homepage = "https://github.com/rdingwell/java-hquery-gateway"
end
