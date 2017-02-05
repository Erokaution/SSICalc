package ero.tool.scalc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField HoursPerWeek;
	private JTextField DollarsPerHour;
	private JTextField DaysInMonth;
	private JTextField SSIPerMonth;
	private JTextArea TextArea;
	private JButton btnCalculate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setTitle("SSI Calculator");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		HoursPerWeek = new JTextField();
		HoursPerWeek.setText("20");
		HoursPerWeek.setToolTipText("Hours Per Week");
		HoursPerWeek.setBounds(10, 11, 86, 20);
		contentPane.add(HoursPerWeek);
		HoursPerWeek.setColumns(10);
		
		DollarsPerHour = new JTextField();
		DollarsPerHour.setText("9.75");
		DollarsPerHour.setToolTipText("Hourly Wage");
		DollarsPerHour.setBounds(106, 11, 86, 20);
		contentPane.add(DollarsPerHour);
		DollarsPerHour.setColumns(10);
		
		DaysInMonth = new JTextField();
		DaysInMonth.setText("28");
		DaysInMonth.setToolTipText("DaysInMonth");
		DaysInMonth.setBounds(252, 11, 86, 20);
		contentPane.add(DaysInMonth);
		DaysInMonth.setColumns(10);
		
		SSIPerMonth = new JTextField();
		SSIPerMonth.setText("660");
		SSIPerMonth.setToolTipText("SSIPerMonth");
		SSIPerMonth.setBounds(346, 11, 86, 20);
		contentPane.add(SSIPerMonth);
		SSIPerMonth.setColumns(10);
		
		TextArea = new JTextArea();
		TextArea.setToolTipText("Please insert gerder.");
		TextArea.setBounds(10, 117, 422, 142);
		contentPane.add(TextArea);
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.setToolTipText("Do Calculation");
		btnCalculate.addActionListener(this);
		btnCalculate.setBounds(176, 42, 89, 23);
		contentPane.add(btnCalculate);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCalculate) {
			try {
				String tstr = "";
				tstr = tstr + "If working " + HoursPerWeek.getText() + " hours per week.. \n";
				tstr = tstr + "At $" + DollarsPerHour.getText() + " per hour.. \n";		
				tstr = tstr + "And there's " + DaysInMonth.getText() + " days this month, and thus " + trimstr((Double.parseDouble(DaysInMonth.getText())/7) +"") + " weeks this month.. \n";
				tstr = tstr + "With $" + SSIPerMonth.getText() + " per month from Social Security.. \n"; 
				tstr = tstr + ".....\n";
	
				double DollarsPerHourd = Double.parseDouble(DollarsPerHour.getText());
				double HoursPerWeekd = Double.parseDouble(HoursPerWeek.getText());
				double DaysInMonthd = Double.parseDouble(DaysInMonth.getText());
				double SSIPerMonthd = Double.parseDouble(SSIPerMonth.getText());
				
				double JobIncomePerDayd = DollarsPerHourd * HoursPerWeekd / 7;
				double JobIncomeThisMonth = JobIncomePerDayd*DaysInMonthd;
				double TrueSSIThisMonth;
				if (JobIncomeThisMonth > 80) TrueSSIThisMonth = SSIPerMonthd - ((JobIncomeThisMonth / 2)-80);
				else TrueSSIThisMonth = SSIPerMonthd;
				double SSIPerDayd = TrueSSIThisMonth / DaysInMonthd;
				
				
				tstr = tstr + "...you'd make $" + trimstr(TrueSSIThisMonth+"") + " this month off SSI, and $" + trimstr(JobIncomeThisMonth + "") + " through work. \n";
				tstr = tstr + "...You'd make $" + trimstr(SSIPerDayd+"") + " per day off SSI, and $"+trimstr(JobIncomePerDayd + "")+ " per days through work. \n";
				tstr = tstr + "...And finally, you'd make $"+trimstr(TrueSSIThisMonth+JobIncomeThisMonth + "") + " in total this month.";
				TextArea.setText(tstr);
			} catch (Exception ee) {				
				TextArea.setText("ERROR: "+ee.toString());
				ee.printStackTrace();
			}
			
			
			
		}
		
	}
	
	public String trimstr(String s) {
		String[] tstr = s.split(Pattern.quote("."));
		if (tstr.length>0) {			
			if (tstr[1].length()>2) return tstr[0] +"."+ tstr[1].substring(0, 2);
			else return tstr[0] +"."+ tstr[1];
		}
		else return s;
	}
}
