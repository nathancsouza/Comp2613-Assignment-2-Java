package a01164474.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import a01164474.data.Book;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

@SuppressWarnings("serial")
public class BookListDialog extends JDialog {
	
	private static final Logger lOG = LogManager.getLogger();
	private final JPanel contentPanel = new JPanel();
	
	private JList<Book> bookList;
	private DefaultListModel<Book> listModel;	

	/**
	 * Create the dialog.
	 */
	public BookListDialog(List<Book> list) {
		listModel = new DefaultListModel<Book>();
		for(Book b: list) {
			listModel.addElement(b);
		}
		
		bookList = new JList<Book>(listModel);
		
		buildMenu();
		addEventHandlers();
	}
	
	private void buildMenu() {
		setSize(550, 500);
		setLocationRelativeTo(null);
		setTitle("Book Store");
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton("Close");
				closeButton.setActionCommand("Cancel");
				closeButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BookListDialog.this.dispose();
					}

				});
				buttonPane.add(closeButton);
			}
		}
		
		add(new JScrollPane(bookList));
        bookList.setCellRenderer(new BookRenderer());
	}
	
	class BookRenderer extends JLabel implements ListCellRenderer<Book> {

		@Override
		public Component getListCellRendererComponent(JList<? extends Book> list, Book book, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			setText(String.format("%s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s", 
					book.getId(), 
					book.getIsbn(), 
					book.getAuthors(),
					book.getTitle(),
					book.getPublicationYear(),
					book.getAverageRating(),
					book.getRatingsCount(),
					book.getImageURL()
					));
			if(isSelected) {
				setBackground(list.getSelectionBackground());
			}else {
				setBackground(list.getBackground());
			}
			return this;
		}
		
	}
	
	private void addEventHandlers() {
		lOG.debug("ListOfBook received EventHandlers");
	}
}
