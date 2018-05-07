
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class FinalApp extends JFrame {
	
	private static final long serialVersionUID = 1L;
	Object[][] allNotes;
	int allNotesCounter;
	JPanel topPanel = new JPanel();
	JPanel btnPanel = new JPanel();
	JLabel startDate = new JLabel("Od daty: ");
	JLabel endDate = new JLabel("Do daty: ");
	JTextField sDateField = new JTextField("24.03.2018");
	JTextField eDateField = new JTextField("25.04.2018");
	
	private FinalApp() {
		
		JButton buttonAdd = new JButton("Dodaj notatkê");
		JButton buttonSearch = new JButton("Wyszukaj notatki");
		JButton buttonShowAll = new JButton("Poka¿ wszystkie");
		
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.CENTER);
		getContentPane().add(btnPanel,  BorderLayout.SOUTH);
		
		AgendaBean bean = new AgendaBean();
		setTitle(bean.getPanelTitle());
		setSize(bean.getWidth(), bean.getHeight());
		
		JScrollPane scrollPane = new JScrollPane(bean.getTable());
		topPanel.add(scrollPane, BorderLayout.CENTER);
		
		buttonAdd.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < bean.getNotesAmount(); i++) {
			        for(int j = 0; j < 3; j++) {
			        	bean.getNotes()[i][j] = bean.getTable().getValueAt(i, j);
			        }
			    }
				
				topPanel.removeAll();
				bean.increaseNotesAmount();
				Object[][] notes = new Object[bean.getNotesAmount()][3];
				for(int i = 0; i < bean.getNotesAmount() - 1; i++) {
					for(int j = 0; j < 3; j++) {
						notes[i][j] = bean.getNotes()[i][j];
					}
				}
				notes[bean.getNotesAmount() - 1][0] = bean.getNotesAmount();
				bean.setNotes(notes);
				bean.refreshTable();
				
				allNotes = bean.getNotes();
				allNotesCounter = bean.getNotesAmount();
				
				JScrollPane scrollPane = new JScrollPane(bean.getTable());
				topPanel.add(scrollPane, BorderLayout.CENTER);
				topPanel.updateUI();
			}
		});
		
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(int i = 0; i < bean.getNotesAmount(); i++) {
			        for(int j = 0; j < 3; j++) {
			        	bean.getNotes()[i][j] = bean.getTable().getValueAt(i, j);
			        }
			    }
				
				topPanel.removeAll();
				
				try {
					java.util.Date utilDateS = new SimpleDateFormat("dd.MM.yyyy").parse(sDateField.getText());
					java.sql.Date sqlDateS = new java.sql.Date(utilDateS.getTime());
					java.util.Date utilDateE = new SimpleDateFormat("dd.MM.yyyy").parse(eDateField.getText());
					java.sql.Date sqlDateE = new java.sql.Date(utilDateE.getTime());
					bean.setStartDate(sqlDateS);
					bean.setEndDate(sqlDateE);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				allNotes = bean.getNotes();
				allNotesCounter = bean.getNotesAmount();
				int filteredNotesCounter = 0;
				for(int i = 0; i < bean.getNotesAmount(); i++) {
					try {
						java.util.Date utilNoteDate = new SimpleDateFormat("dd.MM.yyyy").parse((String) bean.getNotes()[i][2]);
						java.sql.Date noteDate = new java.sql.Date(utilNoteDate.getTime());
			            if(noteDate.after(bean.getStartDate()) && noteDate.before(bean.getEndDate())) {
			            	filteredNotesCounter++;
			            }
			        } catch (ParseException ex) {
			            ex.printStackTrace();
			        }
				}
				
				Object[][] filteredNotes = new Object[filteredNotesCounter][3];
				
				int helpCounter = 0;
				for(int i = 0; i < bean.getNotesAmount(); i++) {
					try {
						java.util.Date utilNoteDate = new SimpleDateFormat("dd.MM.yyyy").parse((String) bean.getNotes()[i][2]);
						java.sql.Date noteDate = new java.sql.Date(utilNoteDate.getTime());
			            if(noteDate.after(bean.getStartDate()) && noteDate.before(bean.getEndDate())) {
			            	filteredNotes[helpCounter] = bean.getNotes()[i];
			            	helpCounter++;
			            }
			        } catch (ParseException ex) {
			            ex.printStackTrace();
			        }
				}
				
				bean.setNotesAmount(filteredNotesCounter);
				bean.setNotes(filteredNotes);
				bean.refreshTable();
				
				JScrollPane scrollPane = new JScrollPane(bean.getTable());
				topPanel.add(scrollPane, BorderLayout.CENTER);
				topPanel.updateUI();
			}
		});
		
		buttonShowAll.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				
				for(int i = 0; i < bean.getNotesAmount(); i++) {
			        for(int j = 0; j < 3; j++) {
			        	bean.getNotes()[i][j] = bean.getTable().getValueAt(i, j);
			        }
			    }
				
				topPanel.removeAll();
				
				bean.setNotesAmount(allNotesCounter);
				bean.setNotes(allNotes);
				bean.refreshTable();
				
				JScrollPane scrollPane = new JScrollPane(bean.getTable());
				topPanel.add(scrollPane, BorderLayout.CENTER);
				topPanel.updateUI();
				
			}
		});
		
		btnPanel.add(buttonAdd);
		btnPanel.add(buttonSearch);
		btnPanel.add(startDate);
		btnPanel.add(sDateField);
		btnPanel.add(endDate);
		btnPanel.add(eDateField);
		btnPanel.add(buttonShowAll);
	}
	
	public static void main(String[] args) {
		FinalApp frame = new FinalApp();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}

