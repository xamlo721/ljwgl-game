package com.xamlo.core.engine.graphics.components.gui;

import com.xamlo.core.engine.graphics.api.gui.ButtonClickListener;
import com.xamlo.core.engine.graphics.api.gui.IColor;
import com.xamlo.core.engine.graphics.api.gui.IFont;
import com.xamlo.core.engine.graphics.api.gui.IPushButton;
import com.xamlo.engine.api.resources.ITextureResource;

public class PushButton extends Label implements IPushButton {

    private String text;
    private IColor textColor;
    private IFont font;
    private IColor buttonColor;
    private IColor buttonHoverColor;
    private IColor buttonPressedColor;
    private IColor buttonDisabledColor;
    private int buttonBorderSize;
    private IColor buttonBorderColor;
    private int buttonCornerRadius;
    private ITextureResource buttonIcon;
    private ButtonIconPosition buttonIconPosition;
    private int buttonIconSpacing;
    private ButtonAlignment buttonAlignment;
    private int buttonPadding;
    private int buttonMargin;
    private boolean buttonEnabled;
    private boolean buttonVisible;
    private boolean buttonPressed;
    private boolean buttonHovered;
    private boolean buttonFocused;
    private ButtonClickListener buttonClickListener;

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setTextColor(IColor color) {
        this.textColor = color;
    }

    @Override
    public IColor getTextColor() {
        return textColor;
    }

    @Override
    public void setFont(IFont font) {
        this.font = font;
    }

    @Override
    public IFont getFont() {
        return font;
    }

    @Override
    public void setButtonColor(IColor color) {
        this.buttonColor = color;
    }

    @Override
    public IColor getButtonColor() {
        return buttonColor;
    }

    @Override
    public void setButtonHoverColor(IColor color) {
        this.buttonHoverColor = color;
    }

    @Override
    public IColor getButtonHoverColor() {
        return buttonHoverColor;
    }

    @Override
    public void setButtonPressedColor(IColor color) {
        this.buttonPressedColor = color;
    }

    @Override
    public IColor getButtonPressedColor() {
        return buttonPressedColor;
    }

    @Override
    public void setButtonDisabledColor(IColor color) {
        this.buttonDisabledColor = color;
    }

    @Override
    public IColor getButtonDisabledColor() {
        return buttonDisabledColor;
    }

    @Override
    public void setButtonBorderSize(int size) {
        this.buttonBorderSize = size;
    }

    @Override
    public int getButtonBorderSize() {
        return buttonBorderSize;
    }

    @Override
    public void setButtonBorderColor(IColor color) {
        this.buttonBorderColor = color;
    }

    @Override
    public IColor getButtonBorderColor() {
        return buttonBorderColor;
    }

    @Override
    public void setButtonCornerRadius(int radius) {
        this.buttonCornerRadius = radius;
    }

    @Override
    public int getButtonCornerRadius() {
        return buttonCornerRadius;
    }

    @Override
    public void setButtonIcon(ITextureResource icon) {
        this.buttonIcon = icon;
    }

    @Override
    public ITextureResource getButtonIcon() {
        return buttonIcon;
    }

    @Override
    public void setButtonIconPosition(ButtonIconPosition position) {
        this.buttonIconPosition = position;
    }

    @Override
    public ButtonIconPosition getButtonIconPosition() {
        return buttonIconPosition;
    }

    @Override
    public void setButtonIconSpacing(int spacing) {
        this.buttonIconSpacing = spacing;
    }

    @Override
    public int getButtonIconSpacing() {
        return buttonIconSpacing;
    }

    @Override
    public void setButtonAlignment(ButtonAlignment alignment) {
        this.buttonAlignment = alignment;
    }

    @Override
    public ButtonAlignment getButtonAlignment() {
        return buttonAlignment;
    }

    @Override
    public void setButtonPadding(int padding) {
        this.buttonPadding = padding;
    }

    @Override
    public int getButtonPadding() {
        return buttonPadding;
    }

    @Override
    public void setButtonMargin(int margin) {
        this.buttonMargin = margin;
    }

    @Override
    public int getButtonMargin() {
        return buttonMargin;
    }

    @Override
    public void setButtonEnabled(boolean enabled) {
        this.buttonEnabled = enabled;
    }

    @Override
    public boolean isButtonEnabled() {
        return buttonEnabled;
    }

    @Override
    public void setButtonVisible(boolean visible) {
        this.buttonVisible = visible;
    }

    @Override
    public boolean isButtonVisible() {
        return buttonVisible;
    }

    @Override
    public void setButtonPressed(boolean pressed) {
        this.buttonPressed = pressed;
    }

    @Override
    public boolean isButtonPressed() {
        return buttonPressed;
    }

    @Override
    public void setButtonHovered(boolean hovered) {
        this.buttonHovered = hovered;
    }

    @Override
    public boolean isButtonHovered() {
        return buttonHovered;
    }

    @Override
    public void setButtonFocused(boolean focused) {
        this.buttonFocused = focused;
    }

    @Override
    public boolean isButtonFocused() {
        return buttonFocused;
    }

    @Override
    public void setButtonClickListener(ButtonClickListener listener) {
        this.buttonClickListener = listener;
    }

    @Override
    public ButtonClickListener getButtonClickListener() {
        return buttonClickListener;
    }

}