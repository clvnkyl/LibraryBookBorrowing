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

public class PrimaryButton extends JButton {

    // Base theme color: [0, 102, 0]
    private static final Color BASE = new Color(0, 102, 0);
    private static final Color HOVER_TEXT = new Color(245, 245, 245);
    private static final Color NORMAL_TEXT = Color.WHITE;

    private boolean hover = false;
    private boolean pressed = false;
    private boolean focusVisible = false;

    public PrimaryButton() {
        setContentAreaFilled(false);
        setOpaque(false);
        setForeground(NORMAL_TEXT);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(10, 20, 10, 20));
        // Use Inter if available; will gracefully fall back if not installed
        setFont(new Font("Inter", Font.BOLD, 14));
        setFocusPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                setForeground(HOVER_TEXT);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                pressed = false;
                setForeground(NORMAL_TEXT);
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

        // Determine background color by state
        Color bg = BASE;
        if (!isEnabled()) {
            bg = mix(BASE, Color.GRAY, 0.6f);
        } else if (pressed) {
            bg = darken(BASE, 0.18f);
        } else if (hover) {
            bg = lighten(BASE, 0.10f);
        }

        // Subtle vertical gradient for depth
        Color top = lighten(bg, 0.06f);
        Color bottom = darken(bg, 0.06f);
        g2.setPaint(new GradientPaint(0, 0, top, 0, height, bottom));
        g2.fillRoundRect(0, 0, width, height, arc, arc);

        // Inner highlight when hovering (soft sheen)
        if (hover && isEnabled() && !pressed) {
            Color highlight = new Color(255, 255, 255, 40);
            g2.setPaint(new GradientPaint(0, 0, highlight, 0, height / 2, new Color(255, 255, 255, 0)));
            g2.fillRoundRect(2, 2, width - 4, (int) (height * 0.55), arc - 4, arc - 4);
        }

        // Focus ring that complements green (lighter lime ring)
        if (focusVisible && isEnabled()) {
            Stroke old = g2.getStroke();
            g2.setStroke(new BasicStroke(2f));
            g2.setColor(new Color(173, 255, 47, 160)); // yellow-green glow
            g2.drawRoundRect(1, 1, width - 3, height - 3, arc - 2, arc - 2);
            g2.setStroke(old);
        }

        // Optional subtle shadow at bottom edge for elevation
        if (isEnabled() && !pressed) {
            g2.setColor(new Color(0, 0, 0, 25));
            g2.fillRoundRect(0, height - Math.max(2, height / 12), width, Math.max(2, height / 12), arc, arc);
        }

        g.drawImage(img, 0, 0, null);

        super.paintComponent(g2); // not strictly needed for our background, but keeps pipeline consistent
        super.paintComponent(g);  // draw the text/icon over our custom background

        g2.dispose();
    }

    // --------- small color helpers ---------
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