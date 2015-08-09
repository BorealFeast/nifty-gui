package de.lessvoid.nifty.node;

import de.lessvoid.nifty.api.NiftyLayout;
import de.lessvoid.nifty.api.types.Point;
import de.lessvoid.nifty.api.types.Rect;
import de.lessvoid.nifty.api.types.Size;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
@RunWith(EasyMockRunner.class)
public class AbstractLayoutNodeImplTest {
  private AbstractLayoutNodeImpl testInstance;

  @Mock
  private NiftyLayout layout;

  @Before
  public void prepare() {
    testInstance = EasyMock.createMockBuilder(AbstractLayoutNodeImpl.class)
        .addMockedMethod("measureInternal")
        .addMockedMethod("arrangeInternal")
        .withConstructor()
        .createMock();
  }

  @Test
  public void testActivate() throws Exception {
    EasyMock.replay(layout, testInstance);
    testInstance.activate(layout);
    EasyMock.verify(layout, testInstance);
  }

  @Test(expected = IllegalStateException.class)
  public void testActivateException() throws Exception {
    EasyMock.replay(layout, testInstance);
    testInstance.activate(layout);
    testInstance.activate(layout);
    EasyMock.verify(layout, testInstance);
  }

  @Test
  public void testIsMeasureValid() throws Exception {
    assertFalse(testInstance.isMeasureValid());
    EasyMock.replay(layout, testInstance);
    testInstance.activate(layout);
    EasyMock.verify(layout, testInstance);
    assertFalse(testInstance.isMeasureValid());
  }

  @Test
  public void testIsArrangeValid() throws Exception {
    assertFalse(testInstance.isArrangeValid());
    EasyMock.replay(layout, testInstance);
    testInstance.activate(layout);
    EasyMock.verify(layout, testInstance);
    assertFalse(testInstance.isArrangeValid());
  }

  @Test
  public void testInvalidateMeasure() throws Exception {
    /* Setup Test */
    EasyMock.expect(testInstance.measureInternal(Size.INFINITE)).andReturn(Size.ZERO);
    layout.reportMeasureInvalid(testInstance);
    EasyMock.expectLastCall().once();
    EasyMock.replay(layout, testInstance);

    /* Execute Test */
    testInstance.activate(layout);
    testInstance.measure(Size.INFINITE);

    assertTrue(testInstance.isMeasureValid());
    testInstance.invalidateMeasure();
    EasyMock.verify(layout, testInstance);
    assertFalse(testInstance.isMeasureValid());
  }

  @Test
  public void testInvalidateArrange() throws Exception {
    /* Setup Test */
    Size tempSize = new Size(10, 10);
    Rect tempRect = new Rect(new Point(0, 0), tempSize);
    EasyMock.expect(testInstance.measureInternal(tempSize)).andReturn(tempSize);
    testInstance.arrangeInternal(tempRect);
    EasyMock.expectLastCall().once();
    layout.reportArrangeInvalid(testInstance);
    EasyMock.expectLastCall().once();
    EasyMock.replay(layout, testInstance);

    /* Execute Test */
    testInstance.activate(layout);
    testInstance.measure(tempSize);
    testInstance.arrange(tempRect);

    assertTrue(testInstance.isArrangeValid());
    testInstance.invalidateArrange();

    EasyMock.verify(layout, testInstance);
    assertFalse(testInstance.isArrangeValid());
  }

  @Test
  public void testGetDesiredSize() throws Exception {
    /* Setup Test */
    Size tempSize = new Size(10, 10);
    EasyMock.expect(testInstance.measureInternal(Size.INFINITE)).andReturn(tempSize);
    EasyMock.replay(layout, testInstance);

    /* Execute Test */
    testInstance.activate(layout);
    testInstance.measure(Size.INFINITE);

    assertTrue(testInstance.isMeasureValid());
    assertEquals(tempSize, testInstance.getDesiredSize());
    EasyMock.verify(layout, testInstance);
  }

  @Test
  public void testGetArrangedRect() throws Exception {
    /* Setup Test */
    Size tempSize = new Size(10, 10);
    Rect tempRect = new Rect(new Point(0, 0), tempSize);
    EasyMock.expect(testInstance.measureInternal(tempSize)).andReturn(tempSize);
    testInstance.arrangeInternal(tempRect);
    EasyMock.expectLastCall().once();
    EasyMock.replay(layout, testInstance);

    /* Execute Test */
    testInstance.activate(layout);
    testInstance.measure(tempSize);
    testInstance.arrange(tempRect);

    assertTrue(testInstance.isArrangeValid());
    assertEquals(tempRect, testInstance.getArrangedRect());

    EasyMock.verify(layout, testInstance);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetLayoutException() throws Exception {
    EasyMock.replay(layout, testInstance);
    testInstance.getLayout();
    EasyMock.verify(layout, testInstance);
  }

  @Test
  public void testGetLayout() throws Exception {
    EasyMock.replay(layout, testInstance);
    testInstance.activate(layout);
    assertEquals(layout, testInstance.getLayout());
    EasyMock.verify(layout, testInstance);
  }
}