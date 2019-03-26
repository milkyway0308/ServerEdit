package skywolf46.ServerEdit.Modules.CraftScript.Data.Classes.Events;

import skywolf46.ServerEdit.Data.Events.ServerEditEvent;

public abstract class LoopableEditEvent extends ServerEditEvent {
    public abstract int getLoopDelay();
    public abstract boolean doNextLoop();
}
