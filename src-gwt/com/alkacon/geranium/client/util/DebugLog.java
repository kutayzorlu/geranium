/*
 * This library is part of Geranium -
 * an open source UI library for GWT.
 *
 * Copyright (c) Alkacon Software GmbH (http://www.alkacon.com)-
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.alkacon.geranium.client.util;

import com.alkacon.geranium.client.ui.css.I_LayoutBundle;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * A basic debug log, to print messages into the client window.<p>
 */
public final class DebugLog extends Composite {

    /** Debug log displayed within the client window. */
    private static DebugLog m_debug;

    /** Global debugging flag. */
    private static final boolean DEBUG = false;

    /** The wrapped widget. */
    protected HTML m_html;

    /**
     * Constructor.<p>
     */
    @SuppressWarnings("unused")
    private DebugLog() {

        if (!DEBUG) {
            return;
        }
        m_html = new HTML();
        initWidget(m_html);
        Style style = getElement().getStyle();
        style.setWidth(200, Unit.PX);
        style.setHeight(500, Unit.PX);
        style.setPadding(10, Unit.PX);
        style.setOverflow(Overflow.AUTO);
        style.setBorderStyle(BorderStyle.SOLID);
        style.setBorderColor(I_LayoutBundle.INSTANCE.constants().css().borderColor());
        style.setBorderWidth(1, Unit.PX);
        style.setPosition(Position.FIXED);
        style.setTop(50, Unit.PX);
        style.setRight(50, Unit.PX);
        style.setBackgroundColor(I_LayoutBundle.INSTANCE.constants().css().backgroundColorDialog());
        style.setZIndex(10);
    }

    /**
     * Returns the debug log.<p>
     * 
     * @return the debug log
     */
    public static DebugLog getInstance() {

        if (m_debug == null) {
            m_debug = new DebugLog();
            if (DEBUG) {
                RootPanel.get().add(m_debug);
            }
        }
        return m_debug;
    }

    /**
     * Prints a new line into the log window by adding a p-tag including given text as HTML.<p>
     * 
     * @param text the text to print
     */
    public void printLine(String text) {

        if (!DEBUG) {
            return;
        }
        @SuppressWarnings("unused")
        Element child = DOM.createElement("p");
        child.setInnerHTML(text);
        m_html.getElement().insertFirst(child);

    }

    /**
     * Clears the debug log.<p>
     */
    @SuppressWarnings("unused")
    public void clear() {

        if (!DEBUG) {
            return;
        }
        m_html.setHTML("");
    }
}
