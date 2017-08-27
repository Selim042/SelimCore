package selim.core.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PluginsLoadedEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	@Override
	public HandlerList getHandlers() {
		return PluginsLoadedEvent.handlers;
	}

	public static HandlerList getHandlerList() {
		return PluginsLoadedEvent.handlers;
	}
}
