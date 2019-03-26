package skywolf46.ServerEdit.Modules.CraftScript.Util;
@FunctionalInterface
public interface TripleConsumer<F,S,T> {

    void consume(F first, S second, T third);
}
