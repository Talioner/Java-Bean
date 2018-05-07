import java.io.Serializable;
import java.sql.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class AgendaBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	 private String panelTitle = "Terminarz";
	 private int width = 800;
	 private int height = 500;
	 private Object [][] notes = new Object[0][3];
	 private Date startDate;
	 private Date endDate;
	 private JTable table;
	 private int notesAmount = 0;
	 
	 public AgendaBean() {
		 
	 }
	 
	 public void refreshTable() {
		 String[] columnNames = { "Indeks", "Notatka", "Data" };
		 DefaultTableModel model = new DefaultTableModel(notes, columnNames);
		 table = new JTable(model) {
			 public Class getColumnClass (int column) {
				 if(column == 0)
					 return Integer.class;
				 if(column == 1)
					 return String.class;
				 else
					 return String.class;
			 }
		 };
		 
		 TableColumn column = null;
		 for (int i = 0; i < 3; i++) {
			 column = table.getColumnModel().getColumn(i);
			 if (i == 0) {
				 column.setMaxWidth(45);
			 }
			 if(i == 1)
				 column.setPreferredWidth(600);
			 if(i == 2) {
				 column.setPreferredWidth(130);
				 column.setMaxWidth(130);
			 }
		 }
		 
		 table.setRowHeight(40);
		 table.setRowMargin(5);
		 table.setPreferredScrollableViewportSize(table.getPreferredSize());
	 }
	 
	 public void increaseNotesAmount() {
		 this.notesAmount += 1;
	}
	 
	 public JTable getTable() {
		 return this.table;
	 }
	 
	 public void setTable(JTable table) {
		 this.table = table;
	 }
	
	public Object [][] getNotes() {
		return notes;
	}
	
	public void setNotes(Object [][] notes) {
		this.notes = notes;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public int getNotesAmount() {
		return notesAmount;
	}


	public void setNotesAmount(int notesAmount) {
		this.notesAmount = notesAmount;
	}

	public String getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(String panelTitle) {
		this.panelTitle = panelTitle;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}