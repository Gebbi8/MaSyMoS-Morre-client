package de.unirostock.sems.morre.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FeatureSet implements Serializable {
	
	private static final long serialVersionUID = 2819005447572420712L;
	private Map<String, String> features;
	
	public FeatureSet() {
		features = new HashMap<String, String>();
	}
	
	public FeatureSet set( String feature, String value ) {
		features.put(feature, value);
		return this;
	}
	
	public Map.Entry<List<String>, List<String>> getFeatures() {
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		
		Map.Entry<List<String>, List<String>> result = new Entry<List<String>, List<String>>(keys, values);
		
		Iterator<Map.Entry<String, String>> iter = features.entrySet().iterator();
		while( iter.hasNext() ) {
			Map.Entry<String, String> next = iter.next();
			keys.add( next.getKey() );
			values.add( next.getValue() );
		}
				
		return result;
	}
	
	public static class Entry<K, V> implements Map.Entry<K, V> {
		
		K key = null;
		V value = null;
		
		public Entry( K key ) {
			this.key = key;
		}
		
		public Entry( K key, V value ) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			this.value = value;
			return this.value;
		}
		
	}
}
