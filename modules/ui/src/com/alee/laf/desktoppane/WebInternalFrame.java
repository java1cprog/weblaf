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

package com.alee.laf.desktoppane;

import com.alee.managers.hotkey.HotkeyData;
import com.alee.managers.language.*;
import com.alee.managers.language.updaters.LanguageUpdater;
import com.alee.managers.log.Log;
import com.alee.managers.style.*;
import com.alee.painter.Paintable;
import com.alee.painter.Painter;
import com.alee.utils.swing.MouseButton;
import com.alee.utils.swing.extensions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.beans.PropertyVetoException;
import java.util.Map;

/**
 * {@link JInternalFrame} extension class.
 * It contains various useful methods to simplify core component usage.
 *
 * This component should never be used with a non-Web UIs as it might cause an unexpected behavior.
 * You could still use that component even if WebLaF is not your application LaF as this component will use Web-UI in any case.
 *
 * @author Mikle Garin
 * @see JInternalFrame
 * @see WebInternalFrameUI
 * @see InternalFramePainter
 */

public class WebInternalFrame extends JInternalFrame
        implements Styleable, Paintable, ShapeMethods, MarginMethods, PaddingMethods, EventMethods, LanguageMethods, LanguageEventMethods
{
    /**
     * Event properties.
     */
    public static final String CLOSABLE_PROPERTY = "closable";
    public static final String MAXIMIZABLE_PROPERTY = "maximizable";
    public static final String ICONABLE_PROPERTY = "iconable";

    /**
     * Creates a non-resizable, non-closable, non-maximizable, non-iconifiable {@code JInternalFrame} with no title.
     */
    public WebInternalFrame ()
    {
        this ( StyleId.auto );
    }

    /**
     * Creates a non-resizable, non-closable, non-maximizable, non-iconifiable {@code WebInternalFrame} with the specified title.
     * Note that passing in a {@code null} {@code title} results in unspecified behavior and possibly an exception.
     *
     * @param title the non-{@code null} {@code String} to display in the title bar
     */
    public WebInternalFrame ( final String title )
    {
        this ( StyleId.auto, title );
    }

    /**
     * Creates a non-closable, non-maximizable, non-iconifiable {@code WebInternalFrame} with the specified title and resizability.
     *
     * @param title     the {@code String} to display in the title bar
     * @param resizable if {@code true}, the internal frame can be resized
     */
    public WebInternalFrame ( final String title, final boolean resizable )
    {
        this ( StyleId.auto, title, resizable );
    }

    /**
     * Creates a non-maximizable, non-iconifiable {@code WebInternalFrame} with the specified title, resizability, and closability.
     *
     * @param title     the {@code String} to display in the title bar
     * @param resizable if {@code true}, the internal frame can be resized
     * @param closable  if {@code true}, the internal frame can be closed
     */
    public WebInternalFrame ( final String title, final boolean resizable, final boolean closable )
    {
        this ( StyleId.auto, title, resizable, closable );
    }

    /**
     * Creates a non-iconifiable {@code WebInternalFrame} with the specified title, resizability, closability, and maximizability.
     *
     * @param title       the {@code String} to display in the title bar
     * @param resizable   if {@code true}, the internal frame can be resized
     * @param closable    if {@code true}, the internal frame can be closed
     * @param maximizable if {@code true}, the internal frame can be maximized
     */
    public WebInternalFrame ( final String title, final boolean resizable, final boolean closable, final boolean maximizable )
    {
        this ( StyleId.auto, title, resizable, closable, maximizable );
    }

    /**
     * Creates a {@code WebInternalFrame} with the specified title, resizability, closability, maximizability, and iconifiability.
     *
     * @param title       the {@code String} to display in the title bar
     * @param resizable   if {@code true}, the internal frame can be resized
     * @param closable    if {@code true}, the internal frame can be closed
     * @param maximizable if {@code true}, the internal frame can be maximized
     * @param iconifiable if {@code true}, the internal frame can be iconified
     */
    public WebInternalFrame ( final String title, final boolean resizable, final boolean closable, final boolean maximizable,
                              final boolean iconifiable )
    {
        this ( StyleId.auto, title, resizable, closable, maximizable, iconifiable );
    }

    /**
     * Creates a non-resizable, non-closable, non-maximizable, non-iconifiable {@code JInternalFrame} with no title.
     *
     * @param id style ID
     */
    public WebInternalFrame ( final StyleId id )
    {
        this ( id, "", false, false, false, false );
    }

    /**
     * Creates a non-resizable, non-closable, non-maximizable, non-iconifiable {@code WebInternalFrame} with the specified title.
     * Note that passing in a {@code null} {@code title} results in unspecified behavior and possibly an exception.
     *
     * @param id    style ID
     * @param title the non-{@code null} {@code String} to display in the title bar
     */
    public WebInternalFrame ( final StyleId id, final String title )
    {
        this ( id, title, false, false, false, false );
    }

    /**
     * Creates a non-closable, non-maximizable, non-iconifiable {@code WebInternalFrame} with the specified title and resizability.
     *
     * @param id        style ID
     * @param title     the {@code String} to display in the title bar
     * @param resizable if {@code true}, the internal frame can be resized
     */
    public WebInternalFrame ( final StyleId id, final String title, final boolean resizable )
    {
        this ( id, title, resizable, false, false, false );
    }

    /**
     * Creates a non-maximizable, non-iconifiable {@code WebInternalFrame} with the specified title, resizability, and closability.
     *
     * @param id        style ID
     * @param title     the {@code String} to display in the title bar
     * @param resizable if {@code true}, the internal frame can be resized
     * @param closable  if {@code true}, the internal frame can be closed
     */
    public WebInternalFrame ( final StyleId id, final String title, final boolean resizable, final boolean closable )
    {
        this ( id, title, resizable, closable, false, false );
    }

    /**
     * Creates a non-iconifiable {@code WebInternalFrame} with the specified title, resizability, closability, and maximizability.
     *
     * @param id          style ID
     * @param title       the {@code String} to display in the title bar
     * @param resizable   if {@code true}, the internal frame can be resized
     * @param closable    if {@code true}, the internal frame can be closed
     * @param maximizable if {@code true}, the internal frame can be maximized
     */
    public WebInternalFrame ( final StyleId id, final String title, final boolean resizable, final boolean closable,
                              final boolean maximizable )
    {
        this ( id, title, resizable, closable, maximizable, false );
    }

    /**
     * Creates a {@code WebInternalFrame} with the specified title, resizability, closability, maximizability, and iconifiability.
     *
     * @param id          style ID
     * @param title       the {@code String} to display in the title bar
     * @param resizable   if {@code true}, the internal frame can be resized
     * @param closable    if {@code true}, the internal frame can be closed
     * @param maximizable if {@code true}, the internal frame can be maximized
     * @param iconifiable if {@code true}, the internal frame can be iconified
     */
    public WebInternalFrame ( final StyleId id, final String title, final boolean resizable, final boolean closable,
                              final boolean maximizable, final boolean iconifiable )
    {
        super ( title, resizable, closable, maximizable, iconifiable );
        setStyleId ( id );
    }

    @Override
    public void setIcon ( final boolean b )
    {
        try
        {
            super.setIcon ( b );
        }
        catch ( final PropertyVetoException e )
        {
            Log.error ( this, e );
        }
    }

    /**
     * Safely hides internal frame.
     */
    public void close ()
    {
        try
        {
            setClosed ( true );
        }
        catch ( final PropertyVetoException e )
        {
            Log.error ( this, e );
        }
    }

    /**
     * Safely displays internal frame.
     */
    public void open ()
    {
        try
        {
            setClosed ( false );
            setVisible ( true );
        }
        catch ( final PropertyVetoException e )
        {
            Log.error ( this, e );
        }
    }

    @Override
    public StyleId getDefaultStyleId ()
    {
        return StyleId.internalframe;
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
    public MouseAdapter onMousePress ( final MouseEventRunnable runnable )
    {
        return EventMethodsImpl.onMousePress ( this, runnable );
    }

    @Override
    public MouseAdapter onMousePress ( final MouseButton mouseButton, final MouseEventRunnable runnable )
    {
        return EventMethodsImpl.onMousePress ( this, mouseButton, runnable );
    }

    @Override
    public MouseAdapter onMouseEnter ( final MouseEventRunnable runnable )
    {
        return EventMethodsImpl.onMouseEnter ( this, runnable );
    }

    @Override
    public MouseAdapter onMouseExit ( final MouseEventRunnable runnable )
    {
        return EventMethodsImpl.onMouseExit ( this, runnable );
    }

    @Override
    public MouseAdapter onMouseDrag ( final MouseEventRunnable runnable )
    {
        return EventMethodsImpl.onMouseDrag ( this, runnable );
    }

    @Override
    public MouseAdapter onMouseDrag ( final MouseButton mouseButton, final MouseEventRunnable runnable )
    {
        return EventMethodsImpl.onMouseDrag ( this, mouseButton, runnable );
    }

    @Override
    public MouseAdapter onMouseClick ( final MouseEventRunnable runnable )
    {
        return EventMethodsImpl.onMouseClick ( this, runnable );
    }

    @Override
    public MouseAdapter onMouseClick ( final MouseButton mouseButton, final MouseEventRunnable runnable )
    {
        return EventMethodsImpl.onMouseClick ( this, mouseButton, runnable );
    }

    @Override
    public MouseAdapter onDoubleClick ( final MouseEventRunnable runnable )
    {
        return EventMethodsImpl.onDoubleClick ( this, runnable );
    }

    @Override
    public MouseAdapter onMenuTrigger ( final MouseEventRunnable runnable )
    {
        return EventMethodsImpl.onMenuTrigger ( this, runnable );
    }

    @Override
    public KeyAdapter onKeyType ( final KeyEventRunnable runnable )
    {
        return EventMethodsImpl.onKeyType ( this, runnable );
    }

    @Override
    public KeyAdapter onKeyType ( final HotkeyData hotkey, final KeyEventRunnable runnable )
    {
        return EventMethodsImpl.onKeyType ( this, hotkey, runnable );
    }

    @Override
    public KeyAdapter onKeyPress ( final KeyEventRunnable runnable )
    {
        return EventMethodsImpl.onKeyPress ( this, runnable );
    }

    @Override
    public KeyAdapter onKeyPress ( final HotkeyData hotkey, final KeyEventRunnable runnable )
    {
        return EventMethodsImpl.onKeyPress ( this, hotkey, runnable );
    }

    @Override
    public KeyAdapter onKeyRelease ( final KeyEventRunnable runnable )
    {
        return EventMethodsImpl.onKeyRelease ( this, runnable );
    }

    @Override
    public KeyAdapter onKeyRelease ( final HotkeyData hotkey, final KeyEventRunnable runnable )
    {
        return EventMethodsImpl.onKeyRelease ( this, hotkey, runnable );
    }

    @Override
    public FocusAdapter onFocusGain ( final FocusEventRunnable runnable )
    {
        return EventMethodsImpl.onFocusGain ( this, runnable );
    }

    @Override
    public FocusAdapter onFocusLoss ( final FocusEventRunnable runnable )
    {
        return EventMethodsImpl.onFocusLoss ( this, runnable );
    }

    @Override
    public MouseAdapter onDragStart ( final int shift, final MouseEventRunnable runnable )
    {
        return EventMethodsImpl.onDragStart ( this, shift, runnable );
    }

    @Override
    public MouseAdapter onDragStart ( final int shift, final MouseButton mouseButton, final MouseEventRunnable runnable )
    {
        return EventMethodsImpl.onDragStart ( this, shift, mouseButton, runnable );
    }

    @Override
    public String getLanguage ()
    {
        return UILanguageManager.getComponentKey ( this );
    }

    @Override
    public void setLanguage ( final String key, final Object... data )
    {
        UILanguageManager.registerComponent ( this, key, data );
    }

    @Override
    public void updateLanguage ( final Object... data )
    {
        UILanguageManager.updateComponent ( this, data );
    }

    @Override
    public void updateLanguage ( final String key, final Object... data )
    {
        UILanguageManager.updateComponent ( this, key, data );
    }

    @Override
    public void removeLanguage ()
    {
        UILanguageManager.unregisterComponent ( this );
    }

    @Override
    public boolean isLanguageSet ()
    {
        return UILanguageManager.isRegisteredComponent ( this );
    }

    @Override
    public void setLanguageUpdater ( final LanguageUpdater updater )
    {
        UILanguageManager.registerLanguageUpdater ( this, updater );
    }

    @Override
    public void removeLanguageUpdater ()
    {
        UILanguageManager.unregisterLanguageUpdater ( this );
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
     * @return the {@link WebInternalFrameUI} object that renders this component
     */
    @Override
    public WebInternalFrameUI getUI ()
    {
        return ( WebInternalFrameUI ) super.getUI ();
    }

    /**
     * Sets the LaF object that renders this component.
     *
     * @param ui {@link WebInternalFrameUI}
     */
    public void setUI ( final WebInternalFrameUI ui )
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
}