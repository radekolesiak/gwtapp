package org.gwtapp.core.client.data;


public interface ModelData {

	public Object get(String property);
	
	/*-
	public Map<String, Object> getProperties();


	public <X> X remove(String property);
	 */

	public Object set(String property, Object value);
}
