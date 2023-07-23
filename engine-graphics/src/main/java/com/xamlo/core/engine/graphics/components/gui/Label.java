package com.xamlo.core.engine.graphics.components.gui;

import com.xamlo.core.engine.graphics.api.gui.IColor;
import com.xamlo.core.engine.graphics.api.gui.IFont;
import com.xamlo.core.engine.graphics.api.gui.ILabel;


public class Label extends Widget implements ILabel {

    private String text;
    private IColor textColor;

    public Label() {
        super();
        this.text = "";
        this.textColor = new Color(0, 0, 0);
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setTextColor(IColor color) {
        this.textColor = color;
    }

    @Override
    public IColor getTextColor() {
        return this.textColor;
    }

    @Override
    public void setFont(IFont font) {
        super.setFont(font);
    }

    @Override
    public IFont getFont() {
        return super.getFont();
    }

    @Override
    public void init() {
        super.init();
        // Дополнительная инициализация для Label
    }

    @Override
    public void release() {
        super.release();
        // Дополнительные действия при освобождении ресурсов Label
    }

    @Override
    public void loadMesh() {
        super.loadMesh();
        // Загрузка собственной геометрии для Label
    }
}