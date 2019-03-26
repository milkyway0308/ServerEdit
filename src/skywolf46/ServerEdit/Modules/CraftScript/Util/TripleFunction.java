package skywolf46.ServerEdit.Modules.CraftScript.Util;
@FunctionalInterface
public interface TripleFunction<F,S,T,R> {

    R apply(F first,S second,T third);
}
