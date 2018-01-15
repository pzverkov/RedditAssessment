package com.zve.redditassessment;

import com.zve.redditassessment.models.RedditComment;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Peter on 15.01.2018.
 */

class MockitoTest {

    @Test
    public void testDepth()  {
        RedditComment test = mock(RedditComment.class);
        when(test.getDepth()).thenReturn(1);
        assertEquals(test.getDepth(), 1);
    }

    @Test
    public void testBody()  {
        RedditComment comment = mock(RedditComment.class);
        when(comment.getBody()).thenThrow(new IllegalArgumentException("You can't get body " +
                "directly, please use delegated method"));
        try {
            comment.getBody();
            fail("Body method wrong usage");
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    // this test demonstrates how to return values independent of the input value
    @Test
    public void testReturnValueInDependentOnMethodParameter()  {
        Comparable<Integer> c= mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        //assert
        assertEquals(-1, c.compareTo(9));
    }

    @Test
    public void testReturnValueInDependentOnMethodParameter2()  {
        Comparable<RedditComment> c= mock(Comparable.class);
        when(c.compareTo(isA(RedditComment.class))).thenReturn(0);
        //assert
        assertEquals(0, c.compareTo(new RedditComment()));
    }
}
