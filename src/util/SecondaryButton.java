package util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class SecondaryButton extends JButton {

    // Neutral theme: starts light gray and moves darker on hover/press
    private static final Color BASE = new Color(242, 242, 242);   // light gray
    private static final Color DISABLED_MIX = new Color(180, 180, 180);
    private static final Color FOCUS_RING = new Color(120, 144, 156, 170); // blue-gray ring

    private boolean hover = false;
    private boolean pressed = false;
    private boolean focusVisible = false;

    public SecondaryButton() {
        setContentAreaFilled(false);
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(10, 20, 10, 20));
        setFont(new Font("Inter", Font.BOLD, 14));
        setFocusPainted(false);

        // Initial text color based on bg contrast
        setForeground(autoTextColor(BASE));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                pressed = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                repaint();
            }
        });

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                focusVisible = true;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                focusVisible = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int arc = height; // pill shape

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // State background
        Color bg = BASE;
        if (!isEnabled()) {
            bg = mix(BASE, DISABLED_MIX, 0.55f);
        } else if (pressed) {
            bg = darken(BASE, 0.28f); // darkest on press
        } else if (hover) {
            bg = darken(BASE, 0.10f); // slightly darker on hover
        }

        // Vertical gradient for depth
        Color top = lighten(bg, 0.06f);
        Color bottom = darken(bg, 0.08f);
        g2.setPaint(new GradientPaint(0, 0, top, 0, height, bottom));
        g2.fillRoundRect(0, 0, width, height, arc, arc);

        // Soft sheen on hover
        if (hover && isEnabled() && !pressed) {
            Color highlight = new Color(255, 255, 255, 50);
            g2.setPaint(new GradientPaint(0, 0, highlight, 0, height / 2, new Color(255, 255, 255, 0)));
            g2.fillRoundRect(2, 2, width - 4, (int) (height * 0.55), arc - 4, arc - 4);
        }

        // Focus ring
        if (focusVisible && isEnabled()) {
            Stroke old = g2.getStroke();
            g2.setStroke(new BasicStroke(2f));
            g2.setColor(FOCUS_RING);
            g2.drawRoundRect(1, 1, width - 3, height - 3, arc - 2, arc - 2);
            g2.setStroke(old);
        }

        // Bottom shadow for elevation (skip when pressed)
        if (isEnabled() && !pressed) {
            g2.setColor(new Color(0, 0, 0, 22));
            g2.fillRoundRect(0, height - Math.max(2, height / 12), width, Math.max(2, height / 12), arc, arc);
        }

        // Choose text color per current bg for contrast
        setForeground(autoTextColor(bg));

        g.drawImage(img, 0, 0, null);
        super.paintComponent(g2);
        super.paintComponent(g);
        g2.dispose();
    }

    // ---------- Helpers ----------
    private static Color autoTextColor(Color bg) {
        // Calculate relative luminance; switch text to white if bg is dark
        double lum = (0.2126 * srgbToLin(bg.getRed()/255.0))
                   + (0.7152 * srgbToLin(bg.getGreen()/255.0))
                   + (0.0722 * srgbToLin(bg.getBlue()/255.0));
        return lum < 0.5 ? Color.WHITE : new Color(33, 33, 33); // dark gray on light bg
    }

    private static double srgbToLin(double c) {
        return (c <= 0.04045) ? (c / 12.92) : Math.pow((c + 0.055) / 1.055, 2.4);
    }

    private static Color lighten(Color c, float amount) {
        amount = clamp(amount);
        int r = (int) (c.getRed() + (255 - c.getRed()) * amount);
        int g = (int) (c.getGreen() + (255 - c.getGreen()) * amount);
        int b = (int) (c.getBlue() + (255 - c.getBlue()) * amount);
        return new Color(clamp(r), clamp(g), clamp(b), c.getAlpha());
    }

    private static Color darken(Color c, float amount) {
        amount = clamp(amount);
        int r = (int) (c.getRed() * (1 - amount));
        int g = (int) (c.getGreen() * (1 - amount));
        int b = (int) (c.getBlue() * (1 - amount));
        return new Color(clamp(r), clamp(g), clamp(b), c.getAlpha());
    }

    private static Color mix(Color a, Color b, float t) {
        t = clamp(t);
        int r = (int) (a.getRed() * (1 - t) + b.getRed() * t);
        int g = (int) (a.getGreen() * (1 - t) + b.getGreen() * t);
        int bch = (int) (a.getBlue() * (1 - t) + b.getBlue() * t);
        int al = (int) (a.getAlpha() * (1 - t) + b.getAlpha() * t);
        return new Color(clamp(r), clamp(g), clamp(bch), clamp(al));
    }

    private static int clamp(int v) {
        return Math.max(0, Math.min(255, v));
    }

    private static float clamp(float v) {
        return Math.max(0f, Math.min(1f, v));
    }
}