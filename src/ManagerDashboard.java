import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ManagerDashboard extends JFrame {
    public ManagerDashboard() {
        super("Manager Dashboard - MCEMS");

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GradientPanel main = new GradientPanel();
        main.setLayout(new BorderLayout(0, 30));
        main.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        setContentPane(main);

        JLabel title = new JLabel("Welcome, Manager!", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        main.add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        addStyledButton(buttonPanel, "View All Employees", e -> new ViewEmployees());
        addStyledButton(buttonPanel, "Add Employee", e -> new AddEmployee());
        addStyledButton(buttonPanel, "View Leave Requests", e -> new ViewLeaveRequests());
        addStyledButton(buttonPanel, "Add Payroll", e -> new AddPayrollRecord());
        addStyledButton(buttonPanel, "View All Attendance", e -> new AttendanceLogViewer());
        addStyledButton(buttonPanel, "Logout", e -> {
            dispose();
            Login.main(new String[0]);
        });

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(buttonPanel);
        main.add(centerWrapper, BorderLayout.CENTER);

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addStyledButton(JPanel container, String text, ActionListener action) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setPreferredSize(new Dimension(270, 60));
        btn.setMaximumSize(new Dimension(270, 60));
        btn.setFont(btn.getFont().deriveFont(Font.BOLD, 18f));
        Color accent = new Color(58, 123, 213);
        btn.setForeground(accent);
        btn.setBackground(Color.WHITE);
        btn.setOpaque(true);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(accent, 3, true));
        btn.addActionListener(action);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(accent);
                btn.setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(Color.WHITE);
                btn.setForeground(accent);
            }
        });

        container.add(btn);
        container.add(Box.createVerticalStrut(20));
    }

    static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(
                0, 0, new Color(58, 123, 213),
                0, getHeight(), new Color(58, 96, 115)
            );
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ManagerDashboard::new);
    }
}