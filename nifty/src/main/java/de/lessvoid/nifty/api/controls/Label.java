package de.lessvoid.nifty.api.controls;

import de.lessvoid.nifty.api.HAlign;
import de.lessvoid.nifty.api.NiftyCanvas;
import de.lessvoid.nifty.api.NiftyCanvasPainter;
import de.lessvoid.nifty.api.NiftyColor;
import de.lessvoid.nifty.api.NiftyFont;
import de.lessvoid.nifty.api.NiftyMinSizeCallback;
import de.lessvoid.nifty.api.NiftyNode;
import de.lessvoid.nifty.api.VAlign;
import de.lessvoid.nifty.internal.math.Vec2;

public class Label extends NiftyAbstractControl implements NiftyMinSizeCallback {
  private NiftyColor textColor = NiftyColor.WHITE();
  private String text;
  private NiftyFont font;

  public void init(final NiftyNode niftyNode) {
    super.init(niftyNode);
    niftyNode.addCanvasPainter(new LabelCanvasPainter());
    niftyNode.enableMinSize(this);
  }

  /**
   * Change the Label text.
   *
   * @param text new text
   */
  public void setText(final String text) {
    this.text = text;
  }

  /**
   * Get the Label text.
   *
   * @return label text
   */
  public String getText() {
    return text;
  }

  /**
   * Set the Label color.
   *
   * @param color the color
   */
  public void setColor(final NiftyColor color) {
    this.textColor = color;
  }

  /**
   * Get the current Label color.
   *
   * @return the current color of the label
   */
  public NiftyColor getColor() {
    return textColor;
  }

  /**
   * Set the NiftyFont to use for this label.
   *
   * @param font the font to use
   */
  public void setFont(final NiftyFont font) {
    this.font = font;
  }

  /**
   * Get the NiftyFont this label uses.
   *
   * @return the NiftyFont
   */
  public NiftyFont getFont() {
    return font;
  }

  /**
   * Set the horizontal alignment.
   * @param halign horizontal alignment
   */
  public void setHAlign(final HAlign halign) {
  }

  /**
   * Set the vertical alignment.
   * @param valign vertical alignment
   */
  public void setVAlign(final VAlign valign) {
  }

  @Override
  public Vec2 calculateMinSize(final NiftyNode niftyNode) {
    return new Vec2(font.getWidth(text), font.getHeight());
  }

  private class LabelCanvasPainter implements NiftyCanvasPainter {
    @Override
    public void paint(final NiftyNode node, final NiftyCanvas canvas) {
      canvas.setTextColor(textColor);
      canvas.text(font, 0, 0, text);
    }
  }
}
