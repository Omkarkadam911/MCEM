import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Login {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        SwingUtilities.invokeLater(Login::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("MCEMS Login");
        frame.setUndecorated(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GradientPanel main = new GradientPanel();
        main.setLayout(new GridBagLayout());
        main.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        frame.setContentPane(main);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        ImageIcon orig = new ImageIcon("employeee.png");
        Image img = orig.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));
        main.add(logo, gbc);

        gbc.gridy++;
        JLabel title = new JLabel("MCEMS Login");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        main.add(title, gbc);

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints f = new GridBagConstraints();
        f.insets = new Insets(5, 5, 5, 5);
        f.fill = GridBagConstraints.HORIZONTAL;

        f.gridx = 0;
        f.gridy = 0;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        form.add(userLabel, f);

        f.gridx = 1;
        JTextField userText = new JTextField(15);
        userText.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(10),
            BorderFactory.createEmptyBorder(5,5,5,5)
        ));
        form.add(userText, f);

        f.gridx = 0;
        f.gridy = 1;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        form.add(passLabel, f);

        f.gridx = 1;
        JPasswordField passText = new JPasswordField(15);
        passText.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(10),
            BorderFactory.createEmptyBorder(5,5,5,5)
        ));
        form.add(passText, f);

        gbc.gridy++;
        main.add(form, gbc);

        gbc.gridy++;
        JButton loginBtn = new JButton("Login");
        loginBtn.setPreferredSize(new Dimension(120, 35));
        loginBtn.setFont(loginBtn.getFont().deriveFont(Font.BOLD, 16f));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBackground(new Color(46, 204, 113));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(new RoundedBorder(15));
        main.add(loginBtn, gbc);

        loginBtn.addActionListener(e -> {
            String username = userText.getText().trim();
            String password = new String(passText.getPassword());
            if (username.equals("manager") && password.equals("admin123")) {
                JOptionPane.showMessageDialog(frame, "Welcome Manager!");
                new ManagerDashboard();
                frame.dispose();
            } else if (username.equals("worker") && password.equals("worker123")) {
                JOptionPane.showMessageDialog(frame, "Welcome Worker!");
                new WorkerDashboard();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(
                    frame,
                    "Invalid credentials",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            int w = getWidth(), h = getHeight();
            GradientPaint gp = new GradientPaint(
                0, 0, new Color(58, 123, 213),
                0, h, new Color(58, 96, 115)
            );
            g2.setPaint(gp);
            g2.fillRect(0, 0, w, h);
        }
    }

    static class RoundedBorder implements javax.swing.border.Border {
        private final int radius;
        RoundedBorder(int r) { radius = r; }
        public Insets getBorderInsets(Component c) {
            return new Insets(radius+1, radius+1, radius+1, radius+1);
        }
        public boolean isBorderOpaque() { return false; }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );
            g2.setColor(Color.WHITE);
            g2.drawRoundRect(x, y, w-1, h-1, radius, radius);
        }
    }
}