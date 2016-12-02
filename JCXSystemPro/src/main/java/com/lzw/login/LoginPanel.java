package com.lzw.login;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.lzw.Utils.Utils;

@SuppressWarnings("serial")

public class LoginPanel extends JPanel {
    protected ImageIcon icon;
    public int width, height;

    public LoginPanel() {
        super();
        icon = new ImageIcon(Utils.getResourceURL("login.jpg"));
        width = icon.getIconWidth();
        height = icon.getIconHeight();
        setSize(width, height);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = icon.getImage();
        g.drawImage(img, 0, 0, getParent());
    }
}