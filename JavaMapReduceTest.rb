require 'java'
require 'lib/commons-io-2.1.jar'
require 'lib/jcoffeescript-1.1.jar'
require 'lib/js.jar'
require 'lib/js-14.jar'
require 'lib/mongo-2.7.2.jar'
$CLASSPATH << 'build/classes'

require 'rubygems'




gem_root = Gem.loaded_specs['hquery-patient-api'].full_gem_path
java_import "org.projecthquery.gateway.MapReduceBuilder"
java_import "org.projecthquery.gateway.MapReduce"
java_import "org.projecthquery.gateway.PatientJSBuilder"


papi = PatientJSBuilder.getPatientAPI(gem_root+"/app/assets/javascripts/")


puts papi

mrb = MapReduceBuilder.new(papi, "org.projecthquery.gateway.mongo.MongoValueIterator", ["localhost","27017","query_gateway_development", "records"].to_java(:string), "org.projecthquery.gateway.InMemoryHashEmitter", [].to_java(:string))
map_js =
'function map(patient) {
  if (patient.age() > 1) {
    emit("age", patient.age());
    emit("count", 1);
  }
};'
reduce_js =
'function reduce(key, values) {
  var sum = 0;
  for(var i in values) sum += values[i];
  return sum;
};'
mr = MapReduce.new(mrb, map_js, reduce_js, '', 'X')

result = mr.run
keys = result.keySet.iterator
while keys.hasNext
	key = keys.next
	value = result.get(key)
	puts "#{key}==>#{value}"
end

puts "Done!"
