package org.gwtapp.core.client.data;

public interface IsClonable<ItSelf> {
	<T extends ItSelf> T cloneTo();
}
