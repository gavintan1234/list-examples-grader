import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

class ContainsA implements StringChecker {
  public boolean checkString(String s) {
    return s.contains("a");
  }
}

public class TestListExamples {
  @Test(timeout = 100)
  public void testFilter(){
    List<String> input = Arrays.asList(new String[]{"average", "power", "car", "window"});
    List<String> expected = Arrays.asList(new String[]{"average", "car"});

    assertArrayEquals(expected.toArray(), (ListExamples.filter(input, new ContainsA())).toArray());
  }

  @Test(timeout = 100)
  public void testFilterDuplicates(){
    List<String> input = Arrays.asList(new String[]{"average", "average", "average", "window"});
    List<String> expected = Arrays.asList(new String[]{"average", "average", "average"});

    assertArrayEquals(expected.toArray(), (ListExamples.filter(input, new ContainsA())).toArray());
  }

  @Test(timeout = 100)
  public void testMerge(){
    List<String> input1 = Arrays.asList(new String[]{"a", "c", "p", "w"});
    List<String> input2 = Arrays.asList(new String[]{"b", "e", "f", "t"});
    List<String> expected = Arrays.asList(new String[]{"a", "b", "c", "e", "f", "p", "t", "w"});

    assertArrayEquals(expected.toArray(), (ListExamples.merge(input1, input2)).toArray());
  }

  @Test(timeout = 100)
  public void testMergeDuplicates(){
    List<String> input1 = Arrays.asList(new String[]{"a", "b", "b", "e"});
    List<String> input2 = Arrays.asList(new String[]{"b", "e", "e", "t"});
    List<String> expected = Arrays.asList(new String[]{"a", "b", "b", "b", "e", "e", "e", "t"});

    assertArrayEquals(expected.toArray(), (ListExamples.merge(input1, input2)).toArray());
  }
}
