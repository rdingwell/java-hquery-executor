require 'query_utilities'

require 'java'
require 'lib/commons-io-2.1.jar'
require 'lib/jcoffeescript-1.1.jar'
#require 'lib/js.jar'
require 'jars/jqg.jar'
require 'jars/mongo-2.7.2.jar'


java_import "org.projecthquery.gateway.MapReduceBuilder"
java_import "org.projecthquery.gateway.MapReduce"

class JavaQueryExecutor
  
  include QueryUtilities
  
  VALUE_ITERATOR = "org.projecthquery.gateway.mongo.MongoValueIterator"
  VALUE_ARGS = ["localhost","27017","query_gateway_test", "records"].to_java(:string)
  EMIT_ITERATOR = "org.projecthquery.gateway.InMemoryHashEmitter"
  EMIT_ARGS = [].to_java(:string)
  #BUILDER = MapReduceBuilder.new(QueryUtilities.patient_api_javascript, "org.projecthquery.gateway.mongo.MongoValueIterator", ["localhost","27017","query_gateway_development", "records"].to_java(:string), "org.projecthquery.gateway.InMemoryHashEmitter", [].to_java(:string))
  
  def self.builder
    @@builder ||= MapReduceBuilder.new(QueryUtilities.patient_api_javascript, VALUE_ITERATOR, VALUE_ARGS, EMIT_ITERATOR, EMIT_ARGS)
  end
  
  def initialize(map_js, reduce_js="function reduce(k,v){return v;}", functions_js="", query_id = "", filter={})
    @engine = MapReduce.new(JavaQueryExecutor.builder, map_js, reduce_js, functions_js||"", query_id||"")
  end
  
  def execute
    results = @engine.run
    retval = {}
    keys = results.keySet.iterator
    while keys.hasNext
        key = keys.next
        value = results.get(key)
        retval[key] = value
    end
    retval
  end
  
end