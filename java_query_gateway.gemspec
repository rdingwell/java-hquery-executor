## java-query-gateway.gemspec
#

Gem::Specification::new do |spec|
  spec.name = "java-query-gateway"
  spec.version = "0.1"
  spec.platform = 'java'
  spec.summary = "java-query-gateway"
  spec.description = "description: it's java"

  spec.files = Dir['{lib,ext}/**/*'] + Dir['{*.gemspec,Rakefile}']

  spec.executables = []
  
  spec.require_path = "lib"

  spec.test_files = nil

  spec.extensions.push(*[])

  spec.author = "Rob Dingwell"
  spec.email = "bobd@mitre.org"
  spec.homepage = "https://github.com/rdingwell/java-hquery-gateway"
end
