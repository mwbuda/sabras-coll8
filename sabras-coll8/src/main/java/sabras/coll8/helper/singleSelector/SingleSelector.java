package sabras.coll8.helper.singleSelector;

import java.util.function.Function;

public interface SingleSelector<X> 
extends Function<Iterable<X>, X>
{

}
