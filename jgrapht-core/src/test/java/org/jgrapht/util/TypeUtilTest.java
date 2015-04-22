
public class TypeUtilTest
    extends TestCase
{
    //~ Methods ----------------------------------------------------------------
    TypeUtil<String> string_type = null;
    TypeUtil<Integer> int_type = null;
    TypeUtil<Boolean> bool_type = null;


    public void testuncheckedCast(){
        string_type.uncheckedCast("String", string_type);
    }

}
