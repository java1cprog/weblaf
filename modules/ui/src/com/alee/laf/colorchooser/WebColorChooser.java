/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.laf.colorchooser;

import com.alee.managers.language.DictionaryListener;
import com.alee.managers.language.LanguageEventMethods;
import com.alee.managers.language.LanguageListener;
import com.alee.managers.language.UILanguageManager;
import com.alee.managers.style.*;
import com.alee.painter.Paintable;
import com.alee.painter.Painter;
import com.alee.utils.swing.DialogOptions;

import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import java.awt.*;
import java.util.Map;

/**
 * {@link JColorChooser} extension class.
 * It contains various useful methods to simplify core component usage.
 *
 * This component should never be used with a non-Web UIs as it might cause an unexpected behavior.
 * You could still use that component even if WebLaF is not your application LaF as this component will use Web-UI in any case.
 *
 * @author Mikle Garin
 * @see JColorChooser
 * @see WebColorChooserUI
 * @see ColorChooserPainter
 */

public class WebColorChooser extends JColorChooser
        implements Styleable, Paintable, ShapeMethods, MarginMethods, PaddingMethods, LanguageEventMethods, DialogOptions
{
    /**
     * Constructs new color chooser.
     */
    public WebColorChooser ()
    {
        this ( StyleId.auto );
    }

    /**
     * Constructs new color chooser.
     *
     * @param initialColor initially selected color
     */
    public WebColorChooser ( final Color initialColor )
    {
        this ( StyleId.auto, initialColor );
    }

    /**
     * Constructs new color chooser.
     *
     * @param model color selection model
     */
    public WebColorChooser ( final ColorSelectionModel model )
    {
        this ( StyleId.auto, model );
    }

    /**
     * Constructs new color chooser.
     *
     * @param id style ID
     */
    public WebColorChooser ( final StyleId id )
    {
        this ( id, Color.WHITE );
    }

    /**
     * Constructs new color chooser.
     *
     * @param id           style ID
     * @param initialColor initially selected color
     */
    public WebColorChooser ( final StyleId id, final Color initialColor )
    {
        this ( id, new DefaultColorSelectionModel ( initialColor ) );
    }

    /**
     * Constructs new color chooser.
     *
     * @param id    style ID
     * @param model color selection model
     */
    public WebColorChooser ( final StyleId id, final ColorSelectionModel model )
    {
        super ( model );
        setStyleId ( id );
    }

    /**
     * Returns whether or not buttons panel should be displayed.
     *
     * @return {@code true} if buttons panel should be displayed, {@code false} otherwise
     */
    public boolean isShowButtonsPanel ()
    {
        return getUI ().isShowButtonsPanel ();
    }

    /**
     * Sets whether or not buttons panel should be displayed.
     *
     * @param display whether or not buttons panel should be displayed
     */
    public void setShowButtonsPanel ( final boolean display )
    {
        getUI ().setShowButtonsPanel ( display );
    }

    /**
     * Returns whether or not web-only colors should be displayed.
     *
     * @return {@code true} if web-only colors should be displayed, {@code false} otherwise
     */
    public boolean isWebOnlyColors ()
    {
        return getUI ().isWebOnlyColors ();
    }

    /**
     * Sets whether or not web-only colors should be displayed.
     *
     * @param webOnly whether or not web-only colors should be displayed
     */
    public void setWebOnlyColors ( final boolean webOnly )
    {
        getUI ().setWebOnlyColors ( webOnly );
    }

    /**
     * Returns previously selected color.
     *
     * @return previously selected color
     */
    public Color getPreviousColor ()
    {
        return getUI ().getPreviousColor ();
    }

    /**
     * Sets previously selected color.
     *
     * @param previous previously selected color
     */
    public void setPreviousColor ( final Color previous )
    {
        getUI ().setPreviousColor ( previous );
    }

    /**
     * Resets color chooser result.
     */
    public void resetResult ()
    {
        getUI ().resetResult ();
    }

    /**
     * Sets color chooser result.
     *
     * @param result color chooser result
     */
    public void setResult ( final int result )
    {
        getUI ().setResult ( result );
    }

    /**
     * Returns color chooser result.
     *
     * @return color chooser result
     */
    public int getResult ()
    {
        return getUI ().getResult ();
    }

    /**
     * Adds color chooser listener.
     *
     * @param listener color chooser listener to add
     */
    public void addColorChooserListener ( final ColorChooserListener listener )
    {
        getUI ().addColorChooserListener ( listener );
    }

    /**
     * Removes color chooser listener.
     *
     * @param listener color chooser listener to remove
     */
    public void removeColorChooserListener ( final ColorChooserListener listener )
    {
        getUI ().removeColorChooserListener ( listener );
    }

    @Override
    public StyleId getDefaultStyleId ()
    {
        return StyleId.colorchooser;
    }

    @Override
    public StyleId getStyleId ()
    {
        return StyleManager.getStyleId ( this );
    }

    @Override
    public StyleId setStyleId ( final StyleId id )
    {
        return StyleManager.setStyleId ( this, id );
    }

    @Override
    public StyleId resetStyleId ()
    {
        return StyleManager.resetStyleId ( this );
    }

    @Override
    public Skin getSkin ()
    {
        return StyleManager.getSkin ( this );
    }

    @Override
    public Skin setSkin ( final Skin skin )
    {
        return StyleManager.setSkin ( this, skin );
    }

    @Override
    public Skin setSkin ( final Skin skin, final boolean recursively )
    {
        return StyleManager.setSkin ( this, skin, recursively );
    }

    @Override
    public Skin resetSkin ()
    {
        return StyleManager.resetSkin ( this );
    }

    @Override
    public void addStyleListener ( final StyleListener listener )
    {
        StyleManager.addStyleListener ( this, listener );
    }

    @Override
    public void removeStyleListener ( final StyleListener listener )
    {
        StyleManager.removeStyleListener ( this, listener );
    }

    @Override
    public Map<String, Painter> getCustomPainters ()
    {
        return StyleManager.getCustomPainters ( this );
    }

    @Override
    public Painter getCustomPainter ()
    {
        return StyleManager.getCustomPainter ( this );
    }

    @Override
    public Painter getCustomPainter ( final String id )
    {
        return StyleManager.getCustomPainter ( this, id );
    }

    @Override
    public Painter setCustomPainter ( final Painter painter )
    {
        return StyleManager.setCustomPainter ( this, painter );
    }

    @Override
    public Painter setCustomPainter ( final String id, final Painter painter )
    {
        return StyleManager.setCustomPainter ( this, id, painter );
    }

    @Override
    public boolean resetPainter ()
    {
        return StyleManager.resetPainter ( this );
    }

    @Override
    public Shape getShape ()
    {
        return ShapeMethodsImpl.getShape ( this );
    }

    @Override
    public Insets getMargin ()
    {
        return MarginMethodsImpl.getMargin ( this );
    }

    @Override
    public void setMargin ( final int margin )
    {
        MarginMethodsImpl.setMargin ( this, margin );
    }

    @Override
    public void setMargin ( final int top, final int left, final int bottom, final int right )
    {
        MarginMethodsImpl.setMargin ( this, top, left, bottom, right );
    }

    @Override
    public void setMargin ( final Insets margin )
    {
        MarginMethodsImpl.setMargin ( this, margin );
    }

    @Override
    public Insets getPadding ()
    {
        return PaddingMethodsImpl.getPadding ( this );
    }

    @Override
    public void setPadding ( final int padding )
    {
        PaddingMethodsImpl.setPadding ( this, padding );
    }

    @Override
    public void setPadding ( final int top, final int left, final int bottom, final int right )
    {
        PaddingMethodsImpl.setPadding ( this, top, left, bottom, right );
    }

    @Override
    public void setPadding ( final Insets padding )
    {
        PaddingMethodsImpl.setPadding ( this, padding );
    }

    @Override
    public void addLanguageListener ( final LanguageListener listener )
    {
        UILanguageManager.addLanguageListener ( getRootPane (), listener );
    }

    @Override
    public void removeLanguageListener ( final LanguageListener listener )
    {
        UILanguageManager.removeLanguageListener ( getRootPane (), listener );
    }

    @Override
    public void removeLanguageListeners ()
    {
        UILanguageManager.removeLanguageListeners ( getRootPane () );
    }

    @Override
    public void addDictionaryListener ( final DictionaryListener listener )
    {
        UILanguageManager.addDictionaryListener ( getRootPane (), listener );
    }

    @Override
    public void removeDictionaryListener ( final DictionaryListener listener )
    {
        UILanguageManager.removeDictionaryListener ( getRootPane (), listener );
    }

    @Override
    public void removeDictionaryListeners ()
    {
        UILanguageManager.removeDictionaryListeners ( getRootPane () );
    }

    /**
     * Returns the look and feel (LaF) object that renders this component.
     *
     * @return the {@link WColorChooserUI} object that renders this component
     */
    @Override
    public WColorChooserUI getUI ()
    {
        return ( WColorChooserUI ) super.getUI ();
    }

    /**
     * Sets the LaF object that renders this component.
     *
     * @param ui {@link WColorChooserUI}
     */
    public void setUI ( final WColorChooserUI ui )
    {
        super.setUI ( ui );
    }

    @Override
    public void updateUI ()
    {
        StyleManager.getDescriptor ( this ).updateUI ( this );
    }

    @Override
    public String getUIClassID ()
    {
        return StyleManager.getDescriptor ( this ).getUIClassId ();
    }

    /**
     * Displays modal color chooser dialog and returns selected color.
     *
     * @param parent parent component
     * @return selected color
     */
    public static Color showDialog ( final Component parent )
    {
        return showDialog ( parent, null, null );
    }

    /**
     * Displays modal color chooser dialog and returns selected color.
     *
     * @param parent parent component
     * @param title  dialog title
     * @return selected color
     */
    public static Color showDialog ( final Component parent, final String title )
    {
        return showDialog ( parent, title, null );
    }

    /**
     * Displays modal color chooser dialog and returns selected color.
     *
     * @param parent parent component
     * @param color  initially selected color
     * @return selected color
     */
    public static Color showDialog ( final Component parent, final Color color )
    {
        return showDialog ( parent, null, color );
    }

    /**
     * Displays modal color chooser dialog and returns selected color.
     *
     * @param parent parent component
     * @param title  dialog title
     * @param color  initially selected color
     * @return selected color
     */
    public static Color showDialog ( final Component parent, final String title, final Color color )
    {
        // Creating new dialog
        final WebColorChooserDialog wcc = new WebColorChooserDialog ( parent, title );

        // Initial color
        if ( color != null )
        {
            wcc.setColor ( color );
        }

        // Showing color chooser dialog
        // This will block further method execution until dialog is closed
        wcc.setVisible ( true );

        // Returning selected color
        return wcc.getResult () == OK_OPTION ? wcc.getColor () : null;
    }
}