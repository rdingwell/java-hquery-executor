package org.projecthquery.gateway.mongo;

import java.net.UnknownHostException;
import java.util.Iterator;

import org.projecthquery.gateway.ValueIterator;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class MongoValueIterator implements ValueIterator {
    DB db;
    DBCollection collection;
    Mongo mongo;

    @Override
    public Iterator<String> iterator() {
        DBCursor cursor = collection.find();
        return new Iter(cursor);
    }

    @Override
    public void init(String[] args) throws RuntimeException {

        try {
            mongo = new Mongo(args[0], Integer.parseInt(args[1]));
        } catch (UnknownHostException e) {
            throw new RuntimeException("Error connecting to Mongo ", e);
        }
        db = mongo.getDB(args[2]);
        collection = db.getCollection(args[3]);

    }

    class Iter implements Iterator<String> {
        DBCursor cursor;

        Iter(DBCursor cur) {
            this.cursor = cur;
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub

        }

        @Override
        public String next() {
            // TODO Auto-generated method stub
            return this.cursor.next().toString();
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return cursor.hasNext();
        }
    }
}
