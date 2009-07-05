package org.gwtapp.core.client.data;



public interface ModelData {
	
	public <X> X get(String property);

	/*-
	public Map<String, Object> getProperties();

	public Collection<String> getPropertyNames();

	public <X> X remove(String property);
	*/

	public <X> X set(String property, X value);
}
