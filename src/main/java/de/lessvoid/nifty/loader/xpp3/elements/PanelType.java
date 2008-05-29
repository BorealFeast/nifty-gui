package de.lessvoid.nifty.loader.xpp3.elements;

import java.util.Map;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.NiftyInputControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.loader.xpp3.elements.helper.NiftyCreator;
import de.lessvoid.nifty.loader.xpp3.elements.helper.StyleHandler;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.TimeProvider;

/**
 * PanelType.
 * @author void
 */
public class PanelType extends ElementType {

  /**
   * create element.
   * @param parent parent parent
   * @param nifty nifty
   * @param screen screen
   * @param registeredEffects registeredEffects
   * @param registeredControls registeredControls
   * @param styleHandler styleHandler
   * @param time time
   * @param inputControl controlController
   * @param screenController screenController
   * @return element
   */
  public Element createElement(
      final Element parent,
      final Nifty nifty,
      final Screen screen,
      final Map < String, RegisterEffectType > registeredEffects,
      final Map < String, RegisterControlDefinitionType > registeredControls,
      final StyleHandler styleHandler,
      final TimeProvider time,
      final NiftyInputControl inputControl, final ScreenController screenController) {
    Element element = NiftyCreator.createPanel(
        getAttributes().getId(),
        nifty,
        screen,
        parent,
        false);
    super.addElementAttributes(
        element,
        screen,
        nifty,
        registeredEffects,
        registeredControls,
        styleHandler,
        time,
        inputControl, screenController);
    parent.add(element);
    return element;
  }
}
