
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.io.*;

public class InputReaderTests {

  public static InputReader getReader(String s) {
    if (s == null) return null;
    InputStream is = new ByteArrayInputStream(s.getBytes());
    return new InputReader(is);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalBufferSz1() {
    new InputReader( 0 );
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalBufferSz2() {
    new InputReader( -1 );
  }

  @Test
  public void testReadInt() throws IOException {

    // Test non negative values
    String str = " 0  1 2    3  \n 55 345 888234\n";
    InputReader in = getReader(str);

    assertEquals( 0, in.readInt() );
    assertEquals( 1, in.readInt() );
    assertEquals( 2, in.readInt() );
    assertEquals( 3, in.readInt() );
    assertEquals( 55, in.readInt() );
    assertEquals( 345, in.readInt() );
    assertEquals( 888234, in.readInt() );

    // Test negative values
    str = "  -0 -1  -2    -3  \n -55 -345 -888234\n";
    in = getReader(str);

    assertEquals( 0, in.readInt() );
    assertEquals( -1, in.readInt() );
    assertEquals( -2, in.readInt() );
    assertEquals( -3, in.readInt() );
    assertEquals( -55, in.readInt() );
    assertEquals( -345, in.readInt() );
    assertEquals( -888234, in.readInt() );

  }

  @Test
  public void testReadIntExtremes() throws IOException {

    // Test integer maximum and minimum values
    String str = Integer.MAX_VALUE + " " + Integer.MIN_VALUE;
    InputReader in = getReader(str);

    assertEquals( Integer.MAX_VALUE, in.readInt() );
    assertEquals( Integer.MIN_VALUE, in.readInt() );

    str = (Integer.MAX_VALUE-1) + " " + (Integer.MIN_VALUE+1);
    in = getReader(str);
    
    // Test integer maximum and minimum values +- 1
    assertEquals( Integer.MAX_VALUE-1, in.readInt() );
    assertEquals( Integer.MIN_VALUE+1, in.readInt() );

  }

}

